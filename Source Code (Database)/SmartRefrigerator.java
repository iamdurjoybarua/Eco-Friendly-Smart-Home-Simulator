public class SmartRefrigerator extends EnergyConsumer {

    public SmartRefrigerator(String name, double powerConsumption) {
        super(name, powerConsumption);
    }

    public void setStatus(boolean status) {
        if (status) {
            turnOn();
        } else {
            turnOff();
        }
    }

    @Override
    public void displayStatus() {
        System.out.println(getName() + " is " + (isStatus() ? "ON" : "OFF"));
    }
}
