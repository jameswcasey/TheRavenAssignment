import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Scanner;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.lang.*;
import java.util.*;


public class Main {
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue().compareTo(o2.getValue()));
            }
        });
        Collections.reverse(list);
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
        Scanner scanner = new Scanner(url.openStream());
        StringBuffer buffer = new StringBuffer();
        while (scanner.hasNext()) {
            buffer.append(scanner.next() + " ");
        }
        String urlString = buffer.toString();
        String reducedText = Jsoup.parse(urlString).text();
        String[] split = reducedText.split("#45484 ]");
        String[] split2 = split[1].split("END OF THE PROJECT");
        String finalText = split2[0];
        String[] theRaven = finalText.split(" ");
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        for (String word : theRaven) {
            if (dict.get(word) != null) {
                dict.put(word, dict.get(word) + 1);
            } else {
                dict.put(word, 1);
            }
        }
        Map<String, Integer> sortedDict = sortByValue(dict);
        for (Map.Entry<String, Integer> en : sortedDict.entrySet()) {
            System.out.println("Key = " + en.getKey() +
                    ", Value = " + en.getValue());
        }
        System.out.println("Results: " + sortedDict);
    }
}
