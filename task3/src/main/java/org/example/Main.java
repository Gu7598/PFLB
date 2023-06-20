package org.example;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static JsonParser parser = new JsonParser();

    public static void main(String[] args) {
       
        String testsJsonLine = readFile(args[0]);
      String valuesJsonLine = readFile(args[1]);
        System.out.println(args[0] +" " + args[1]);
     
        JsonElement jElementTests = parser.parse(testsJsonLine);
        JsonArray childArrayTests = jElementTests
                .getAsJsonObject()
                .getAsJsonArray("tests"); // получить объект Place

      
        JsonElement jElementValues = parser.parse(valuesJsonLine);
        JsonArray childArrayValues = jElementValues
                .getAsJsonObject()
                .getAsJsonArray("values"); 
        for (int i = 0; i < childArrayValues.size(); i++) {
          
            JsonElement idFromValues = childArrayValues.get(i).getAsJsonObject().get("id");
            String valueFromValues = childArrayValues.get(i).getAsJsonObject().get("value").toString();
            String valueNew = valueFromValues.substring(1, valueFromValues.length() - 1);

            replaceValueMethod(childArrayTests, idFromValues, valueNew);
        }
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jElementTests);
    

        writeFile("report.json", json);
    }

    public static void replaceValueMethod(JsonArray childArray, JsonElement idFromValues, String valueFromValues) {

        for (int i = 0; i < childArray.size(); i++) {
            JsonElement idFromTests = childArray.get(i).getAsJsonObject().get("id");
            if (idFromValues.equals(idFromTests)) {
                if (childArray.get(i).getAsJsonObject().get("value") != null) {
                    childArray.get(i).getAsJsonObject().addProperty("value", valueFromValues);
                }
            }
        }

        for (int i = 0; i < childArray.size(); i++) {
          
            if (childArray.get(i).getAsJsonObject().get("values") != null) {
                diggerMethod(childArray, idFromValues, valueFromValues, i);
            }
        }
    }

    public static void diggerMethod(JsonArray childArray, JsonElement idFromValues, String valueFromValues, int i) {
        childArray = childArray.get(i)
                .getAsJsonObject()
                .getAsJsonArray("values");
        replaceValueMethod(childArray, idFromValues, valueFromValues);
    }

    public static String readFile(String path) {
        StringBuilder finishTextFromFile = new StringBuilder();
        String readableLine;
        try {
            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);
            while ((readableLine = br.readLine()) != null) {
                finishTextFromFile.append(readableLine).append("\n");
            }
            br.close();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return finishTextFromFile.toString();
    }

    public static void writeFile(String path, String writeLine) {
 
        try (
                FileWriter writer = new FileWriter(path, false)) {
        
            new FileWriter(path, false).close();
            writer.write(writeLine);
            writer.flush();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
