package mBeans;

import mBeans.PointsCounter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.management.*;
import java.lang.management.ManagementFactory;

@ManagedBean(eager = true)
@ApplicationScoped
public class HuetaDlyaRegistration {
    private static PointsCounter pcBean;
    private static ClickInterval clickIntervalB;

    public HuetaDlyaRegistration() {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        pcBean = new PointsCounter();
        clickIntervalB = new ClickInterval();
        try {
            ObjectName pcName = new ObjectName("web:type=PointsCounter");
            server.registerMBean(pcBean, pcName);
            ObjectName clickIntervalBName = new ObjectName("web:type=ClickInterval");
            server.registerMBean(clickIntervalB, clickIntervalBName);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            System.out.println("КАМИЛЬ ДОЛБАЕБ");
        }
    }

    public static ClickInterval getClickIntervalB() {
        return clickIntervalB;
    }

    public static PointsCounter getPcBean() {
        return pcBean;
    }
}
