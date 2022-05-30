package model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CounterBean {
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private int counter;
    private boolean flag;

    public void incrementCounter() {
        counter = counter + 1;
        System.out.println("total count : " + counter);
        if (counter % 10 == 0) {
            System.out.println("flag !!!!");
            flag = true;
        } else {
            flag = false;
        }
    }
}
