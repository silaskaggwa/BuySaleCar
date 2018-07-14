package listeners;

import datastorage.DataStorage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializeListner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println(">>>> loading default data");
        DataStorage.INSTANCE.loadDefaultData();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
