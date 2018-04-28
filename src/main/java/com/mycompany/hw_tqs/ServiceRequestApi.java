package com.mycompany.hw_tqs;

/**
 *
 * @author carlos
 */
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



import javax.ejb.Singleton;

@Singleton
public class ServiceRequestApi{

    // essential URL structure is built using constants
    public static final String ACCESS_KEY = "7e057f8c2a99aa62f4c224f3532226d9";
    public static final String BASE_URL = "http://apilayer.net/api/";

    // this object is used for executing requests to the (REST) API
    static CloseableHttpClient httpClient = HttpClients.createDefault();
      

    public String getExchangeRatio(String quantity, String originalCurrency, String destinationCurrency) {
        final String ENDPOINT = "live";
        final String MAINCURR = "USD";
        
        //adaptado do exemplo da documentação,
        //get ratio
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&currencies=" + originalCurrency + "," + destinationCurrency + "&format=1");
        
        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            //convert response to Json
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
            //get ratios (USD related))
            JSONObject jsonObj = exchangeRates.getJSONObject("quotes");
            double ratio1 = jsonObj.getDouble(MAINCURR + originalCurrency);
            double ratio2 = jsonObj.getDouble(MAINCURR + destinationCurrency);
            String floatStringRep = String.valueOf(Double.parseDouble(quantity)/ratio1*ratio2);
            return floatStringRep;
           
        } catch (IOException | ParseException | JSONException e) {
            // TODO Auto-generated catch block
            return "Error in getExchangeRatio(), please check the URL and the currencies selected";
        }
        
    }
    
    
    public List<String> getCurrencies(){
        final String ENDPOINT = "list";
        
        String coinApiUrl = BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY ;
        System.out.println("ESTE É O URL da API list onde consome:  " + coinApiUrl);
        HttpGet get = new HttpGet(coinApiUrl);

        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            //convert response to JSON
            JSONObject jsonAllCurrencies = new JSONObject(EntityUtils.toString(entity));
            // Parsed JSON Objects are accessed according to the JSON resonse's hierarchy, output strings are built
            List<String> allCurrentCurrencies = new ArrayList<>();
            Iterator<String> iter = jsonAllCurrencies.getJSONObject("currencies").keys();          
            while(iter.hasNext())
                allCurrentCurrencies.add(iter.next());
            
            response.close();
            return allCurrentCurrencies;
                  
        } catch (IOException | ParseException | JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
