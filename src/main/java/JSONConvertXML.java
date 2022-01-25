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
            File fXmlFile = new File("src/main/java/map.osm");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArrays = new JSONArray();


            doc.getDocumentElement().normalize();
            String rootNode = doc.getDocumentElement().getNodeName();
            NodeList list = doc.getElementsByTagName("*");
            Map books = new LinkedHashMap();



            for(int k=0; k< doc.getDocumentElement().getAttributes().getLength();k++){
                books.put(doc.getDocumentElement().getAttributes().item(k).getNodeName(),doc.getDocumentElement().getAttributes().item(k).getNodeValue());

            }

            for(int i = 1; i < list.getLength(); i++) {
                Node nList = list.item(i);
                Node nchild = nList.getChildNodes().item(i);
                Element eElement =(Element) nList;
                NodeList eList = doc.getElementsByTagName(eElement.getNodeName());
                Map elementMap = new LinkedHashMap();
                Map oneElement = new LinkedHashMap();
                JSONArray jsonArray = new JSONArray();
                //System.out.println(eElement.getNodeName());



                if(eList.getLength()==1){
                    for (int j = 0; j < nList.getAttributes().getLength(); j++) {

                        oneElement.put(nList.getAttributes().item(j).getNodeName(), nList.getAttributes().item(j).getNodeValue());

                    }

                    books.put(nList.getNodeName(),oneElement);



                }else {
                    JSONArray nArray = new JSONArray();
                    if(nList.getAttributes().getLength() > 1) {
                        for (int j = 0; j < nList.getAttributes().getLength(); j++) {

                            elementMap.put(nList.getAttributes().item(j).getNodeName(), nList.getAttributes().item(j).getNodeValue());
                        }
                        nArray.add(elementMap);
                        jsonArray.add(nArray);
                        jsonArrays.add(jsonArray);
                        if(nList.hasChildNodes() && nchild !=null) {
                            books.put(nchild.getNodeName(), jsonArrays);
                        }
                        else{
                            books.put(nList.getNodeName(),jsonArrays);
                        }
                    }else {
                        if(nList.hasChildNodes()) {
                            books.put(nList.getChildNodes().item(i).getNodeName(), elementMap);
                        }
                        else{
                            books.put(nList.getNodeName(),elementMap);
                        }
                    }
                    /*if(nList.getAttributes().getLength() > 1){
                        jsonArray.add(elementMap);
                        //jsonArrays.add(jsonArray);

                        if(nList.hasChildNodes() && nchild !=null) {
                            books.put(nchild.getNodeName(), jsonArray);
                        }
                        else{
                            books.put(nList.getNodeName(),jsonArray);
                        }
                    }else {
                        if(nList.hasChildNodes()) {
                            books.put(nList.getChildNodes().item(i).getNodeName(), elementMap);
                        }
                        else{
                            books.put(nList.getNodeName(),elementMap);
                        }
                    }
                    System.out.println(jsonArray);*/
                }


            }

            jsonObject.put(rootNode,books);

            PrintWriter pw = new PrintWriter("src/main/java/maps.json");
            pw.write(jsonObject.toJSONString());
            pw.flush();
            pw.close();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
