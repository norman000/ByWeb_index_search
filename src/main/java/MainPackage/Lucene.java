package MainPackage;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Lucene {
    public static void create_index(HashMap hashmap) throws IOException, ParseException {
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        File indexDir = new File("/lucene_index_file/index");
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
        IndexWriter w = new IndexWriter(FSDirectory.open(indexDir), config);

        Iterator it = hashmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            addDoc(w, pairs.getValue().toString(), pairs.getKey().toString());
            it.remove();
        }
        w.close();
    }

    private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }
}