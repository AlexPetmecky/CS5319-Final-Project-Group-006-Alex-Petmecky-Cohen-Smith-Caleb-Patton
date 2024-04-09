import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
public class Dates {
    JSONArray dates = new JSONArray();
    String directory = "data/dates.json";

    //constructor
    public Dates() {
        getDates();
    }

    //get dates from file
    public void getDates() {
        try {
            JSONParser parser = new JSONParser();
            dates = (JSONArray) parser.parse(new FileReader(directory));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //write all dates to file
    public void writeDates() {
        JSONParser parser = new JSONParser();
        try {
            dates = (JSONArray)(parser.parse(new FileReader(directory)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
