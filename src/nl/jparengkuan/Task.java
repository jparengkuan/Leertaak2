package nl.jparengkuan;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class Task implements Runnable {

    protected Socket clientSocket = null;

    public Task(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            String input;
            String buffer = "";

            while((input = in.readLine())  != null) {

                if (input.equals("\t<MEASUREMENT>")) {
                    buffer = "";
                }

                buffer = buffer + input;

                if (input.equals("\t</MEASUREMENT>")) {

                    //Create Hashmap from the buffer
                    HashMap<String, String> xmlBuffer = this.parseXML(buffer);

                    WeatherDataModel  data = weatherDataModel(xmlBuffer);

                    FileWriter.writeData(data);




                }

            }

            in.close(); // Close the stream

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, String> parseXML(String data){
        return  XMLParser.Parse(data);
    }

    public WeatherDataModel weatherDataModel(HashMap<String, String> weatherData)
    {
        WeatherDataModel weatherDataModel = new WeatherDataModel(
                weatherData.get("Stn"), weatherData.get("Date"), weatherData.get("Time"), weatherData.get("Temp"),
                weatherData.get("Dewp"), weatherData.get("Stp"), weatherData.get("SLP"),
                weatherData.get("Visib"), weatherData.get("Wdsp"), weatherData.get("Prcp"),
                weatherData.get("Sndp"), weatherData.get("Frshtt"),
                weatherData.get("Cldc"), weatherData.get("Wnddir")
        );

        return weatherDataModel;

    }
}
