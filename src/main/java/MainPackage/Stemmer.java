package MainPackage;

import java.io.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.snowball.*;
import org.apache.lucene.util.*;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
public class Stemmer{
    public static String Stem(String text, String language) throws Exception{
        StringBuffer result = new StringBuffer();
        if (text!=null && text.trim().length()>0){
            StringReader tReader = new StringReader(text);
            Analyzer analyzer = new SnowballAnalyzer(Version.LUCENE_35,language);
            TokenStream tStream = analyzer.tokenStream("contents", tReader);
            CharTermAttribute term = tStream.addAttribute(CharTermAttribute.class);

            try {
                while (tStream.incrementToken()){
                    result.append(term.toString());
                    result.append(" ");
                }
            } catch (IOException ioe){
                System.out.println("Error: "+ioe.getMessage());
            }
        }

        if (result.length()==0)
            result.append(text);
        return result.toString().trim();
    }

    public static void main (String[] args) throws Exception{
        String result = Stemmer.Stem("Michele Bachmann amenities pressed her allegations that the former head of her Iowa presidential bid was bribed by the campaign of rival Ron Paul to endorse him, even as one of her own aides denied the charge.", "English");
        System.out.println(result);
    }
}