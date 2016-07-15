package HTTP;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Model.Customer;
import Utilities.DateTime;


/**
 * Created by kuush on 6/9/2016.
 */
public class HttpManager {

    public static String get_Data(String uri) {

        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

    public String GetData(String url) {
        System.out.print(url);
        BufferedReader reader = null;

        try {
            URL url_ = new URL(url);
            HttpURLConnection con = (HttpURLConnection) url_.openConnection();

            if (con.getResponseCode() != 200) {
                return "Unable to connect to Service";
            }else {


                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                con.disconnect();
                return sb.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Timeout";
        } finally {
            if (reader != null) {
                try {
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error";
                }
            }
        }
    }

    public String Customer_Updated(Object... objects){

        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder response = null;
      //  String jsonInString = null;



        Customer customer_to_send = (Customer)objects[0];

        try {


            url_ =new URL("http://164.100.138.204/eServices/updateUserDetails/");
            conn_ = (HttpURLConnection)url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn_.connect();

           //Convert the Object into Json Object
            ObjectMapper mapper = new ObjectMapper();
           String jsonInString = mapper.writeValueAsString(customer_to_send);
              Log.e("String",jsonInString);
            StringBuilder New_String = new StringBuilder();
            New_String.append("jsonData=");
            New_String.append(jsonInString);

          //  JSONObject obj = new JSONObject(New_String.toString());

            Log.e("My App data in Json", New_String.toString());
            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(New_String.toString());
            out.close();

            try{
                int HttpResult =conn_.getResponseCode();
                Log.e("Server Code",Integer.toString(HttpResult));
                if(HttpResult !=HttpURLConnection.HTTP_OK){
                    return "Timeout.";

                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    response = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        response.append(line + "\n");
                    }
                    br.close();
                }

            } catch(Exception e){
                return "Error";
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(conn_!=null)
                conn_.disconnect();
        }
        return response.toString().trim();
    }


}
