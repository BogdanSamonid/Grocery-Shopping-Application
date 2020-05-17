package service;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;

public class JsonParser {

    JSONParser parser;
    public JsonParser(){
        parser = new JSONParser();
    }

    public JSONObject parse(String file) throws IOException, ParseException {

        Object obj = parser.parse(new InputStreamReader(JsonParser.class.getResourceAsStream(file))); //parse
        JSONObject jsonObject = (JSONObject) obj;

        return jsonObject;
    }
}
