package MainPackage;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Queries {
    public static ArrayList<ScoreDoc[]> do_query(ArrayList<String> need_query_array, HashMap<String, String> hashmap) throws IOException, ParseException {
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        File indexDir = new File("/lucene_index_file/index");
        Directory index = FSDirectory.open(indexDir);
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int num = 0;
        ArrayList<ScoreDoc[]> list_doc_wich_rel = new ArrayList<ScoreDoc[]>();
        for (int i = 0; i < need_query_array.size(); i++) {
            String querystr = hashmap.get(need_query_array.get(i).toString());
            Query q = new QueryParser(Version.LUCENE_40, "title", analyzer).parse(QueryParser.escape(querystr));

            TotalHitCountCollector collector = new TotalHitCountCollector();
            searcher.search(q, collector);
            TopDocs td = searcher.search(q, Math.max(1, collector.getTotalHits()));
            ScoreDoc[] hits = td.scoreDocs;
            list_doc_wich_rel.add(hits);
            num++;
        }

        reader.close();
        return list_doc_wich_rel;
    }

}
