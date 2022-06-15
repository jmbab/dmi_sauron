package com.dmi_sauron.models;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* Resource-klassen er en singleton class. Det vil sige at den kun bliver instantieret
én gang (properties læses ind fra fil). Efterfølgende kan man hvor som helst i sit program
bede om en instans og bruge indholdet.
 */

public class Resources {

    static int req_file = 1,
    ok_send_file = 2,
    server_error = 3;

    private static Resources instance = null;
    private String serverList;
    private String scriptDir;
    private String logDir = null;
    private String command;
    private String dest;

    private Resources() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application.properties");
        /* This part is for required properties */
        try {
            serverList = resourceBundle.getString("ServerList");
            scriptDir = resourceBundle.getString("ScriptDir");
            command = resourceBundle.getString("command");
            dest = resourceBundle.getString("dest");
        }
        catch (MissingResourceException e) {
            System.out.println("Missing resource exception: " + e);
            System.exit(1);
        }

        /* This part is for optional properties */
        logDir = readOptionalResource(resourceBundle, "LogDir");
    }


    private String readOptionalResource(ResourceBundle bundle, String name) {
        String result = null;

        try {
            result = bundle.getString(name);
        }
        catch (MissingResourceException e) {
            // Do nothing as property are not required
        }
        return result;
    }

    // only getInstance can be called (public, as the rest is private)
    public static final Resources getInstance() {
        if (instance == null) { instance = new Resources(); }

        return instance;
    }


    public String getServerList() { return serverList; }
    public String getScriptDir() { return scriptDir; }
    public String getLogDir() { return logDir; }
    public String getCommand(){ return command; }
    public String getDest() { return dest; }

}

/* Indholdet af "NameOfPropertyFile":

        # These are required properties
        ServerList=host1,host2
        ScriptDir=/path/two
        # Optional properties
        LogDir=/path/three

        NameOfPropertyFile skal ligge i class-path eller nævnes på class-path

 */