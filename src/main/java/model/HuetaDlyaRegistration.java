package model;

import mBeans.PointsCounter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.management.*;
import java.lang.management.ManagementFactory;

@ManagedBean(eager = true)
@ApplicationScoped
public class HuetaDlyaRegistration {
    private static PointsCounter pcBean;
    public HuetaDlyaRegistration() {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
         pcBean = new PointsCounter();
        try {
            ObjectName pcName = new ObjectName("web:type=PointsCounter");
            server.registerMBean(pcBean, pcName);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            System.out.println("КАМИЛЬ ДОЛБАЕБ");
        }
    }

    public static PointsCounter getPcBean() {
        return pcBean;
    }
}
