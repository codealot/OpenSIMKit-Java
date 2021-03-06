/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensimkit;

import org.opensimkit.utilities.SerialPorts;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmedmaawy
 */
public class SettingsFrame extends javax.swing.JFrame {

    /**
     * Creates new form SettingsFrame
     */
    SerialPorts serialPorts;
    ArrayList<String> serialPortList;

    public SettingsFrame() {
        initComponents();

        if (OpenSIMKit.serialPorts != null) {
            serialPorts = OpenSIMKit.serialPorts;

            if (serialPorts.isConnected()) {
                jButtonConnect.setText("Disconnect");
            }
        } else {
            serialPorts = new SerialPorts();
        }

        serialPortList = serialPorts.getSerialPortList();
        int numPorts = serialPortList.size();

        for (int portLoop = 0; portLoop < numPorts; portLoop++) {
            jComboBoxPorts.addItem(serialPortList.get(portLoop));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneSettings = new javax.swing.JTabbedPane();
        jPanelAppSettings = new javax.swing.JPanel();
        jPanelSettingsLeft = new javax.swing.JPanel();
        jLabelBaudRate = new javax.swing.JLabel();
        jComboBoxBaudRate = new javax.swing.JComboBox();
        jLabelDataBits = new javax.swing.JLabel();
        jComboBoxDataBits = new javax.swing.JComboBox();
        jLabelParity = new javax.swing.JLabel();
        jComboBoxParity = new javax.swing.JComboBox();
        jLabelStopBits = new javax.swing.JLabel();
        jComboBoxStopBits = new javax.swing.JComboBox();
        jComboBoxPorts = new javax.swing.JComboBox();
        jLabelPort = new javax.swing.JLabel();
        jButtonConnect = new javax.swing.JButton();
        jPanelAdvanced = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");

        jPanelSettingsLeft.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelBaudRate.setText("Baud Rate");

        jComboBoxBaudRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "75", "110", "300", "1200", "2400", "4800", "9600", "19200", "38400", "57600", "115200" }));
        jComboBoxBaudRate.setSelectedIndex(6);

        jLabelDataBits.setText("Data Bits");

        jComboBoxDataBits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "6", "7", "8", "9" }));
        jComboBoxDataBits.setSelectedIndex(3);

        jLabelParity.setText("Parity");

        jComboBoxParity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "Odd", "Even", "Mark", "Space" }));

        jLabelStopBits.setText("Stop Bits");

        jComboBoxStopBits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "1.5", "2" }));

        org.jdesktop.layout.GroupLayout jPanelSettingsLeftLayout = new org.jdesktop.layout.GroupLayout(jPanelSettingsLeft);
        jPanelSettingsLeft.setLayout(jPanelSettingsLeftLayout);
        jPanelSettingsLeftLayout.setHorizontalGroup(
            jPanelSettingsLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelSettingsLeftLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelSettingsLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jComboBoxBaudRate, 0, 228, Short.MAX_VALUE)
                    .add(jComboBoxDataBits, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jComboBoxParity, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanelSettingsLeftLayout.createSequentialGroup()
                        .add(jPanelSettingsLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelBaudRate)
                            .add(jLabelDataBits)
                            .add(jLabelParity)
                            .add(jLabelStopBits))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jComboBoxStopBits, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSettingsLeftLayout.setVerticalGroup(
            jPanelSettingsLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelSettingsLeftLayout.createSequentialGroup()
                .add(32, 32, 32)
                .add(jLabelBaudRate)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jComboBoxBaudRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelDataBits)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jComboBoxDataBits, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelParity)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jComboBoxParity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelStopBits)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 7, Short.MAX_VALUE)
                .add(jComboBoxStopBits, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelPort.setText("Port");

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanelAppSettingsLayout = new org.jdesktop.layout.GroupLayout(jPanelAppSettings);
        jPanelAppSettings.setLayout(jPanelAppSettingsLayout);
        jPanelAppSettingsLayout.setHorizontalGroup(
            jPanelAppSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelAppSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelSettingsLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelAppSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelAppSettingsLayout.createSequentialGroup()
                        .add(jPanelAppSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jComboBoxPorts, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanelAppSettingsLayout.createSequentialGroup()
                                .add(jLabelPort)
                                .add(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelAppSettingsLayout.createSequentialGroup()
                        .add(0, 177, Short.MAX_VALUE)
                        .add(jButtonConnect))))
        );
        jPanelAppSettingsLayout.setVerticalGroup(
            jPanelAppSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelAppSettingsLayout.createSequentialGroup()
                .add(jPanelAppSettingsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanelAppSettingsLayout.createSequentialGroup()
                        .add(36, 36, 36)
                        .add(jLabelPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBoxPorts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButtonConnect))
                    .add(jPanelAppSettingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanelSettingsLeft, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPaneSettings.addTab("Settings", jPanelAppSettings);

        org.jdesktop.layout.GroupLayout jPanelAdvancedLayout = new org.jdesktop.layout.GroupLayout(jPanelAdvanced);
        jPanelAdvanced.setLayout(jPanelAdvancedLayout);
        jPanelAdvancedLayout.setHorizontalGroup(
            jPanelAdvancedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 529, Short.MAX_VALUE)
        );
        jPanelAdvancedLayout.setVerticalGroup(
            jPanelAdvancedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 269, Short.MAX_VALUE)
        );

        jTabbedPaneSettings.addTab("Advanced", jPanelAdvanced);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPaneSettings)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPaneSettings)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
        // TODO add your handling code here:

        if (!serialPorts.isConnected()) {
            int selectedOption = jComboBoxPorts.getSelectedIndex();

            if (!serialPorts.connect(selectedOption)) {
                JOptionPane.showMessageDialog(this, "Unable to connect to the port");
            } else {
                serialPorts.setParameters(Long.parseLong(jComboBoxBaudRate.getSelectedItem().toString()),
                        jComboBoxDataBits.getSelectedItem().toString(),
                        jComboBoxStopBits.getSelectedItem().toString(),
                        jComboBoxParity.getSelectedItem().toString());

                JOptionPane.showMessageDialog(this, "Connected to the port");
                jButtonConnect.setText("Disconnect");
                OpenSIMKit.serialPorts = serialPorts;
                OpenSIMKit.mainFrame.setConnectedInterface();

                this.dispose();
            }
        } else {
            OpenSIMKit.serialPorts = null;
            serialPorts.disconnect();
            OpenSIMKit.mainFrame.setDisconnectedInterface();

            this.dispose();
        }

    }//GEN-LAST:event_jButtonConnectActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JComboBox jComboBoxBaudRate;
    private javax.swing.JComboBox jComboBoxDataBits;
    private javax.swing.JComboBox jComboBoxParity;
    private javax.swing.JComboBox jComboBoxPorts;
    private javax.swing.JComboBox jComboBoxStopBits;
    private javax.swing.JLabel jLabelBaudRate;
    private javax.swing.JLabel jLabelDataBits;
    private javax.swing.JLabel jLabelParity;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JLabel jLabelStopBits;
    private javax.swing.JPanel jPanelAdvanced;
    private javax.swing.JPanel jPanelAppSettings;
    private javax.swing.JPanel jPanelSettingsLeft;
    private javax.swing.JTabbedPane jTabbedPaneSettings;
    // End of variables declaration//GEN-END:variables
}
