import java.util.ArrayList;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;


public class JSONFileCreator {

    public static void main(String[] args) throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject();
        JSONArray messages = new JSONArray();

        ArrayList<String> categories = new ArrayList();
        categories.add("School");
        categories.add("Students");

        Map mes = new LinkedHashMap(7);
        mes.put("subject","Congrats");
        mes.put("recipient","Meike");
        mes.put("sender","Tolani");
        mes.put("date","17/01/2022");
        mes.put("content","Hey!, congrats on your new award!");
        mes.put("content_type","normal");
        mes.put("categories",categories);

        messages.add(mes);

        jsonObject.put("message",messages);

        PrintWriter pw = new PrintWriter("src/main/java/JSONCreator.json");
        pw.write(jsonObject.toJSONString());
        pw.flush();
        pw.close();




    }
}
