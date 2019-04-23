package uk.co.HaydnG.RESTful;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

public class ServiceController extends AsyncTask<String, Void, String> {

    private UrlReaderCallback mCallback = null;
    private static final String HOST = "http://192.168.0.6:8080/ShoppingSystem/API/";

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String NO_AUTH = "NO_AUTH";
    public static final String AUTH = "AUTH";
    private AppCompatActivity Main;

    @Override
    protected String doInBackground(String... strings){

        URL url = null;
        HttpURLConnection con = null;
        int status = -1;
        BufferedReader in = null;
        StringBuffer content = null;


        try {
            url = new URL(HOST + strings[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(strings[2]);

            if(strings[1].equals(this.AUTH)){
                String basicAuth = String.format("Basic %s", Base64.encodeToString((strings[3] + ":" + strings[4]).getBytes(),Base64.NO_WRAP));
                con.setRequestProperty("Authorization", basicAuth);
            }

            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", "Java client");
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json");

            System.out.println("RequestType: " + strings[2]);
            if (strings[2].equals(this.POST)) {


                OutputStream os = con.getOutputStream();
                System.out.println(strings[3]);
                byte[] input = strings[3].getBytes();
                os.write(input, 0, input.length);
            }

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            status = con.getResponseCode();
            System.out.println("STATUS CODE: " + status);

            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


        if(content == null){
            return null;

        }else{
            return content.toString();
        }


    }

    @Override
    protected void onPostExecute(String result)
    {
        // communicates the result back via the UrlReaderCallback interface
        mCallback.StatusCodeHandler(result, Main);
    }

    public ServiceController(UrlReaderCallback callback, AppCompatActivity main){

        mCallback = callback;
    }


}
