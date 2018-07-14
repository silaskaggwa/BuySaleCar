package listeners;

import datastorage.DataStorage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializeListner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataStorage.INSTANCE.loadDefaultData();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
