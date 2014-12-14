package MainPackage;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.lucene.search.ScoreDoc;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.joox.*;
import java.io.*;
import java.util.Map;

public class XmlWork {
    public static HashMap get_id_query(String filename)
    {
        HashMap<String, String> hashmap = new HashMap();

        try {
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("task");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    hashmap.put(eElement.getAttribute("id").toString(), eElement.getElementsByTagName("querytext").item(0).getTextContent().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashmap;
    }

    public static HashMap<String, String[][]> get_relevance_table(String filename)
    {
        HashMap<String, String[][]> hashmap_table = new HashMap<String,  String[][]>();

        try {
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("task");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    NodeList nList_doc = doc.getElementsByTagName("document");

                    String[][] array_str = new String[nList_doc.getLength()][2];

                    for (int temp_doc = 0; temp_doc < nList_doc.getLength(); temp_doc++) {

                        Node nNode_doc = nList_doc.item(temp_doc);

                        if (nNode_doc.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement_doc = (Element) nNode_doc;

                            array_str[temp_doc][0] = eElement_doc.getAttribute("id").toString();
                            array_str[temp_doc][1] = eElement_doc.getAttribute("relevance").toString();
                        }
                    }
                    hashmap_table.put(eElement.getAttribute("id").toString(), array_str);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashmap_table;
    }

    public static HashMap<String, HashMap> get_relevance_hashmap(String filename)
    {
        HashMap<String, HashMap> hashmap_table = new HashMap<String, HashMap>();

        HashMap<String, String> hashmap_need_doc = new HashMap<String, String>();
        try {
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("task");
            ArrayList<HashMap> help_array = new ArrayList<HashMap>();
            //System.out.println(help_array.length);

            for (int temp = 0; temp < nList.getLength(); temp++) {
                help_array.add(new HashMap());
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    NodeList nList_doc = doc.getElementsByTagName("document");

                    for (int temp_doc = 0; temp_doc < nList_doc.getLength(); temp_doc++) {

                        Node nNode_doc = nList_doc.item(temp_doc);

                        if (nNode_doc.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement_doc = (Element) nNode_doc;
                            hashmap_need_doc.put(eElement_doc.getAttribute("id").toString(), eElement_doc.getAttribute("relevance").toString());
                        }
                    }
                    hashmap_table.put(eElement.getAttribute("id").toString(), hashmap_need_doc);
                    hashmap_need_doc.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashmap_table;
    }

    public static HashMap<String, String> get_relevance_hashmap_m(String filename)
    {
        HashMap<String, String> hashmap_table = new HashMap();

        try {
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("task");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(0);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    NodeList nList_doc = doc.getElementsByTagName("document");

                    for (int temp_doc = 0; temp_doc < nList_doc.getLength(); temp_doc++) {

                        Node nNode_doc = nList_doc.item(temp_doc);

                        if (nNode_doc.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement_doc = (Element) nNode_doc;
                            hashmap_table.put(eElement_doc.getAttribute("id").toString(), eElement_doc.getAttribute("relevance").toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashmap_table;
    }

    public static ArrayList<String> get_list_query(String filename) {
        ArrayList<String> get_list_array = new ArrayList<String>();

        try {
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("task");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    get_list_array.add(eElement.getAttribute("id").toString());
                }
            }
        }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        return get_list_array;
    }
}
