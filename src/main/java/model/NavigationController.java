package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController implements Serializable {
    public String processIndex() {
        return "welcome";
    }
    public String processApp() {
        return "welcome";
    }
    // Add this to buttons in welcome :|
}
