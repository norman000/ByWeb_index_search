package MainPackage;

import java.io.*;
import org.apache.lucene.search.ScoreDoc;
import java.util.ArrayList;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;
import java.util.HashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.lucene.queryparser.classic.ParseException;

import javax.management.Query;

public class Test {
    public static void write_xml(ArrayList<ScoreDoc[]> list_doc_wich_rel, ArrayList<String> need_query_array ) throws IOException, JDOMException {
        try {
            Document doc = new Document();
            for (int i = 0; i < list_doc_wich_rel.size(); i++) {

                String name_queries = need_query_array.get(i);
                System.out.println(name_queries);
                Element queries_name = new Element("task");
                queries_name.setAttribute(new Attribute("id", name_queries));
                doc.setRootElement(queries_name);

                for (int j = 0; j < list_doc_wich_rel.get(i).length; j++) {
                     //документ релевантный по этому запросу
                     int docId = list_doc_wich_rel.get(i)[j].doc;
                     Element staff = new Element("document");
                     staff.setAttribute(new Attribute("id", Integer.toString(docId)));
                     staff.setAttribute(new Attribute("relevance", "vital"));
                     doc.getRootElement().addContent(staff);
                }
                System.out.println("Try Saved!");
                XMLOutputter xmlOutput = new XMLOutputter();

                xmlOutput.setFormat(Format.getPrettyFormat());
                xmlOutput.output(doc, new FileWriter("/Users/Nurislam/Documents/lucene_index_file/new_xml/" + name_queries + ".xml"));

                System.out.println("File Saved!");

            }

        }
         catch (Exception e) {
             e.printStackTrace();

        }

    }

    public static void main(String[] args) throws IOException, JDOMException, ParseException
    {
        //массив запросов, которые нужно произвести
        ArrayList<String> need_query_array = XmlWork.get_list_query("/Users/Nurislam/Documents/lucene_index_file/data/new/and_relevant-minus_table.xml");

        //номер запроса, номер документа - релевантность
        //HashMap<String, HashMap> get_relevance_hashmap = XmlWork.get_relevance_hashmap("/Users/Nurislam/Documents/lucene_index_file/data/and_relevant-minus_table.xml");

        //System.out.println(get_relevance_hashmap.get("arw53809").size());
        //номер запроса - текст запроса
        HashMap<String, String> hashmap = XmlWork.get_id_query("/Users/Nurislam/Documents/lucene_index_file/data/new/web2008_adhoc.xml");

        //список документов, релевантных по нужному запросу
        ArrayList<ScoreDoc[]> list_doc_wich_rel = Queries.do_query(need_query_array, hashmap);
        //System.out.println(list_doc_wich_rel.size());
        write_xml(list_doc_wich_rel, need_query_array);
    }

}