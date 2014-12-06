package t02_t03;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Author: Mikhail Tsvik (tsvik@me.com)
 * Date: 07.04.14
 */

public class Loader extends SwingWorker<Void, Car> {
    private List<Car> data = new ArrayList<>();
    private final static String fileName = "data.txt";
    private int counter;
    private double fuelСonsumption;
    private Car.FuelType fuel;
    private String[] buff;

    @Override
    protected Void doInBackground() throws Exception {
        Scanner scanner = new Scanner(Loader.class.getResourceAsStream(fileName));
        scanner.useDelimiter(System.getProperty("line.separator"));
        while (scanner.hasNext()) {
            buff = scanner.next().split(" ");
            switch (buff[0]) {
                case "92":
                    fuel = Car.FuelType.FUEL92;
                    break;
                case "95":
                    fuel = Car.FuelType.FUEL95;
                    break;
                case "98":
                    fuel = Car.FuelType.FUEL98;
                    break;
                case "ДТ":
                    fuel = Car.FuelType.DFUEL;
                    break;
                default:
                    fuel = Car.FuelType.UNKNOWN;
            }
            try {
                fuelСonsumption = Double.parseDouble(buff[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                continue;
            }
            final StringBuilder name = new StringBuilder();
            for (int j = 2; j < buff.length; j++) name.append(buff[j]).append(" ");
            Thread.sleep((new Random().nextInt(1501) + 500));
            Car tempCar = new Car(fuel, fuelСonsumption, name.toString());
            if (counter < 4) {
                publish(tempCar);
                counter++;
            } else data.add(tempCar);
        }
        scanner.close();
        return null;
    }

    @Override
    protected void done() {
        FuelCalculation.addToBox(data);
    }

    @Override
    protected void process(List<Car> data) {
        FuelCalculation.addToBox(data.get(0));
    }
}