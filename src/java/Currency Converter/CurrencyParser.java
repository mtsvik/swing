package ru.ifmo.enf.tsvik.t02;


/**
 * Author: Mikhail Tsvik (tsvik@me.com)
 * Date: 30.03.14
 */

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class parses the current exchange rate from the site of the European Central Bank using SAX Pareser.
 * SAX Parser provides API to parse XML documents. SAX Parsers are different than DOM parser because
 * it doesn’t load complete XML into memory and read xml document sequentially.
 * javax.xml.parsers.SAXParser provides method to parse XML document using event handlers.
 * This class implements XMLReader interface and provides overloaded versions of parse() methods
 * to read XML document from File, InputStream, SAX InputSource and String URI.
 * The actual parsing is done by the Handler class and we need to create our own
 * handler class to parse the XML document. We need to implement org.xml.sax.ContentHandler interface to create
 * our own handler classes. This interface contains callback methods that receive notification when any event occurs,
 * for example StartDocument, EndDocument, StartElement, EndElement, CharacterData etc.
 * org.xml.sax.helpers.DefaultHandler provides default implementation of ContentHandler interface and we
 * can extend this class to create our own handler. It’s advisable to extend this class because we might need
 * only few of the methods to implement. Extending this class will keep our code cleaner and maintainable.
 */

public class CurrencyParser extends DefaultHandler {
    private static final String ecbRatesURL = "http://www.ecb.int/stats/eurofxref/eurofxref-daily.xml";
    protected static final Map<String, Double> rates = new HashMap<>();

    /* If internet connection doesn't exist (UnknownHostException) used deafault xml-file. */
    public static void parse() throws SAXException, IOException {
        CurrencyParser handler = new CurrencyParser();
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(handler);
        try {
            xmlReader.parse(new InputSource(new URL(ecbRatesURL).openStream()));
        } catch (UnknownHostException e) {
            xmlReader.parse(new InputSource(CurrencyParser.class.getResourceAsStream("eurofxref-daily.xml")));
        }
        rates.put(" " + "EUR", 1d);
    }

    /* This method gets the information from the specified xml-file tags. */
    @Override
    public void startElement(String namespaceURI, String localName, String qName, org.xml.sax.Attributes atts) throws SAXException {
        if (localName.equals("Cube")) {
            String currency = atts.getValue("currency");
            String rate = atts.getValue("rate");
            if (currency != null && rate != null) rates.put(" " + currency, Double.parseDouble(rate));
        }
    }

    /* This metod returns string array of keys (currency names). */
    public static String[] getKeys() {
        return rates.keySet().toArray(new String[rates.keySet().size()]);
    }
}

