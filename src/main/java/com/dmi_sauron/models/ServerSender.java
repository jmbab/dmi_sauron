package com.dmi_sauron.models;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSender {

    private static Socket clientSocket = null;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void initServerSocket() {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Listening to port:5000");
            clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");

        } catch (Exception e) {
            e.printStackTrace();
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

    }

    public static void main(String[] args) {

        // henvisning til singleton klasse for at hente variabler: command + dest
        Resources resources = Resources.getInstance();

        // Kører uendeligt (nødvendigt): serveren lytter altid efter nye anmodninger (requests)
        while (true) {

            // metode der starter server (uendeligt: daemon kører i loop)
            initServerSocket();

            try {
                // First while loop used to create a new socket everytime the client accepts a connection
                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

                // loop for at vente og lytte efter request / kommando på InputStream fra ClientReceiver

                // boolean verificering: er det en kommando jeg kender? Er det en lovlig kommando eller noget farligt?

                if (dataInputStream == null){
                    System.out.println("Socket is null");
                    dataInputStream.close();
                    dataOutputStream.close();
                    clientSocket.close();
                    System.exit(0);
                } else {
                    System.out.println("Socket OK");
                }

                // Second while loop used to communicate back and forth
                // while (clientSocket.isConnected()){

                // "magic" comment above the statement to suppress warnings about the infinite loop
                //noinspection InfiniteLoopStatement
                while (true) {

                    // vi læser direkte vores int fra klienten
                    int commandNum = dataInputStream.readInt();
                    // static int req_file = 1 (in Resources class)
                    System.out.println("Integer response from client: " + commandNum);

                    if (commandNum == Resources.req_file) {
                        // static int ok_send_file = 2 (in Resources class)
                        dataOutputStream.writeInt(Resources.ok_send_file);

                        // Genrerer json filen gennem bash shell script. OBS: resources.getDest() erstatter ${1} i scriptet
                        RunShellScript_01 jsonGen = new RunShellScript_01(resources.getCommand(), resources.getDest());
                        System.out.println("command: " + resources.getCommand() + " dest: " + resources.getDest());
                        // instans af RunShellScript kører scriptet og genererer en JSON fil
                        jsonGen.run();
                        // Sender JSON filen fra Server til Client
                        sendFile(resources.getDest() + "/" + "serverinfo.json");

                    } else {
                        // static int server_error = 3 (in Resources class)
                        dataOutputStream.writeInt(Resources.server_error);
                    }
                    dataOutputStream.flush();

                }

            } catch (EOFException e) {

                /* EOFException: End Of File Exception --> Signals that an end of file or end of stream has been reached unexpectedly during input.
                This exception is mainly used by data input streams to signal end of stream.
                Note that many other input operations return a special value on end of stream rather than throwing an exception.
                */
                System.out.println("End of file reached: " + e);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}

