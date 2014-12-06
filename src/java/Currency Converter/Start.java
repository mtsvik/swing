package ru.ifmo.enf.tsvik.t02;

import org.xml.sax.SAXException;

import javax.swing.*;
import java.io.IOException;

/**
 * Author: Mikhail Tsvik (tsvik@me.com)
 * Date: 23.03.14
 */

public class Start {
    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException, UnsupportedLookAndFeelException, SAXException, ClassNotFoundException {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        currencyConverter.setVisible(true);
    }
}
