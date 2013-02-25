/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensimkit.utilities;

import java.util.ArrayList;

/**
 *
 * @author ahmedmaawy
 */
public class Messages {

    ArrayList<String> messages = new ArrayList<String>();

    /**
     * Constructor
     *
     * @param textToProcess
     */
    public Messages(String textToProcess) {
        String[] splitString = textToProcess.split("\n");
        int elementCount = splitString.length;

        for (int i = 0; i < elementCount; i++) {
            if (!splitString[i].contains("+CMGL")
                    && !splitString[i].trim().equals("OK")
                    && !splitString[i].trim().equals("")) {
                messages.add(splitString[i].trim());
            }
        }
    }

    /**
     * Get messages
     *
     * @return
     */
    public ArrayList<String> getMessages() {
        return messages;
    }
}
