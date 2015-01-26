package FuelCalculateDynamicProgramming;

/**
 * Author: Mikhail Tsvik (tsvik@me.com)
 * Date: 07.04.14
 */

public class Car {
    private final double fuelСonsumption;
    private final String name;
    private final FuelType fuel;

    enum FuelType {
        FUEL92("92"), FUEL95("95"), FUEL98("98"), DFUEL("DF"), UNKNOWN("Unknown");
        private final String name;

        private FuelType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Car(FuelType fuelType, double fuelСonsumption, String name) {
        this.fuelСonsumption = fuelСonsumption;
        this.fuel = fuelType;
        this.name = name;
    }

    public String getFuelType() {
        return "[Fuel type: " + fuel.getName() + "]";
    }

    public double getFuelСonsumption() {
        return fuelСonsumption;
    }

    @Override
    public String toString() {
        return " " + name;
    }
}
