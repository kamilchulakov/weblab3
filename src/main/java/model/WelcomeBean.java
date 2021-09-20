package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
/*
Note
In JSF 1.x, you had to declare beans in the faces-config.xml, but this is no longer required in JSF 2.0.
 */
@ManagedBean
@SessionScoped
public class WelcomeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
