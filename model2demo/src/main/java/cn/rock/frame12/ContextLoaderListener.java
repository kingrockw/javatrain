package cn.rock.frame12;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String frame12config = sce.getServletContext().getInitParameter("frame12config");
        ApplicationContext.init(frame12config);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
