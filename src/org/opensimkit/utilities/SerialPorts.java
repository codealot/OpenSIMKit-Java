/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensimkit.utilities;

/**
 *
 * @author ahmedmaawy
 */
import purejavacomm.CommPortIdentifier;
import purejavacomm.PortInUseException;
import purejavacomm.SerialPort;
import purejavacomm.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opensimkit.OpenSIMKit;
import purejavacomm.SerialPortEventListener;

public class SerialPorts {

    private ArrayList<CommPortIdentifier> serialPorts;
    private ArrayList<String> serialPortList;
    private SerialPort serialPort;
    private boolean connected = false;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedReader bufferedReader;
    private PrintStream printStream;
    // Returned by the event handler
    public static String serialPortReturnValue;
    // Constants
    final char CTRL_Z = (char) 26;
    final String CMD_CHECK_CONNECTION = "AT\r\n";
    final String CMD_SET_TEXT_MODE_FORMAT = "AT+CMGF=1\r\n";
    final String CMD_CHAR_SET_PCCP347 = "AT+CSCS=\"PCCP437\"\r\n";
    final String CMD_SELECT_SIM_STORAGE = "AT+CPMS=\"SM\"\r\n";
    final String CMD_READ_ALL_MESSAGES = "AT+CMGL=\"ALL\"\r\n";
    final String CMD_READ_MESSAGE = "AT+CMGR={{ message_index }}\r\n";
    final String CMD_DETAILED_ERRORS = "AT+CMEE=1";
    final String CMD_DELETE_ALL_MESSAGES = "AT+CMGD=1,4\r\n";
    // Full write command
    final String CMD_WRITE_MESSAGE_TO_MEMORY = "AT+CMGW=\"{{ message_contact }}\"\r{{ message }}";
    // Parial write command
    final String CMD_REQUEST_WRITE_MESSAGE = "AT+CMGW=\"{{ message_contact }}\"\r\n";
    final String CMD_DO_WRITE_AFTER_REQUEST = "{{ message }}";

    /**
     * Constructor
     */
    public SerialPorts() {
        serialPortList = new ArrayList<String>();
        serialPorts = new ArrayList<CommPortIdentifier>();

        Enumeration portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            CommPortIdentifier cpi = (CommPortIdentifier) portList.nextElement();

            if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                serialPorts.add(cpi);
                serialPortList.add(cpi.getName());
            } else if (cpi.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
                // Do nothing at the moment
            } else {
                // Unknown port
            }
        }
    }

    /**
     * Synchronized variable access
     *
     * @param value
     */
    public static synchronized void setSerialPortReturnValue(String value) {
        serialPortReturnValue = value;
    }

    /**
     * Set the serial port parameters
     *
     * @param speed
     * @param dataBits
     * @param stopBits
     * @param parity
     * @return boolean
     */
    public boolean setParameters(long speed, String dataBits, String stopBits, String parity) {
        try {
            int speedValue;
            int dataBitValue;
            int stopBitValue;
            int parityValue;


            // Baud rate

            if (speed == 75) {
                speedValue = 75;
            } else if (speed == 110) {
                speedValue = 110;
            } else if (speed == 300) {
                speedValue = 300;
            } else if (speed == 1200) {
                speedValue = 1200;
            } else if (speed == 4800) {
                speedValue = 4800;
            } else if (speed == 19200) {
                speedValue = 19200;
            } else if (speed == 38400) {
                speedValue = 38400;
            } else if (speed == 57600) {
                speedValue = 57600;
            } else if (speed == 115200) {
                speedValue = 115200;
            } else {
                speedValue = 9600;
            }

            // Data bits

            if (dataBits.equals("5")) {
                dataBitValue = SerialPort.DATABITS_5;
            } else if (dataBits.equals("6")) {
                dataBitValue = SerialPort.DATABITS_6;
            } else if (dataBits.equals("7")) {
                dataBitValue = SerialPort.DATABITS_7;
            } else {
                dataBitValue = SerialPort.DATABITS_8;
            }

            // Stop bits

            if (stopBits.equals("1.5")) {
                stopBitValue = SerialPort.STOPBITS_1_5;
            } else if (stopBits.equals("2")) {
                stopBitValue = SerialPort.STOPBITS_2;
            } else {
                stopBitValue = SerialPort.STOPBITS_1;
            }

            // Parity

            if (parity.equals("Mark")) {
                parityValue = SerialPort.PARITY_MARK;
            } else if (parity.equals("Odd")) {
                parityValue = SerialPort.PARITY_ODD;
            } else if (parity.equals("Even")) {
                parityValue = SerialPort.PARITY_EVEN;
            } else if (parity.equals("Space")) {
                parityValue = SerialPort.PARITY_SPACE;
            } else {
                parityValue = SerialPort.PARITY_NONE;
            }

            serialPort.setSerialPortParams(speedValue, dataBitValue, stopBitValue, parityValue);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }

        return true;
    }

    /**
     * Connect to a serial port
     *
     * @param portIndex
     *
     * @return boolean
     */
    public boolean connect(int port) {
        if (!connected) {
            try {
                serialPort = (SerialPort) serialPorts.get(port).open("OpenSIMKit", 2000);
                try {
                    serialPort.setSerialPortParams(9600,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);

                    serialPort.addEventListener((SerialPortEventListener) new SerialPortListener());
                    serialPort.notifyOnDataAvailable(true);
                    serialPort.notifyOnOutputEmpty(true);

                    inputStream = serialPort.getInputStream();
                    outputStream = serialPort.getOutputStream();

                    bufferedReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                    printStream = new PrintStream(serialPort.getOutputStream(), true);

                    setTextFormat();
                    setMemSIMCard();
                } catch (UnsupportedCommOperationException ex) {
                    connected = false;
                    Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

                } catch (TooManyListenersException ex) {
                    connected = false;
                    Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);
                    return false;

                } catch (IOException ex) {
                    connected = false;
                    Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            } catch (PortInUseException ex) {
                connected = false;
                Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

            connected = true;
        } else {
            return false;
        }
        return true;
    }

    /**
     * Auto connect to an open port
     *
     * @return
     */
    public boolean autoConnect() {
        OpenSIMKit.serialPorts = null;
        String pattern = "";

        String osName = System.getProperty("os.name").toLowerCase();
        OSUtilities osUtilities = new OSUtilities(osName);

        if (osUtilities.isWindows()) {
            pattern = "COM";
        } else if (osUtilities.isMac()) {
            pattern = "tty.usb";
        } else if (osUtilities.isUnix()) {
            pattern = "ttyUSB";
        } else if (osUtilities.isSolaris()) {
            return false;
        }

        //int currentPortIndex = 0;
        for (int port = 0; port < serialPortList.size(); port++) {
            // Valid candidate to connect to ?
            if (serialPortList.get(port).contains(pattern)) {
                if (connect(port)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Disconnect the port
     *
     * @return boolean
     */
    public boolean disconnect() {
        // TODO: Investigate why an exception occurs when closing the port
        if (connected) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        serialPort.getInputStream().close();
                        serialPort.getOutputStream().close();
                    } catch (IOException ex) {
                        Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    serialPort.removeEventListener();
                    serialPort.close();
                }
            }.start();
        }
        return true;
    }

    private String readPort() {
        String output = "";
        String readLine = "";

        try {
            while ((readLine = bufferedReader.readLine()) != null) {
                output = output.concat("\r\n" + readLine); // add the "output = "
            }
        } catch (IOException ex) {
            Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return output;
    }

    /**
     * Sets the format to text format
     */
    private boolean setTextFormat() {
        printStream.print(CMD_SET_TEXT_MODE_FORMAT);
        return true;
    }

    /**
     * Sets the storage to SIM card storage
     */
    private boolean setMemSIMCard() {
        printStream.print(CMD_SELECT_SIM_STORAGE);
        return true;
    }

    /**
     * Runs a custom command
     *
     * @param command
     * @return
     */
    private void execute(String command) {
        serialPortReturnValue = "";
        printStream.print(command);
    }

    /**
     * Wait for output
     */
    private String waitForOutput() {
        int numAttempts = 5;
        int currentAttempt = 1;

        try {
            while (serialPortReturnValue.trim().equals("") && currentAttempt < numAttempts) {
                Thread.sleep(500);
                currentAttempt++;
            }

            if (currentAttempt == numAttempts && serialPortReturnValue.trim().equals("")) {
                return null;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }

        return serialPortReturnValue.trim();
    }

    /**
     * Get all messages in the system
     *
     * @return String
     */
    public String getAllMessages() {
        execute(CMD_READ_ALL_MESSAGES);

        String result = waitForOutput();
        return result;
    }

    /**
     * Save a message
     *
     * @param message
     * @return
     */
    public boolean saveMessage(String contact, String message) {
        String requestWriteCommand = CMD_REQUEST_WRITE_MESSAGE.replace("{{ message_contact }}", contact);
        execute(requestWriteCommand);

        String result = waitForOutput();

        if (result.contains(">")) {
            String messageToWrite = (CMD_DO_WRITE_AFTER_REQUEST.replace("{{ message }}", message)).concat(String.valueOf(CTRL_Z));
            execute(messageToWrite);

            result = waitForOutput();

            if (result.contains("ERROR")) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Save a number of messages to the SIM Card
     *
     * @param contact
     * @param messages
     * @param clearAll
     * @return
     */
    public boolean saveMessages(String contact, ArrayList<String> messages, boolean clearAll) {
        String result = "";

        if (clearAll) {
            execute(CMD_DELETE_ALL_MESSAGES);
            result = waitForOutput();

            if (result == null || result.contains("ERROR")) {
                return false;
            }
        }

        int msgSize = messages.size();

        for (int index = 0; index < msgSize; index++) {
            // Rule out empty messages
            if (!messages.get(index).trim().equals("")) {
                // Save message
                if (!saveMessage(contact, messages.get(index))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * getSerialPortList()
     *
     * @return List of Serial Port Names
     */
    public ArrayList<String> getSerialPortList() {
        return serialPortList;
    }

    /**
     * Checks to see if the serial port is connected
     *
     * @return
     */
    public boolean isConnected() {
        return connected;
    }
}