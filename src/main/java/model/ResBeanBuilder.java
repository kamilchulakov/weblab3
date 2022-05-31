package model;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class ResBeanBuilder {
    @ManagedProperty(value = "#{resultsBean}")
    static ResultsBeanPoints resultsBeanPoints;

    public static ResultsBeanPoints getBean() {
        if (resultsBeanPoints == null) {
            try {
                resultsBeanPoints = new ResultsBeanPoints();
            } catch (Exception e) {

            }
        }
        return resultsBeanPoints;
    }
}
