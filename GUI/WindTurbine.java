// Class representing a wind turbine
public class WindTurbine implements RenewableEnergySource {
    private double bladeDiameter; // in meters
    private double windSpeed; // in meters per second
    private double efficiency;

    // Constructor for the WindTurbine
    public WindTurbine(double bladeDiameter, double efficiency) {
        this.bladeDiameter = bladeDiameter;
        this.windSpeed = 0.0;
        this.efficiency = efficiency;
    }

    // Setter for the wind speed
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    // Override the generateEnergy method to calculate energy produced by the wind turbine
    @Override
    public double generateEnergy() {
        // Simplified power calculation:  Power = 0.5 * airDensity * sweptArea * windSpeed^3 * efficiency
        double airDensity = 1.225; // kg/m^3 (standard air density)
        double sweptArea = Math.PI * Math.pow(bladeDiameter / 2, 2);
        return 0.5 * airDensity * sweptArea * Math.pow(windSpeed, 3) * efficiency;
    }

    // Getter for the blade diameter of the wind turbine
    public double getBladeDiameter() {
        return bladeDiameter;
    }
}