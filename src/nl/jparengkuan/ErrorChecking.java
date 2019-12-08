package nl.jparengkuan;

import java.util.HashMap;

public class ErrorChecking {


    public static String CheckEmptyField(HashMap<String, String> weatherData) {

        for (String fieldName : weatherData.keySet()) {

            if (weatherData.get(fieldName) == "") {
                return fieldName;
            }

        }

        return "OK";
    }

    public static boolean IsCorrectTemp(String temp, String stn){


        MySQLAccess dbHandler = MySQLAccess.getInstance();

        float currentTemp = Math.abs(Float.parseFloat(temp));

        float latestTemp = Math.abs(dbHandler.extrapolation("TEMP", stn));


        // Check if latest temp is 0.0
        // This means that there are no records in de database so we accept any temp

        if (latestTemp == 0.0)
        {
            return true;
        }

        // Check if the current temperature is correct
        // Incorrect is 20% less or more then the latest temp in database

        if ( currentTemp > latestTemp*1.2 || currentTemp < latestTemp*0.8)  {

            return false;
        }

        return true;
    }
}
