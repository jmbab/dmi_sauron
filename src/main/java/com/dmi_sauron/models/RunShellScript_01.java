/*Instantier RunScript med den shell kommando og eventuelle argumenter,
 * der skal udføres, dernæst kør scriptet ved at kalde run. */

package com.dmi_sauron.models;

import java.io.IOException;

public class RunShellScript_01 {

    private String[] cmdline;
    private boolean finished;
    private Resources props;

    /* If a command with no arguments is to be executed, just pass
     * a String with the command name to exec. And if there are more
     * than 1 arguments, just expand cmdline */

    /* First argument: path to script file, Second argument: destination folder path for json file*/

    // erklæring / constructor (med datatype)
    public RunShellScript_01(String command, String dest) {
        props = Resources.getInstance();

        if (dest == null) {
            cmdline = new String[1];
        } else {
            cmdline = new String[2];
            cmdline[1] = dest;
        }
        cmdline[0] = command;

        finished = false;
    }

    /* No information can be passed when starting the thread, so all information
     * has to be set up in advance */

    public void run() {
        finished = false;
        try {
            Process ps = Runtime.getRuntime().exec(cmdline);
            ps.waitFor(); // This will wait for the external script to terminate
        } catch (IOException e) {
            System.out.println("Error executing batch script: " + e);
            return;
        } catch (InterruptedException e) {
            System.out.println("Script execution interrupted: " + e);
        }
        finished = true;
    }
}
