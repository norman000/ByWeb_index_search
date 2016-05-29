package MainPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Metrics {
    public static void main(String args[]) {
        ArrayList<HashMap> hm_array = new ArrayList<HashMap>();
        int all_rel_first = 0;

        HashMap<String, String> hm1 = new HashMap<String, String>();
        HashMap<String, String> hm2 = new HashMap<String, String>();
        HashMap<String, String> hm3 = new HashMap<String, String>();
        HashMap<String, String> hm4 = new HashMap<String, String>();

        hm1 = XmlWork.get_relevance_hashmap_m("/lucene_index_file/new_xml/arw57797.xml");
        hm2 = XmlWork.get_relevance_hashmap_m("/lucene_index_file/new_xml/arw57272.xml");
        hm3 = XmlWork.get_relevance_hashmap_m("/lucene_index_file/new_xml/arw53809.xml");
        hm4 = XmlWork.get_relevance_hashmap_m("/lucene_index_file/new_xml/arw53808.xml");

        hm_array.add(hm1);
        hm_array.add(hm2);
        hm_array.add(hm3);
        hm_array.add(hm4);

        ArrayList<HashMap> hm_array_past = new ArrayList<HashMap>();

        HashMap<String, String> hm1_past = new HashMap<String, String>();
        HashMap<String, String> hm2_past = new HashMap<String, String>();
        HashMap<String, String> hm3_past = new HashMap<String, String>();
        HashMap<String, String> hm4_past = new HashMap<String, String>();

        hm1_past = XmlWork.get_relevance_hashmap_m("/lucene_index_file/past_xml/arw57797.xml");
        hm2_past = XmlWork.get_relevance_hashmap_m("/lucene_index_file/past_xml/arw57272.xml");
        hm3_past = XmlWork.get_relevance_hashmap_m("/lucene_index_file/past_xml/arw53809.xml");
        hm4_past = XmlWork.get_relevance_hashmap_m("/lucene_index_file/past_xml/arw53808.xml");

        hm_array_past.add(hm1_past);
        hm_array_past.add(hm2_past);
        hm_array_past.add(hm3_past);
        hm_array_past.add(hm4_past);

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        Iterator it_1 = hm1_past.entrySet().iterator();
        while (it_1.hasNext()) {
            Map.Entry pairs = (Map.Entry) it_1.next();
            if (pairs.getValue().toString().equals("notrelevant")) {
                boolean is_cont = hm1.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    b++;
                } else {
                    d++;
                }
            }
            if (pairs.getValue().toString().equals("vital")) {
                all_rel_first++;

                boolean is_cont = hm1.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    a++;
                } else {
                    c++;
                }
            }
        }

        Iterator it_2 = hm2_past.entrySet().iterator();
        while (it_2.hasNext()) {
            Map.Entry pairs = (Map.Entry) it_2.next();
            if (pairs.getValue().toString().equals("notrelevant")) {
                boolean is_cont = hm2.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    b++;
                } else {
                    d++;
                }
            }
            if (pairs.getValue().toString().equals("vital")) {
                boolean is_cont = hm2.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    a++;
                } else {
                    c++;
                }
            }
        }
        Iterator it_3 = hm3_past.entrySet().iterator();
        while (it_3.hasNext()) {
            Map.Entry pairs = (Map.Entry) it_3.next();
            if (pairs.getValue().toString().equals("notrelevant")) {
                boolean is_cont = hm3.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    b++;
                } else {
                    d++;
                }
            }
            if (pairs.getValue().toString().equals("vital")) {
                boolean is_cont = hm3.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    a++;
                } else {
                    c++;
                }
            }
        }
        Iterator it_4 = hm4_past.entrySet().iterator();
        while (it_4.hasNext()) {
            Map.Entry pairs = (Map.Entry) it_4.next();
            if (pairs.getValue().toString().equals("notrelevant")) {
                boolean is_cont = hm4.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    b++;
                } else {
                    d++;
                }
            }
            if (pairs.getValue().toString().equals("vital")) {
                boolean is_cont = hm4.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    a++;
                } else {
                    c++;
                }
            }
        }

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

        double recall = (a + 0.0) / (a + 0.0 + c);
        System.out.println(" ");
        System.out.println(recall);

        double precision = (a + 0.0) / (a + 0.0 + b);
        System.out.println(" ");
        System.out.println(precision);

        double accuracy = (a + d + 0.0) / (a + b + c + d + 0.0);
        System.out.println(" ");
        System.out.println(accuracy);

        double error = (b + c + 0.0) / (a + b + c + d + 0.0);
        System.out.println(" ");
        System.out.println(error);

        double r = recall;
        double p = precision;

        double F = 2 / (1 / p + 1 / r);
        System.out.println(" ");
        System.out.println(F);

        double average_precision = aver_count(hm1, hm1_past, all_rel_first);
        System.out.println(" ");
        System.out.println(average_precision);
    }

    public static double precision(int a, int b) {
        return ((a + 0.0 + b) == 0) ? 0.0 : (a + 0.0) / (a + 0.0 + b);
    }

    public static double aver_count(HashMap<String, String> hm1, HashMap<String, String> hm1_past, int all_rel_first) {
        double average = 0;
        double sum = 1.0;
        int a = 0, b = 0, c = 0, d = 0;

        Iterator it_1 = hm1_past.entrySet().iterator();
        while (it_1.hasNext()) {
            Map.Entry pairs = (Map.Entry) it_1.next();
            if (pairs.getValue().toString().equals("notrelevant")) {
                boolean is_cont = hm1.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    b++;
                } else {
                    d++;
                }
            }
            if (pairs.getValue().toString().equals("vital")) {
                sum = sum + precision(a, b);

                boolean is_cont = hm1.containsKey(pairs.getKey().toString());
                if (is_cont) {
                    a++;
                } else {
                    c++;
                }
            }
        }
        return sum / all_rel_first;
    }
}

