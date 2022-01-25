import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.PrintWriter;

public class JSONNewConvert {

    public static void main(String[] args) {

        String data = "";

        try {
            data = FileUtils.fileRead("src/main/java/map.osm");
            XmlMapper xmlMapper = new XmlMapper();

            JsonNode jsonNode = xmlMapper.readTree(data.getBytes());

            ObjectMapper objectMapper = new ObjectMapper();

            String value = objectMapper.writeValueAsString(jsonNode);

            PrintWriter pw = new PrintWriter("src/main/java/maps.json");
            pw.write(value);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
