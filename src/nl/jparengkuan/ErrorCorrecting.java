package nl.jparengkuan;

public class ErrorCorrecting {

    public static float correctValue(String Stn, String fieldName){
        MySQLAccess dbHandler = MySQLAccess.getInstance();
        return dbHandler.extrapolation(fieldName, Stn);
    }
}
