// Class representing solar panels, implementing RenewableEnergySource
class SolarPanel implements RenewableEnergySource {
    private double surfaceArea; // in square meters
    private double efficiency; // as a decimal (e.g., 0.2 for 20%)
    private double sunlightIntensity; // in Watts per square meter

    // Constructor for the SolarPanel
    public SolarPanel(double surfaceArea, double efficiency) {
        this.surfaceArea = surfaceArea;
        this.efficiency = efficiency;
        this.sunlightIntensity = 0.0; // Initially no sunlight
    }

    // Setter for the sunlight intensity
    public void setSunlightIntensity(double sunlightIntensity) {
        this.sunlightIntensity = sunlightIntensity;
    }

    // Override the generateEnergy method to calculate energy produced by the solar panel
    @Override
    public double generateEnergy() {
        // Calculate energy generated: Area * Intensity * Efficiency
        return surfaceArea * sunlightIntensity * efficiency;
    }

    // Getter for the surface area of the solar panel
    public double getSurfaceArea() {
        return surfaceArea;
    }
}
