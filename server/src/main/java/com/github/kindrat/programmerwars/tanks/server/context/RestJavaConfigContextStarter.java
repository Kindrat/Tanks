package com.github.kindrat.programmerwars.tanks.server.context;

import com.github.kindrat.programmerwars.tanks.server.Application;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.net.URI;

public class RestJavaConfigContextStarter extends ContextStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestJavaConfigContextStarter.class);
    private final Class<? extends ResourceConfig> jerseyConfigClass;
    private final Class<?> springConfigClass;
    private ConfigurableApplicationContext applicationContext;
    private HttpServer server;

    public RestJavaConfigContextStarter(
            Class<Application> workerClass,
            Class<? extends ResourceConfig> jerseyConfigClass,
            Class<?> springConfigClass
    ) {
        super(workerClass);
        this.jerseyConfigClass = jerseyConfigClass;
        this.springConfigClass = springConfigClass;
    }

    @Override
    public ApplicationContext createApplicationContext() throws IOException {
        LOGGER.info("Initializing application context for: {}", springConfigClass);
        LOGGER.info("Starting grizzly...");
        // Initialize Grizzly HttpServer
        server = new HttpServer();
        // Initialize and add Spring-aware Jersey resource
        WebappContext ctx = new WebappContext("ctx", "/");
        ctx.addContextInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        ctx.addContextInitParameter("contextConfigLocation", springConfigClass.getName());
        ctx.addListener("org.springframework.web.context.ContextLoaderListener");
        ctx.addListener("org.springframework.web.context.request.RequestContextListener");
        // Register Spring servlet
        ServletContainer servlet = new ServletContainer();
        ServletRegistration reg = ctx.addServlet("spring", servlet);
        // The logging filters for server logging.
        reg.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", "com.sun.jersey.api.container.filter.LoggingFilter");
        reg.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", "com.sun.jersey.api.container.filter.LoggingFilter;robowarserver.rest.AuthFilter");
        reg.setInitParameter("javax.ws.rs.Application", jerseyConfigClass.getName());
        reg.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        reg.setInitParameter(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, "true");
        reg.setInitParameter(ServerProperties.WADL_FEATURE_DISABLE, "true");
        reg.addMapping("/*");
        ctx.deploy(server);

        applicationContext = (ConfigurableApplicationContext) WebApplicationContextUtils.getWebApplicationContext(servlet.getServletContext());
        URI serverUri = applicationContext.getBean(URI.class);

        NetworkListener listener = new NetworkListener("grizzly2", serverUri.getHost(), serverUri.getPort());
        server.addListener(listener);
        server.start();

        LOGGER.info("Jersey app started at {}", serverUri);

        return applicationContext;
    }

    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(
                new Thread("shutdownHook") {
                    @Override
                    public void run() {
                        close();
                    }
                }
        );
    }

    public ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void close() {
        LOGGER.info("Stopping server..");
        server.shutdownNow();
        applicationContext.close();
    }
}
