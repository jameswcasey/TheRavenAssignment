import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TheRaven {
   HashMap<String, Integer> result = new HashMap<String, Integer>();

    /**
     * <p>
     * Method responsible for sorting the HashMap by turning it into a list and back.
     * </p>
     * @param hm
     * @return
     */
    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
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

    /**
     *<p>
     *   Creates a Scanner to real the URL.
     *      * Creates a string buffer to append the scanned items together.
     *      * Formats the String based off program requirements.
     *      * Organizes the items within the HashMap by sorting them ast a list.
     *      * Prints the result.
     *</p>
     * @throws Exception
     */
    public void print() throws Exception {
        URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
        Connection conn = null;
        Statement stmt = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/word_occurences?useSSL=false", "root", "jake9224");
                System.out.println("Connection is created successfully:");
                stmt = conn.createStatement();
                System.out.println("Statement is created successfully:");

            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Something went wrong");
            } finally {

            }
        try {
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
                    if (stmt != null) {
                        String query1 = "INSERT INTO words " + "VALUES ('" + word + "')";
                        stmt.executeUpdate(query1);
                        System.out.println("inserted");
                    }
                }
            }
            HashMap<String, Integer> sortedDict = sortByValue(dict);
            for (Map.Entry<String, Integer> en : sortedDict.entrySet()) {
                System.out.println("Key = " + en.getKey() +
                        ", Value = " + en.getValue());
            }
            System.out.println("Results: " + sortedDict);
            this.result = sortedDict;
            conn.close();
        } catch(Exception e) {

        }
    }
}
