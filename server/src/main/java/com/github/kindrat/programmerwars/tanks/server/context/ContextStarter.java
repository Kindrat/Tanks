package com.github.kindrat.programmerwars.tanks.server.context;

import com.github.kindrat.programmerwars.tanks.server.Application;
import com.github.kindrat.programmerwars.tanks.server.ApplicationMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public abstract class ContextStarter {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private final Class<Application> applicationClass;

    public ContextStarter(Class<Application> applicationClass) {
        this.applicationClass = applicationClass;
    }

    public void start() {
        String workerName = applicationClass.getSimpleName();
        try {
            ApplicationContext applicationContext = createApplicationContext();
            Application application = applicationContext.getBean(applicationClass);
            registerMbean(application);
            LOG.info("{} started", workerName);
        } catch (Exception e) {
            LOG.error("Failed to start {}", workerName, e);
        }
    }

    public abstract ApplicationContext createApplicationContext() throws Exception;

    private void registerMbean(ApplicationMBean applicationMBean) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName mBeanName = createMBeanName(applicationMBean.getClass());
            mBeanServer.registerMBean(applicationMBean, mBeanName);
        } catch (Exception e) {
            LOG.error("Could not register MBean : {}", e.getMessage(), e);
        }
    }

    private ObjectName createMBeanName(Class<? extends ApplicationMBean> mBeanType) throws MalformedObjectNameException {
        String className = mBeanType.getSimpleName();
        String packageName = mBeanType.getPackage().getName();
        String name = packageName + ":type=" + className;
        return new ObjectName(name);
    }
}
