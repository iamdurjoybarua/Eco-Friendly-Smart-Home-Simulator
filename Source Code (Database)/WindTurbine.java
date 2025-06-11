// Class representing a wind turbine
public class WindTurbine implements RenewableEnergySource {
    private double bladeDiameter; // in meters
    private double windSpeed;     // in meters per second
    private double efficiency;    // efficiency factor (0-1)

    // Constructor to initialize blade diameter and efficiency
    public WindTurbine(double bladeDiameter, double efficiency) {
        this.bladeDiameter = bladeDiameter;
        this.efficiency = efficiency;
        this.windSpeed = 0.0; // default wind speed
    }

    // Setter for wind speed
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    // Generates energy output in Watts using simplified wind turbine power formula
    @Override
    public double generateEnergy() {
        double airDensity = 1.225; // kg/m^3 (standard air density at sea level)
        double sweptArea = Math.PI * Math.pow(bladeDiameter / 2, 2);
        return 0.5 * airDensity * sweptArea * Math.pow(windSpeed, 3) * efficiency;
    }

    // Getter for blade diameter
    public double getBladeDiameter() {
        return bladeDiameter;
    }
}
