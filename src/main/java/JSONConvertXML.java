import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.json.simple.JSONArray;
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
            JSONArray jsonArray = new JSONArray();

            doc.getDocumentElement().normalize();
            String rootNode = doc.getDocumentElement().getNodeName();
            NodeList list = doc.getElementsByTagName("*");
            Map books = new LinkedHashMap();

            for(int k=0; k< doc.getDocumentElement().getAttributes().getLength();k++){
                books.put(doc.getDocumentElement().getAttributes().item(k).getNodeName(),doc.getDocumentElement().getAttributes().item(k).getNodeValue());

            }

            for(int i = 1; i < list.getLength(); i++) {
                Node nList = list.item(i);
                Element eElement = (Element) nList;
                Map elementMap = new LinkedHashMap();

               for(int j=0; j < nList.getAttributes().getLength(); j++){

                    elementMap.put(nList.getAttributes().item(j).getNodeName(), nList.getAttributes().item(j).getNodeValue());

                }
                if(nList.getAttributes().getLength() > 1){
                    jsonArray.add(elementMap);
                }
                if(nList.hasChildNodes()) {
                    books.put(nList.getChildNodes().item(i).getNodeName(), jsonArray);
                }
                else{
                    books.put(nList.getNodeName(),jsonArray);
                }
            }

            jsonObject.put(rootNode,books);
            //System.out.println(jsonObject);



            PrintWriter pw = new PrintWriter("src/main/java/created_file.json");
            pw.write(jsonObject.toJSONString());
            pw.flush();
            pw.close();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
