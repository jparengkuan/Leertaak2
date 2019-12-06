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

    public void extrapolation(String field, String Stn) {

        System.out.println("field is " + field);

        String query = "SELECT " + field.toUpperCase() + " FROM data WHERE STN = " + Stn + " ORDER BY DATE AND TIME LIMIT 30";
        System.out.println(query);

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {


            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }


    }

    public void insertData(WeatherDataModel weatherDataModel){
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
            preparedStatement.setInt(14, weatherDataModel.getWinddir());
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
