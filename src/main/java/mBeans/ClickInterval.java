package mBeans;

import javax.management.NotificationBroadcasterSupport;

public class ClickInterval extends NotificationBroadcasterSupport implements ClickIntervalMBean {
    long lastTime = 0l;
    long sumTime = 0l;
    long avgInterval = 0;

    @Override
    public void doInterval() {
        long currentTime = System.currentTimeMillis();
        avgInterval = sumTime + (currentTime - lastTime) / HuetaDlyaRegistration.getPcBean().totalPoints;
        lastTime = currentTime;
    }

    @Override
    public long getInterval() {
        return avgInterval;
    }
}
