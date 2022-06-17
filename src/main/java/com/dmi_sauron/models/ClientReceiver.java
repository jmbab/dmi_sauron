package com.dmi_sauron.models;

import java.io.*;
import java.net.Socket;

public class ClientReceiver {

    private static Socket clientSocket = null;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        // Mulighed for en for each loop over host / server fra String Array (kun med hostnames, portnummer skifter ikke) eller vector string med iterator / collection
        requestServerFile("localhost", 5000);

        // + evt. andre funktionaliter i fremtiden
    }

    private static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        Resources resources = Resources.getInstance();
        // fileName skal indeholde den fulde sti (full path)
        FileOutputStream fileOutputStream = new FileOutputStream(resources.getDest() + "/" + fileName);

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[16 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }

    private static void requestServerFile(String hostname, int portnummer){

        // ArrayList jsonArrayList = new ArrayList<>();

        try {

            // Loop gennem alle servere fra serverliste (med while loop? lukke forbindelse hver gang?)
            Resources resources = Resources.getInstance();
            String serverListString = resources.getServerList();
            String[] serverList = serverListString.split(",");
            for (String server: serverList) {
                // System.out.println(server);
                hostname = server;
            }

            // Socket socket = new Socket("hostname", port number);
            clientSocket = new Socket(hostname, portnummer);

/*            inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            // To improve efficiency, we use a buffer method (send by blocks instead of character by character)
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);*/

            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Send tal der siger "giv mig en fil" og lytte efter json fil på InputStream

            dataOutputStream.writeInt(Resources.req_file);
//          bufferedWriter.newLine();
            dataOutputStream.flush();

            // Waiting for server response and print it
            int response = dataInputStream.readInt();

            if (response == Resources.ok_send_file){
                System.out.println("Receiving JSON file.");
                // Client bestemmer filnavnet herunder
                receiveFile(hostname + "_serverinfo.json");
            } else if (response == Resources.server_error){
                System.out.println("Server error.");
            } else {
                System.out.println("Unknown server response: " + response);
            }

            // JSONObject jsonObj = ...
            // jsonArrayList.add(jsonObj);

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}

