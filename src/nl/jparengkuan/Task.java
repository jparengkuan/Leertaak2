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

                   ErrorChecking.test(this.parseXML(buffer));
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
}
