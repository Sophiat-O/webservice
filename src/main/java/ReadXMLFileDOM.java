import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;

public class ReadXMLFileDOM {

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("src/main/java/map.osm");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("node");
            NodeList wayList = doc.getElementsByTagName("way");
            NodeList relationList = doc.getElementsByTagName("relation");
            NodeList boundList = doc.getElementsByTagName("bounds");
            NodeList tagList = doc.getElementsByTagName("tag");
            Node boundNode = boundList.item(0);


            System.out.println("Map bound is: ");

            for(int i = 0; i < boundNode.getAttributes().getLength(); i++){
                System.out.println(boundNode.getAttributes().item(i).getNodeName() + ": "+ boundNode.getAttributes().item(i).getNodeValue());

            }

            System.out.println(" ");
            System.out.println("--------------------------------------------------------------------");
            System.out.println(" ");


            System.out.println("Node count is: " + nList.getLength());
            System.out.println("Way count is: " + wayList.getLength());
            System.out.println("Relation count is: " + relationList.getLength());

            System.out.println(" ");
            System.out.println("---------------------------------TAGS-------------------------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.println(" ");

            for(int i = 0; i < tagList.getLength(); i++){
                System.out.println(tagList.item(i).getAttributes().item(0).getNodeValue() + ": " +tagList.item(i).getAttributes().item(1).getNodeValue() );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
