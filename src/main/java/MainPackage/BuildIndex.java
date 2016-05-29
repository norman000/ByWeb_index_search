package MainPackage;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;

public class BuildIndex {
    public static HashMap parse_txt_file(String text) {
        HashMap hashmap = new HashMap();
        StringBuffer s_buffer = new StringBuffer(text.subSequence(0, text.length()));

        String[] text_split = text.split("\t" + "http:");
        int count = 1;
        for (int i = 1; i < text_split.length; i++) {
            hashmap.put(String.valueOf(count), text_split[i]);
            count++;
        }
        return hashmap;
    }

    public static void create_index(File[] listOfFiles) throws Exception {
        String[][] answer_array = {};
        String text_from_one_file = "";
        int num_file = 1;
        for (File file : listOfFiles) {
            if (file.getName().toString().length() == 15) {
                System.out.println(num_file);
                text_from_one_file = get_text_one_file(file.getPath());
                HashMap hashmap = (parse_txt_file(text_from_one_file));
                Lucene hl = new Lucene();
                hl.create_index(hashmap);
                System.out.println(file.getName());
                num_file++;
            }
        }
    }

    public static String get_text_one_file(String filename) throws Exception {
        File file = new File(filename);
        String content = FileUtils.readFileToString(file);
        return content;
    }

    public static File[] get_file_in_folder(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }
}
