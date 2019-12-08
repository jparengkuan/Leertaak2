package nl.jparengkuan;

import java.sql.*;

public class MySQLAccess {

    private static MySQLAccess single_instance = null;
    public Connection connection = null;

    private MySQLAccess() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connect();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

    }

    private void connect() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unwdmi?user=root");
    }

    public synchronized float extrapolation(String field, String Stn) {



        field = field.toUpperCase();

        String query = "SELECT CONVERT(CONCAT(DATE, \" \", TIME), DATETIME) as DATE, "+field+" AS VALUE FROM DATA WHERE STN = "+Stn+" ORDER BY DATE DESC LIMIT 1";


        float value = 0;

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {


            ResultSet resultSet = preparedStatement.executeQuery();



            while(resultSet.next()) {

               value = resultSet.getFloat("VALUE");

            }



        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return value;

    }

    public synchronized void insertData(WeatherDataModel weatherDataModel){
        String query = "INSERT INTO data (STN, DATE, TIME, TEMP, DEWP, STP, SLP, VISIB, WDSP, PRCP, SNDP, FRSHTT, CLDC, WNDDIR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {

            preparedStatement.setInt(1, weatherDataModel.getStn());
            preparedStatement.setDate(2, weatherDataModel.getDate());
            preparedStatement.setTime(3, weatherDataModel.getTime());
            preparedStatement.setFloat(4, weatherDataModel.getTemp());
            preparedStatement.setFloat(5, weatherDataModel.getDewp());
            preparedStatement.setFloat(6, weatherDataModel.getStp());
            preparedStatement.setFloat(7, weatherDataModel.getSlp());
            preparedStatement.setFloat(8, weatherDataModel.getVisib());
            preparedStatement.setFloat(9, weatherDataModel.getWdsp());
            preparedStatement.setFloat(10, weatherDataModel.getPrcp());
            preparedStatement.setFloat(11, weatherDataModel.getSndp());
            preparedStatement.setInt(12, weatherDataModel.getFrshtt());
            preparedStatement.setFloat(13, weatherDataModel.getCldc());
            preparedStatement.setFloat(14, weatherDataModel.getWinddir());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

    }

    public static MySQLAccess getInstance()
    {
        if (single_instance == null )
            single_instance = new MySQLAccess();

        return single_instance;
    }
}
