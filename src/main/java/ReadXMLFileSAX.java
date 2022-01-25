import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLFileSAX {

    private static int nNodeCount=0;
    private static int wayCount=0;
    private static int relCount=0;


    public static void main(String argv[]) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {


                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("bounds")) {
                        for(int i=0; i<attributes.getLength(); i++){
                            System.out.println(attributes.getLocalName(i) +" " + attributes.getValue(i));
                        }
                        System.out.println("--------------------------------");
                        System.out.println(" ");
                    }

                    if (qName.equalsIgnoreCase("node")) {
                        nNodeCount++;
                    }

                    if (qName.equalsIgnoreCase("way")) {
                        wayCount++;
                    }

                    if (qName.equalsIgnoreCase("relation")) {
                        relCount++;
                    }
                    if (qName.equalsIgnoreCase("tag")) {
                        System.out.println("Key: " + attributes.getValue("k"));
                        System.out.println("Value: " + attributes.getValue("v"));

                    }
                }


            };

            saxParser.parse("src/main/java/map.osm", handler);
            System.out.println("--------------------------------");
            System.out.println(" ");
            System.out.println("Node count is: " + nNodeCount );
            System.out.println("Way count is: " + wayCount);
            System.out.println("Relation count is: " + relCount);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
