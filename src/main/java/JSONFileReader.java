import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class JSONFileReader {

    public static void main(String[] args) {

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("src/main/java/person.json"));
            String firstName = (String) jsonObject.get("firstName");
            String lastName = (String) jsonObject.get("lastName");
            long  age = (long) jsonObject.get("age");
            Map address = (Map) jsonObject.get("address");
            String streetAddress = (String) address.get("streetAddress");
            String city = (String) address.get("city");
            String state = (String) address.get("state");
            long postalCode = (long) address.get("postalCode");
            JSONArray phoneNumbers = (JSONArray) jsonObject.get("phoneNumbers");


            System.out.println("Contents of the JSON are: ");
            System.out.println("First Name :"+ firstName);
            System.out.println("Last Name: "+ lastName);
            System.out.println("Age: "+ age);
            System.out.println("Street Address: "+ streetAddress);
            System.out.println("City: "+ city);
            System.out.println("State: "+ state);
            System.out.println("Postal code: " + postalCode);

            for(int i = 0; i<phoneNumbers.size();i++){

                Map type = (Map) phoneNumbers.get(i);
                Map num = (Map) phoneNumbers.get(i);

                String phoneType = (String) type.get("type");
                String phoneNum = (String) num.get("number");

                System.out.println("Type: " + phoneType);
                System.out.println("Number: " + phoneNum);

            }


            System.out.println(" ");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
