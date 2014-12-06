package ru.ifmo.enf.tsvik.t02;

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
 * Date: 31.03.14
 */


/**
 * This class is a special universal listener for a JTextField (source value) and JComboBoxes (source and result currency).
 * Also, class calculates currency value
 */
public class Listner implements DocumentListener, ItemListener {

    private JComboBox<String> srcChoose;
    private JComboBox<String> resultChoose;
    private JTextField srcValue;
    private JTextField resultValue;

    public Listner(JComboBox<String> srcChoose, JComboBox<String> resultChoose, JTextField srcValue, JTextField resultValue) {
        this.srcChoose = srcChoose;
        this.resultChoose = resultChoose;
        this.srcValue = srcValue;
        this.resultValue = resultValue;
    }

    private void update() {
        String result = calculateCurrency(srcChoose.getSelectedItem().toString(), resultChoose.getSelectedItem().toString(), srcValue.getText());
        resultValue.setText(result);
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
    }

    /* This metod calculates currency value and changes text color if input is incorrect */
    private String calculateCurrency(String srcCurr, String resultCurr, String amount) {
        try {
            resultValue.setForeground(CurrencyConverter.fontColor);
            double srcRate = CurrencyParser.rates.get(srcCurr);
            double rsltRate = CurrencyParser.rates.get(resultCurr);
            if (amount.equals("")) return "";
            double calcResult = Double.parseDouble(amount) * (srcRate / rsltRate);
            double roundedResult = new BigDecimal(calcResult).setScale(2, RoundingMode.UP).doubleValue();
            return String.valueOf(roundedResult);
        } catch (NumberFormatException e) {
            resultValue.setForeground(Color.red);
            return "Invalid amount";
        }
    }
}
