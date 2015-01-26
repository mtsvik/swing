package FuelCalculateDynamicProgramming;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.util.List;

import static javax.swing.BorderFactory.*;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * Author: Mikhail Tsvik (tsvik@me.com)
 * Date: 07.04.14
 */

public class FuelCalculation extends JFrame {

    private static JComboBox<Car> choose = new JComboBox<>();
    private JPanel panel = new JPanel(new GridBagLayout());
    private JTextField fuelPrice = new JTextField();
    private JTextField yearDist = new JTextField();
    private JTextField summary = new JTextField();
    private JLabel carName = new JLabel("Choose car");
    private JLabel fuelName = new JLabel("Cost of fuel, RUB/L");
    private static JLabel fuelType = new JLabel();
    private JLabel distCount = new JLabel("Year distance, KM");
    private JLabel result = new JLabel("Result, RUB");

    public FuelCalculation() throws Exception {
        super("Fuel Calculation (Pre-Alpha Version)");
        Loader loader = new Loader();
        loader.execute();
        setLookAndFeel(getSystemLookAndFeelClassName());
        if (getSystemLookAndFeelClassName().equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"))
            setLookAndFeel(new MetalLookAndFeel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(panel);
        summary.setEditable(false);

        /* Create a listners for JCBoxes and JTfild source value  */
        choose.addItemListener(new Listener(choose, fuelPrice, yearDist, summary));
        fuelPrice.getDocument().addDocumentListener(new Listener(choose, fuelPrice, yearDist, summary));
        yearDist.getDocument().addDocumentListener(new Listener(choose, fuelPrice, yearDist, summary));

        designAndLocation(choose, fuelPrice, yearDist, summary, panel, carName, fuelName, distCount, result, fuelType);
    }


    static final Color fontColor = new Color(169, 183, 198);
    static final Color bgColor = new Color(53, 53, 53);
    static final Color labelColor = new Color(120, 120, 120);

    private void designAndLocation(JComboBox<Car> choose, JTextField fuelPrice, JTextField yearDist, JTextField summary,
                                   JPanel panel, JLabel carName, JLabel fuelName, JLabel distCount, JLabel result, JLabel fuelType) {

        /* Location */
        panel.add(carName, new GridBagConstraints(0, 0, 3, 1, 100, 10, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
        panel.add(choose, new GridBagConstraints(0, 1, 3, 1, 100, 90, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 10, 5, 10), 0, 0));

        panel.add(fuelName, new GridBagConstraints(0, 2, 1, 1, 100, 10, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 10, 0, 5), 0, 0));
        panel.add(fuelType, new GridBagConstraints(1, 2, 1, 1, 100, 10, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 10, 0, 50), 0, 0));
        panel.add(fuelPrice, new GridBagConstraints(0, 3, 2, 1, 100, 90, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 10, 0, 5), 0, 0));
        panel.add(distCount, new GridBagConstraints(2, 2, 1, 1, 40, 10, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 0, 10), 0, 0));
        panel.add(yearDist, new GridBagConstraints(2, 3, 1, 1, 40, 90, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 0, 10), 0, 0));

        panel.add(result, new GridBagConstraints(0, 4, 3, 1, 100, 10, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
        panel.add(summary, new GridBagConstraints(0, 5, 3, 1, 100, 90, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 10, 10, 10), 0, 0));

        /* Fonts */
        fuelPrice.setFont(new Font("Verdana", 0, 16));
        yearDist.setFont(new Font("Verdana", 0, 16));
        summary.setFont(new Font("Verdana", 0, 16));
        choose.setFont(new Font("Verdana", 0, 13));
        carName.setFont(new Font("Verdana", 0, 13));
        fuelName.setFont(new Font("Verdana", 0, 13));
        distCount.setFont(new Font("Verdana", 0, 13));
        result.setFont(new Font("Verdana", 0, 13));
        fuelType.setFont(new Font("Verdana", 0, 13));

        /* Foregrounds colors */
        fuelPrice.setForeground(fontColor);
        yearDist.setForeground(fontColor);
        summary.setForeground(fontColor);
        choose.setForeground(fontColor);
        carName.setForeground(labelColor);
        fuelName.setForeground(labelColor);
        distCount.setForeground(labelColor);
        result.setForeground(labelColor);
        fuelType.setForeground(labelColor);

        /* Backgrounds colors */
        panel.setBackground(new Color(66, 66, 66));
        fuelPrice.setBackground(bgColor);
        yearDist.setBackground(bgColor);
        summary.setBackground(bgColor);
        choose.setBackground(bgColor);

        /* Other colors */
        fuelPrice.setCaretColor(fontColor);
        yearDist.setCaretColor(fontColor);

        /* Romoves arrow button and sets count of visible elements of popup list */
        choose.setMaximumRowCount(10);
        choose.setUI(new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return new JButton() {
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });

        /* Adds margin to JTextFields and removes borders*/
        choose.setBorder(createCompoundBorder(createLineBorder(new Color(66, 66, 66), 0), createEmptyBorder(0, 0, 0, 0)));
        fuelPrice.setBorder(createCompoundBorder(createLineBorder(new Color(66, 66, 66), 0), createEmptyBorder(0, 7, 0, 0)));
        yearDist.setBorder(createCompoundBorder(createLineBorder(new Color(66, 66, 66), 0), createEmptyBorder(0, 7, 0, 0)));
        summary.setBorder(createCompoundBorder(createLineBorder(new Color(66, 66, 66), 0), createEmptyBorder(0, 7, 0, 0)));
    }

    /* Updates fuel type label when choose has been changed */
    static void changeFuelTypeLabel(String fuel) {
        fuelType.setText(fuel);
    }

    static void addToBox(Car car) {
        choose.addItem(car);
    }

    static void addToBox(List<Car> dataOfCars) {
        for (Car car : dataOfCars) {
            choose.addItem(car);
        }
    }
}
