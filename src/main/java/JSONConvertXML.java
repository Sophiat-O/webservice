import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;


public class JSONConvertXML {

    public static void main(String[] args) {

        try {
            File fXmlFile = new File("src/main/java/book.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            JSONObject jsonObject = new JSONObject();

            doc.getDocumentElement().normalize();
            String rootNode = doc.getDocumentElement().getNodeName();
            NodeList list = doc.getElementsByTagName("*");
            Map books = new LinkedHashMap();

            for(int i = 0; i < list.getLength(); i++) {
                Node nList = list.item(i);
                Element eElement = (Element) nList;
                if (nList.getNodeType() == Node.ELEMENT_NODE) {
                    books.put(nList.getNodeName(), eElement.getElementsByTagName(nList.getNodeName()).item(i).getNodeValue());
                }
            }

            System.out.println(books);
            jsonObject.put(rootNode,books);


//            PrintWriter pw = new PrintWriter("src/main/java/new_file3.json");
//            pw.write(jsonObject.toJSONString());
//            pw.flush();
//            pw.close();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
