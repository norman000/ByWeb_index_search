package MainPackage;

import java.util.HashMap;
import java.util.Iterator;

public class MainClass {
    public static void main(String[] args) throws Exception {

        //строим индексы и сохраняем в файл
        BuildIndex bi = new BuildIndex();
        File[] get_file_array = bi.get_file_in_folder("/lucene_index_file/data/By.web");
        bi.create_index(get_file_array);

        //получаем таблицу релевантных документов
        //ключ - id запроса
        //значение - массив номеров документов
        //notrelevant - не релевантен
        //vital - релевантен
        HashMap<String, String[][]> hashmap_table = new HashMap<String, String[][]>();
        hashmap_table = XmlWork.get_relevance_table("/lucene_index_file/data/and_relevant-minus_table.xml");
        System.out.println(hashmap_table.size());

        Iterator it = hashmap_table.entrySet().iterator();
        String[][] st = (hashmap_table.get("arw57797"));
        for (int k = 0; k < st.length; k++) {
            System.out.println(st[k][1]);
        }
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String[][] st = pairs.getValue();
            it.remove();
        }

        //парсим xml с запросами
        XmlWork xw = new XmlWork();
        HashMap hashmap = xw.get_id_query("/lucene_index_file/data/web2008_adhoc.xml", hashmap_table);

        //выполняем те запросы, которые нужны
        //получим массив со списком, содержащим список с релевантными документами
        Queries qr = new Queries();
        ArrayList<ScoreDoc[]> scrd_array = qr.do_query(hashmap);

        Concatinate.concatinate(hashmap_table, scrd_array);

        XmlWork.write_to_xml();
        System.out.println(hashmap_table.get("arw57797")[1][1]);
        Iterator it = hashmap_table.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            System.out.println(pairs.getValue());
            it.remove();
        }
    }
}
