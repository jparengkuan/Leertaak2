package nl.jparengkuan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;

    protected ExecutorService service;

    public MultiThreadedServer(int port, int nThreads) {
        this.serverPort = port;
        this.service = Executors.newFixedThreadPool(nThreads);
    }

    @Override
    public void run() {
        // synchronize the action of multiple threads and make sure that only one thread
        // can access the resource at a given point in time
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }

        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server stopped!");
                    return;
                }

                throw new RuntimeException("Error accepting client connection!", e);
            }


            this.service.execute(new Task(clientSocket));

        }


    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }


    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open ServerSocket on port: " + this.serverPort, e);
        }
    }
}
