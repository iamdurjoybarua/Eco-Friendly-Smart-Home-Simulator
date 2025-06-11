// Class representing solar panels, implementing RenewableEnergySource
public class SolarPanel implements RenewableEnergySource {
    private double surfaceArea;        // in square meters
    private double efficiency;         // as decimal (e.g., 0.2 for 20%)
    private double sunlightIntensity;  // in Watts per square meter

    // Constructor to initialize surface area and efficiency
    public SolarPanel(double surfaceArea, double efficiency) {
        this.surfaceArea = surfaceArea;
        this.efficiency = efficiency;
        this.sunlightIntensity = 0.0; // default: no sunlight initially
    }

    // Setter for sunlight intensity (W/m^2)
    public void setSunlightIntensity(double sunlightIntensity) {
        this.sunlightIntensity = sunlightIntensity;
    }

    // Calculate energy generated in Watts: Area * Intensity * Efficiency
    @Override
    public double generateEnergy() {
        return surfaceArea * sunlightIntensity * efficiency;
    }

    // Getter for surface area
    public double getSurfaceArea() {
        return surfaceArea;
    }
}
