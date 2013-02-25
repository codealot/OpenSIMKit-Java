/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensimkit;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opensimkit.utilities.Messages;

/**
 *
 * @author ahmedmaawy
 */
public final class MainFrame extends javax.swing.JFrame {
    
    private PermissionsPanel permissionsPanel = new PermissionsPanel();
    private ConnectedPanel connectedPanel = new ConnectedPanel();
    private DisconnectedPanel disconnectedPanel = new DisconnectedPanel();
    
    private boolean dataCollectionInterfaceConstructed = false;
    private boolean connectedInterfaceConstructed = false;
    private boolean disconnectedInterfaceConstructed = false;
    
    private final String openSIMKitUrl = "http://opensimkit.com/";
    private final String gitHubUrl = "https://github.com/Abayima/OpenSIMKit-Java";
    private final String twitterUrl = "https://twitter.com/opensimkit";
    
    /**
     * Creates new form MainJFrame
     */
    
    public MainFrame() {
        initComponents();
        
        if(OpenSIMKit.anonymousDataCollection.isSettingsFileExists()) {
            setDisconnectedInterface();
        }
        else {
            setAnonymousDataCollectionInterface();
        }
    }
    
    /**
     * Anonymous data collection panel
     */
    
    public void setAnonymousDataCollectionInterface()
    {
        
        stackPanel.removeAll();

        org.jdesktop.layout.GroupLayout jPanelBottomRightLayout = new org.jdesktop.layout.GroupLayout(stackPanel);
        stackPanel.setLayout(jPanelBottomRightLayout);

        jPanelBottomRightLayout.setHorizontalGroup(
            jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBottomRightLayout.createSequentialGroup()
                .add(permissionsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanelBottomRightLayout.setVerticalGroup(
            jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBottomRightLayout.createSequentialGroup()
                .add(jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(permissionsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );

        dataCollectionInterfaceConstructed = true;
        
        permissionsPanel.setVisible(true);
        disconnectedPanel.setVisible(false);
        connectedPanel.setVisible(false);
    }
    
    /**
     * Change the interface to a connected one
     */
    
    public void setConnectedInterface()
    {
        // Contruct interface
        
        
        stackPanel.removeAll();

        org.jdesktop.layout.GroupLayout jPanelBottomRightLayout = new org.jdesktop.layout.GroupLayout(stackPanel);
        stackPanel.setLayout(jPanelBottomRightLayout);

        jPanelBottomRightLayout.setHorizontalGroup(
            jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBottomRightLayout.createSequentialGroup()
                .add(connectedPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanelBottomRightLayout.setVerticalGroup(
            jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBottomRightLayout.createSequentialGroup()
                .add(jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(connectedPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );

        connectedInterfaceConstructed = true;
        
        // Toggle interface
        
        permissionsPanel.setVisible(false);
        disconnectedPanel.setVisible(false);
        connectedPanel.setVisible(true);
        
        // Read all messages from SIM Card
        
        if(OpenSIMKit.serialPorts.isConnected())
        {
            String allMessages = OpenSIMKit.serialPorts.getAllMessages();
            Messages messages = new Messages(allMessages);
            connectedPanel.getMessagesPanel().setItems(messages.getMessages());
        }
    }
    
    /**
     * Change the interface to a disconnected one
     */
    
    public void setDisconnectedInterface()
    {
        
        // Construct interface
            
        stackPanel.removeAll();

        org.jdesktop.layout.GroupLayout jPanelBottomRightLayout = new org.jdesktop.layout.GroupLayout(stackPanel);
        stackPanel.setLayout(jPanelBottomRightLayout);

        jPanelBottomRightLayout.setHorizontalGroup(
            jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBottomRightLayout.createSequentialGroup()
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(disconnectedPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 709, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanelBottomRightLayout.setVerticalGroup(
            jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelBottomRightLayout.createSequentialGroup()
                .add(jPanelBottomRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(disconnectedPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 512, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );

        disconnectedInterfaceConstructed = true;
        
        // Toggle interface
        
        permissionsPanel.setVisible(false);
        connectedPanel.setVisible(false);
        disconnectedPanel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonTwitter1 = new javax.swing.JButton();
        headPanel = new javax.swing.JPanel();
        btnConnectTwitter = new javax.swing.JButton();
        btnConnectGitHub = new javax.swing.JButton();
        btnConnectSIMKit = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButton1 = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(6, 0), new java.awt.Dimension(6, 0), new java.awt.Dimension(6, 32767));
        mainPanel = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        jPanel3 = new javax.swing.JPanel();
        jLabelAboutOSK = new javax.swing.JLabel();
        jSeparatorSocialMediaBottom = new javax.swing.JSeparator();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabelAbayimaLogo1 = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        stackPanel = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemSettings = new javax.swing.JMenuItem();

        jButtonTwitter1.setBackground(new java.awt.Color(26, 26, 26));
        jButtonTwitter1.setForeground(new java.awt.Color(26, 26, 26));
        jButtonTwitter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/opensimkit/resources/GitHub.png"))); // NOI18N
        jButtonTwitter1.setBorder(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 171, 183));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        headPanel.setBackground(new java.awt.Color(153, 171, 183));
        headPanel.setMaximumSize(new java.awt.Dimension(32767, 100));
        headPanel.setMinimumSize(new java.awt.Dimension(652, 100));
        headPanel.setName("JPanelNorth"); // NOI18N
        headPanel.setPreferredSize(new java.awt.Dimension(570, 50));
        headPanel.setLayout(new javax.swing.BoxLayout(headPanel, javax.swing.BoxLayout.LINE_AXIS));

        btnConnectTwitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/opensimkit/resources/TwitterConnect.png"))); // NOI18N
        btnConnectTwitter.setBorder(null);
        btnConnectTwitter.setMaximumSize(new java.awt.Dimension(50, 50));
        btnConnectTwitter.setMinimumSize(new java.awt.Dimension(50, 50));
        btnConnectTwitter.setPreferredSize(new java.awt.Dimension(50, 50));
        btnConnectTwitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectTwitterActionPerformed(evt);
            }
        });
        headPanel.add(btnConnectTwitter);

        btnConnectGitHub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/opensimkit/resources/GitHubConnect.png"))); // NOI18N
        btnConnectGitHub.setBorder(null);
        btnConnectGitHub.setMaximumSize(new java.awt.Dimension(50, 50));
        btnConnectGitHub.setMinimumSize(new java.awt.Dimension(50, 50));
        btnConnectGitHub.setPreferredSize(new java.awt.Dimension(50, 50));
        btnConnectGitHub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectGitHubActionPerformed(evt);
            }
        });
        headPanel.add(btnConnectGitHub);

        btnConnectSIMKit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/opensimkit/resources/SIMKitConnect.png"))); // NOI18N
        btnConnectSIMKit.setBorder(null);
        btnConnectSIMKit.setMaximumSize(new java.awt.Dimension(50, 50));
        btnConnectSIMKit.setMinimumSize(new java.awt.Dimension(50, 50));
        btnConnectSIMKit.setPreferredSize(new java.awt.Dimension(50, 50));
        btnConnectSIMKit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectSIMKitActionPerformed(evt);
            }
        });
        headPanel.add(btnConnectSIMKit);
        headPanel.add(filler8);
        headPanel.add(filler1);

        jButton1.setText("jButton1");
        jButton1.setMaximumSize(new java.awt.Dimension(120, 36));
        jButton1.setMinimumSize(new java.awt.Dimension(120, 36));
        jButton1.setPreferredSize(new java.awt.Dimension(120, 36));
        headPanel.add(jButton1);
        headPanel.add(filler4);

        getContentPane().add(headPanel, java.awt.BorderLayout.SOUTH);

        mainPanel.setBackground(new java.awt.Color(153, 171, 183));
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));

        sidePanel.setBackground(new java.awt.Color(153, 171, 183));
        sidePanel.setMaximumSize(new java.awt.Dimension(150, 32767));
        sidePanel.setMinimumSize(new java.awt.Dimension(150, 0));
        sidePanel.setPreferredSize(new java.awt.Dimension(150, 620));
        sidePanel.setLayout(new javax.swing.BoxLayout(sidePanel, javax.swing.BoxLayout.PAGE_AXIS));

        filler3.setBackground(new java.awt.Color(153, 171, 183));
        sidePanel.add(filler3);

        jPanel3.setBackground(new java.awt.Color(153, 171, 183));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabelAboutOSK.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelAboutOSK.setText("<html><div style=\"width:100%;\">SIMKIT is a free and open source software designed to turn the world's feature phones into low cost e-readers</div></html>");
        jPanel3.add(jLabelAboutOSK);

        sidePanel.add(jPanel3);

        jSeparatorSocialMediaBottom.setBackground(new java.awt.Color(153, 171, 183));
        sidePanel.add(jSeparatorSocialMediaBottom);
        sidePanel.add(filler2);

        jSeparator1.setMaximumSize(new java.awt.Dimension(32767, 10));
        jSeparator1.setMinimumSize(new java.awt.Dimension(4, 10));
        jSeparator1.setPreferredSize(new java.awt.Dimension(150, 10));
        sidePanel.add(jSeparator1);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(filler7);

        jLabelAbayimaLogo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelAbayimaLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/opensimkit/resources/AbayimaLogo.png"))); // NOI18N
        jPanel1.add(jLabelAbayimaLogo1);
        jPanel1.add(filler6);

        sidePanel.add(jPanel1);

        mainPanel.add(sidePanel);

        stackPanel.setBackground(new java.awt.Color(223, 232, 239));
        stackPanel.setBorder(null);

        org.jdesktop.layout.GroupLayout stackPanelLayout = new org.jdesktop.layout.GroupLayout(stackPanel);
        stackPanel.setLayout(stackPanelLayout);
        stackPanelLayout.setHorizontalGroup(
            stackPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 509, Short.MAX_VALUE)
        );
        stackPanelLayout.setVerticalGroup(
            stackPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 513, Short.MAX_VALUE)
        );

        mainPanel.add(stackPanel);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        jMenuFile.setText("File");

        jMenuItemSettings.setText("Settings");
        jMenuItemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSettingsActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSettings);

        jMenuBar.add(jMenuFile);

        setJMenuBar(jMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSettingsActionPerformed
        // TODO add your handling code here:
        SettingsFrame settingsFrame = new SettingsFrame();
        settingsFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemSettingsActionPerformed

    private void btnConnectSIMKitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectSIMKitActionPerformed
        // TODO add your handling code here:
        try {            
            URI uri = new URI(this.openSIMKitUrl);
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConnectSIMKitActionPerformed

    private void btnConnectGitHubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectGitHubActionPerformed
        // TODO add your handling code here:
        try {            
            URI uri = new URI(this.gitHubUrl);
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConnectGitHubActionPerformed

    private void btnConnectTwitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectTwitterActionPerformed
        // TODO add your handling code here:
        try {            
            URI uri = new URI(this.twitterUrl);
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConnectTwitterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnectGitHub;
    private javax.swing.JButton btnConnectSIMKit;
    private javax.swing.JButton btnConnectTwitter;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JPanel headPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonTwitter1;
    private javax.swing.JLabel jLabelAbayimaLogo1;
    private javax.swing.JLabel jLabelAboutOSK;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemSettings;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparatorSocialMediaBottom;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel stackPanel;
    // End of variables declaration//GEN-END:variables
}
