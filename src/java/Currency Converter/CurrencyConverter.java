package ru.ifmo.enf.tsvik.t02;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.io.IOException;

import static javax.swing.BorderFactory.*;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * Author: Mikhail Tsvik (tsvik@me.com)
 * Date: 23.03.14
 */

public class CurrencyConverter extends JFrame {
    public CurrencyConverter() throws IOException, SAXException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("Currency Converter (Pre-Alpha Version)");
        CurrencyParser.parse();
        setLookAndFeel(getSystemLookAndFeelClassName());
        if (getSystemLookAndFeelClassName().equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) setLookAndFeel(new MetalLookAndFeel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 150);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        setContentPane(panel);

        /* Add to JCBox source currency name and choose start (default) value */
        JComboBox<String> srcChoose = new JComboBox<>(CurrencyParser.getKeys());
        srcChoose.setSelectedIndex(13);

        /* Add to JCBox source currency name and choose start (default) value */
        JComboBox<String> resultChoose = new JComboBox<>(CurrencyParser.getKeys());
        resultChoose.setSelectedIndex(31);

        JTextField srcValue = new JTextField();
        JTextField resultValue = new JTextField();
        resultValue.setEditable(false);

        /* Create a listners for JCBoxes and JTfild source value  */
        srcChoose.addItemListener(new Listner(srcChoose, resultChoose, srcValue, resultValue));
        resultChoose.addItemListener(new Listner(srcChoose, resultChoose, srcValue, resultValue));
        srcValue.getDocument().addDocumentListener(new Listner(srcChoose, resultChoose, srcValue, resultValue));

        designAndLocation(srcChoose, resultChoose, srcValue, resultValue, panel);
    }


    static final Color fontColor = new Color(169, 183, 198);
    private void designAndLocation(JComboBox<String> srcChoose, JComboBox<String> resultChoose, JTextField srcValue, JTextField resultValue, JPanel panel) {

        /* Location */
        panel.add(srcChoose, new GridBagConstraints(0, 0, 1, 1, 10, 20,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 5, 5), 0, 0));

        panel.add(resultChoose, new GridBagConstraints(1, 0, 1, 1, 10, 20,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 5, 5, 10), 0, 0));

        panel.add(srcValue, new GridBagConstraints(0, 1, 1, 1, 20, 80,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 10, 10, 5), 0, 0));

        panel.add(resultValue, new GridBagConstraints(1, 1, 1, 1, 20, 80,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 10, 10), 0, 0));

        /* Colors */
        panel.setBackground(new Color(66, 66, 66));
        resultValue.setFont(new Font("Verdana", 0, 18));
        srcValue.setFont(new Font("Verdana", 0, 18));
        srcValue.setBackground(new Color(53, 53, 53));
        srcValue.setForeground(fontColor);
        resultValue.setBackground(new Color(53, 53, 53));
        resultValue.setForeground(fontColor);
        srcValue.setCaretColor(fontColor);
        srcChoose.setBackground(new Color(53, 53, 53));
        srcChoose.setForeground(fontColor);
        resultChoose.setBackground(new Color(53, 53, 53));
        resultChoose.setForeground(fontColor);

        /* Romoves arrow button, sets count of visible elements of popup list and changes font in JComboBoxes*/
        for (JComboBox combo: new JComboBox[]{srcChoose, resultChoose}){
            combo.setUI(new BasicComboBoxUI() {
                protected JButton createArrowButton() {
                    return new JButton() {
                        public int getWidth() {
                            return 0;
                        }
                    };
                }
            });
            combo.setMaximumRowCount(15);
            combo.setFont(new Font("Verdana", 0, 14));
        }

        /* Adds margin to JTextFields and removes borders*/
        srcChoose.setBorder(createCompoundBorder(createLineBorder(new Color(66, 66, 66), 0), createEmptyBorder(0, 0, 0, 0)));
        resultChoose.setBorder(createCompoundBorder(createLineBorder(new Color(66, 66, 66), 0), createEmptyBorder(0, 0, 0, 0)));
        srcValue.setBorder(createCompoundBorder(createLineBorder(new Color(66, 66, 66), 0), createEmptyBorder(0, 7, 0, 0)));
        resultValue.setBorder(createCompoundBorder(createLineBorder(new Color(66, 66, 66), 0), createEmptyBorder(0, 7, 0, 0)));
    }
}



