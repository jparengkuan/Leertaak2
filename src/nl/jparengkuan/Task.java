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

                    //Check for a missing field
                    String missingfield = ErrorChecking.CheckEmptyField(xmlBuffer);

                    if(missingfield.contentEquals("OK"))
                    {
                        //fields are all supplied check for error in temperature
                        if(!ErrorChecking.IsCorrectTemp(xmlBuffer.get("Temp"), xmlBuffer.get("Stn"))){

                            float correctedTemp = ErrorCorrecting.correctValue(xmlBuffer.get("Stn"), "Temp");
                            //Update the hashmap with the corrected value
                            xmlBuffer.put("Temp", Float.toString(correctedTemp));

                            //Create model and insert into database
                            WeatherDataModel  data = weatherDataModel(xmlBuffer);
                            MySQLAccess dbHandler = MySQLAccess.getInstance();
                            dbHandler.insertData(data);
                        }
                        else {
                            // all date is correct insert into database
                            WeatherDataModel  data = weatherDataModel(xmlBuffer);
                            MySQLAccess dbHandler = MySQLAccess.getInstance();
                            dbHandler.insertData(data);
                        }

                    }
                    else {
                        // Get the extrapolated value of the missing field
                         float correctedValue = ErrorCorrecting.correctValue(xmlBuffer.get("Stn"), missingfield);

                        //Update the hashmap with the corrected value
                        xmlBuffer.put(missingfield, Float.toString(correctedValue));

                        //Create model and insert into database
                        WeatherDataModel  data = weatherDataModel(xmlBuffer);
                        MySQLAccess dbHandler = MySQLAccess.getInstance();
                        dbHandler.insertData(data);

                    }


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
                weatherData.get("Stn"), weatherData.get("Date"), weatherData.get("Time"), Float.parseFloat(weatherData.get("Temp")),
                Float.parseFloat(weatherData.get("Dewp")), Float.parseFloat(weatherData.get("Stp")), Float.parseFloat(weatherData.get("SLP")),
                Float.parseFloat(weatherData.get("Visib")), Float.parseFloat(weatherData.get("Wdsp")), Float.parseFloat(weatherData.get("Prcp")),
                Float.parseFloat(weatherData.get("Sndp")), weatherData.get("Frshtt"),
                Float.parseFloat(weatherData.get("Cldc")), Float.parseFloat(weatherData.get("Wnddir"))
        );

        return weatherDataModel;

    }
}
