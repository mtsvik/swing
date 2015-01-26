package FuelCalculateDynamicProgramming;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Author: Mikhail Tsvik (tsvik@me.com)
 * Date: 08.04.14
 */

/**
 * This class is a special universal listener for a JTextField and JComboBoxes .
 * Also, class calculates result value
 */
public class Listener implements DocumentListener, ItemListener {

    private JComboBox<Car> choose;
    private JTextField fuelPrice;
    private JTextField yearDist;
    private JTextField summary;

    public Listener(JComboBox<Car> choose, JTextField fuelPrice, JTextField yearDist, JTextField summary) {
        this.choose = choose;
        this.fuelPrice = fuelPrice;
        this.yearDist = yearDist;
        this.summary = summary;
    }

    private void update() {
        String resultCalc = calculate((Car) choose.getSelectedItem(), fuelPrice.getText(), yearDist.getText(), summary);
        summary.setText(resultCalc);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        update();
        FuelCalculation.changeFuelTypeLabel(((Car) choose.getSelectedItem()).getFuelType());
    }

    /* This metod calculates result value and changes text color if input is incorrect */
    private String calculate(Car car, String fuelCost, String yearDist, JTextField result) {
        try {
            result.setForeground(FuelCalculation.fontColor);
            if (fuelCost.equals("")) return "";
            if (yearDist.equals("")) return "";
            double fuelPrice = Double.parseDouble(fuelCost);
            double year = Double.parseDouble(yearDist);
            double fuelCons = car.getFuelСonsumption();
            if (fuelPrice < 0 | year < 0) {
                result.setForeground(Color.red);
                return "Invalid amount";
            }
            double calcResult = fuelPrice * year * (fuelCons / 100);
            double roundedResult = new BigDecimal(calcResult).setScale(2, RoundingMode.UP).doubleValue();
            return String.valueOf(roundedResult);
        } catch (NumberFormatException e) {
            result.setForeground(Color.red);
            return "Invalid amount";
        }
    }

}
