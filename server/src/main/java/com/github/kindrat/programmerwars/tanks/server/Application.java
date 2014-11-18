package com.github.kindrat.programmerwars.tanks.server;

import com.github.kindrat.programmerwars.tanks.common.util.LogUtils;
import com.github.kindrat.programmerwars.tanks.server.context.RestContext;
import com.github.kindrat.programmerwars.tanks.server.context.RestJavaConfigContextStarter;
import com.github.kindrat.programmerwars.tanks.server.context.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Application implements ApplicationMBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) {
        try {
            LogUtils.initLoggers();
            RestJavaConfigContextStarter starter = new RestJavaConfigContextStarter(Application.class, RestContext.class, SpringContext.class);
            starter.start();
            starter.registerShutdownHook();
            // prevent auto-exiting to OS
            Thread.currentThread().join();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            System.exit(1);
        }
    }
}
