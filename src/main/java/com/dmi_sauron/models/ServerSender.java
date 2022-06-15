package com.dmi_sauron.models;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// START SECOND

public class ServerSender {

    private static Socket clientSocket = null;
    private static InputStreamReader inputStreamReader = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) throws IOException {

        // metode der starter server (uendeligt: daemon kører i loop)
        initServerSocket();

        // henvisning til singleton klasse for at hente command og dest variabler
        Resources resources = Resources.getInstance();

        while (true) {

            try {
                // First while loop used to create a new socket everytime the client accepts a connection

                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                // loop for at vente og lytte efter request / kommando på InputStream fra ClientReceiver

                // boolean verificering: er det en kommando jeg kender? Er det en lovlig kommando eller noget farligt?

                // Second while loop used to communicate back and forth
                while (true) {

                    // vi læser direkte vores int fra klienten
                    int commandNum = dataInputStream.readInt();

                    if (commandNum == Resources.req_file) {
                        dataOutputStream.writeInt(Resources.ok_send_file);
                        // Genrerer json filen gennem bash shell script. OBS: resources.getDest() erstatter ${1} i scriptet
                        RunShellScript_01 jsonGen = new RunShellScript_01(resources.getCommand(), resources.getDest());
                        jsonGen.run();
                        // Sender filen
                        sendFile(resources.getDest() + "/" + "serverinfo.json");
                    } else {
                        dataOutputStream.writeInt(Resources.server_error);
                    }
                    dataOutputStream.flush();
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }

    }
    private static void sendFile(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // send file size
        dataOutputStream.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[16 * 1024];
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
        clientSocket.close();
        inputStreamReader.close();
        outputStreamWriter.close();
        bufferedReader.close();
        bufferedWriter.close();
        dataInputStream.close();
        dataInputStream.close();
    }
    public static void initServerSocket() {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");

//          dataInputStream = new DataInputStream(clientSocket.getInputStream());
//          dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

}

