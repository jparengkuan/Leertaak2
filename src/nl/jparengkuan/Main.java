package nl.jparengkuan;


public class Main {

    public static void main(String[] args) {

        MySQLAccess database = MySQLAccess.getInstance();

        MultiThreadedServer mtserver = new MultiThreadedServer(7789, 10);
        new Thread(mtserver).start();

    }

}
