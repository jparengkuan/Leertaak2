package nl.jparengkuan;


public class Main {

    public static void main(String[] args) {


        MultiThreadedServer mtserver = new MultiThreadedServer(7789, 100);
        new Thread(mtserver).start();

    }

}
