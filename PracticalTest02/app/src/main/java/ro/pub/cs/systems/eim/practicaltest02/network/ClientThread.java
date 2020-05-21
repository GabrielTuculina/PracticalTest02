package ro.pub.cs.systems.eim.practicaltest02.network;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practicaltest02.general.Utilities;

public class ClientThread extends Thread {
    private String address;
    private int port;
    private String hour;
    private String minute;
    private String informationType;
    private TextView weatherForecastTextView;

    private Socket socket;

    public ClientThread(String address, int port, String hour, String minute, String informationType, TextView weatherForecastTextView) {
        this.address = address;
        this.port = port;
        this.hour = hour;
        this.minute = minute;
        this.informationType = informationType;
        this.weatherForecastTextView = weatherForecastTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                return;
            }
            printWriter.println(hour);
            printWriter.println(minute);
            printWriter.flush();
            printWriter.println(informationType);
            printWriter.flush();
            String weatherInformation;
            while ((weatherInformation = bufferedReader.readLine()) != null) {
                final String finalizedWeateherInformation = weatherInformation;
                weatherForecastTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        weatherForecastTextView.setText(finalizedWeateherInformation);
                    }
                });
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
