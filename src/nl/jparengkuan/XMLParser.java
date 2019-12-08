package nl.jparengkuan;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

public class XMLParser {

    public static HashMap<String, String> Parse(String data){

        org.jdom2.input.SAXBuilder saxBuilder = new SAXBuilder();

        try {
            org.jdom2.Document doc = saxBuilder.build(new StringReader(data));

            Element root = doc.getRootElement();

            HashMap<String, String> weatherData = new HashMap<String, String>();

            weatherData.put("Stn", root.getChild("STN").getText());
            weatherData.put("Date", root.getChild("DATE").getText());
            weatherData.put("Time", root.getChild("TIME").getText());
            weatherData.put("Temp", root.getChild("TEMP").getText());
            weatherData.put("Dewp", root.getChild("DEWP").getText());
            weatherData.put("Stp", root.getChild("STP").getText());
            weatherData.put("SLP", root.getChild("SLP").getText());
            weatherData.put("Visib", root.getChild("VISIB").getText());
            weatherData.put("Wdsp", root.getChild("WDSP").getText());
            weatherData.put("Prcp", root.getChild("PRCP").getText());
            weatherData.put("Sndp", root.getChild("SNDP").getText());
            weatherData.put("Frshtt", root.getChild("FRSHTT").getText());
            weatherData.put("Cldc", root.getChild("CLDC").getText());
            weatherData.put("Wnddir", root.getChild("WNDDIR").getText());

            return weatherData;

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
