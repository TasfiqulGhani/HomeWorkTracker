package tashfik.jjmotivation.com.wakeup.models;

public class HomeWork {

    String work;
    int deliveryDay;
    int deliveryMonth;
    int theDay;
    int theMonth;
    int led;


    public HomeWork() {


    }


    public int getTheDay() {
        return theDay;
    }

    public void setTheDay(int theDay) {
        this.theDay = theDay;
    }

    public int getTheMonth() {
        return theMonth;
    }

    public void setTheMonth(int theMonth) {
        this.theMonth = theMonth;
    }

    public int getLed() {
        return led;
    }

    public void setLed(int led) {
        this.led = led;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public int getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(int deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public int getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(int deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }
}
