package com.maximumclique.graph;


import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
/**
 * @author Crunchify.com
 */
 
public class Test {
 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
 
        JSONObject obj = new JSONObject();
        obj.put("Name", "crunchify.com");
        obj.put("Author", "Arpit Shah");
 
        JSONArray company = new JSONArray();
        company.add("Compnay: eBay");
        company.add("Compnay: Paypal");
        company.add("Compnay: Google");
        obj.put("Company List", company);
 
        FileWriter file = new FileWriter("/Users/Dany/Downloads/file1.txt");
        try {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            file.flush();
            file.close();
        }
    }
}