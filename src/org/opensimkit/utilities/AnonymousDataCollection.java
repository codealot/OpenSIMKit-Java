/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensimkit.utilities;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author ahmedmaawy
 */
public class AnonymousDataCollection {

    private String binaryPath = null;
    private String filePath = "";
    private final String COLLECT_ABOUT_USE = "collect_about_use";
    private final String COLLECT_TURNED_ON = "collect_turned_on";
    private final String xmlFileName = "AppSettings.xml";
    private boolean settingsFileExists = false;
    private boolean collectAboutUseEnabled = false;
    private boolean collectTurnedOnEnabled = false;

    /**
     * Class constructor
     */
    public AnonymousDataCollection() {
        // Get the current running path
        String path = AnonymousDataCollection.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        try {
            binaryPath = URLDecoder.decode(path, "UTF-8");
            filePath = StringUtil.trimRight(binaryPath, '/');

            int i = filePath.indexOf("/OpenSIMKit.jar");
            if (i > -1) {
                filePath = StringUtil.trimRight((filePath.substring(0, i)), '/');
            }

            filePath = filePath.concat("/" + xmlFileName);
            
            File file = new File(filePath);
            
            settingsFileExists = file.exists() ? readXMLFile() : false;

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AnonymousDataCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read settings XML file
     *
     * @return
     */
    private boolean readXMLFile() {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc= factory.newDocumentBuilder().parse(xmlFile);
            
            doc.getDocumentElement().normalize();

            NodeList n;
            n = doc.getElementsByTagName(this.COLLECT_ABOUT_USE);
            collectAboutUseEnabled = Boolean.parseBoolean(n.item(0).getTextContent());
            
            n = doc.getElementsByTagName(this.COLLECT_TURNED_ON);
            collectTurnedOnEnabled = Boolean.parseBoolean(n.item(0).getTextContent());

        } catch (Exception ex) {
            Logger.getLogger(AnonymousDataCollection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Saves settings to an XML File
     *
     * @param collectAboutUse
     * @param collectTurnedOn
     * @return boolean
     */
    public boolean saveXMLSettings(boolean collectAboutUse, boolean collectTurnedOn) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().newDocument();

            // root elements
            Element child;
            Element root = doc.createElement("settings");
            doc.appendChild(root);
            
            String s;

            // Collect about use
            s = collectAboutUse ? "true" : "false";
            child = doc.createElement(this.COLLECT_ABOUT_USE);
            child.appendChild(doc.createTextNode(s));
            root.appendChild(child);

            // Collect if turned on
            s = collectTurnedOn ? "true" : "false";
            child = doc.createElement(this.COLLECT_TURNED_ON);
            child.appendChild(doc.createTextNode(s));
            root.appendChild(child);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(filePath));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AnonymousDataCollection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            
        } catch (TransformerException ex) {
            Logger.getLogger(AnonymousDataCollection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * File exists property
     *
     * @return
     */
    public boolean isSettingsFileExists() {
        return settingsFileExists;
    }

    /**
     * Collect data about the use property
     *
     * @return
     */
    public boolean isCollectAboutUseEnabled() {
        return collectAboutUseEnabled;
    }

    /**
     * Collect data on being turned on property
     *
     * @return
     */
    public boolean isCollectTurnedOnEnabled() {
        return collectTurnedOnEnabled;
    }
}
