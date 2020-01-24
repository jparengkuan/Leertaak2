package nl.jparengkuan;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileWriter {

    public static void writeData(WeatherDataModel weatherData) {
        try {
            Path currentDir = FileSystems.getDefault().getPath("");

            String path = currentDir + weatherData.getStn() + ".xml";

            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            } else {
                FileOutputStream writer = new FileOutputStream(path, true);

                Element weatherdata = new Element("WEATHERDATA");
                Document doc = new Document(weatherdata);

                Element measurement = new Element("MEASUREMENT");
                measurement.addContent(new Element("STN").setText(weatherData.getStn()));
                measurement.addContent(new Element("DATE").setText(weatherData.getDate()));
                measurement.addContent(new Element("TIME").setText(weatherData.getTime()));
                measurement.addContent(new Element("TEMP").setText(weatherData.getTemp()));
                measurement.addContent(new Element("DEWP").setText(weatherData.getDewp()));
                measurement.addContent(new Element("STP").setText(weatherData.getStp()));
                measurement.addContent(new Element("SLP").setText(weatherData.getSlp()));
                measurement.addContent(new Element("VISIB").setText(weatherData.getVisib()));
                measurement.addContent(new Element("WDSP").setText(weatherData.getWdsp()));
                measurement.addContent(new Element("PRCP").setText(weatherData.getPrcp()));
                measurement.addContent(new Element("SNDP").setText(weatherData.getSndp()));
                measurement.addContent(new Element("FRSHTT").setText(weatherData.getFrshtt()));
                measurement.addContent(new Element("CLDC").setText(weatherData.getCldc()));
                measurement.addContent(new Element("WNDDIR").setText(weatherData.getWinddir()));

                doc.getRootElement().addContent(measurement);

                XMLOutputter xmlOutput = new XMLOutputter();
                xmlOutput.setFormat(Format.getPrettyFormat());
                xmlOutput.output(doc, writer);

                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
