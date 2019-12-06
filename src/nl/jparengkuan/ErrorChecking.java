package nl.jparengkuan;

import java.util.HashMap;

public class ErrorChecking {

    public static synchronized void test(HashMap<String, String> weatherData){

        Boolean missingValue = false;
        MySQLAccess dbHandler = MySQLAccess.getInstance();

        for (String i : weatherData.keySet()) {

            // i is the fieldname

            if (weatherData.get(i) == ""){
                missingValue = true;
                System.out.println( i + " ----> " + weatherData.get(i));
                dbHandler.extrapolation(i, weatherData.get("Stn"));
            }

        }

        if (!missingValue){
            WeatherDataModel weatherDataModel = new WeatherDataModel(
                    weatherData.get("Stn"), weatherData.get("Date"), weatherData.get("Time"), Float.parseFloat(weatherData.get("Temp")),
                    Float.parseFloat(weatherData.get("Dewp")), Float.parseFloat(weatherData.get("Stp")), Float.parseFloat(weatherData.get("SLP")),
                    Float.parseFloat(weatherData.get("Visib")), Float.parseFloat(weatherData.get("Wdsp")), Float.parseFloat(weatherData.get("Prcp")),
                    Float.parseFloat(weatherData.get("Sndp")), weatherData.get("Frshtt"),
                    Float.parseFloat(weatherData.get("Cldc")), Integer.parseInt(weatherData.get("Wnddir"))
            );
           // System.out.println(weatherDataModel.toString());
            //dbHandler.insertData(weatherDataModel);
        }







    }
}
