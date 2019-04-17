package uk.co.HaydnG.RESTful;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Base64;

public class ServiceController extends AsyncTask<String, Void, String> {

    private UrlReaderCallback mCallback = null;
    private static final String HOST = "http://192.168.0.6:8080/ShoppingSystem/API/";

    public final String GET = "GET";

    @Override
    protected String doInBackground(String... strings){

        URL url = null;
        HttpURLConnection con = null;
        int status = -1;
        BufferedReader in = null;
        StringBuffer content = null;
        String basicAuth = String.format("Basic %s", Base64.encodeToString((strings[1] + ":" + strings[2]).getBytes(),Base64.NO_WRAP));

        System.out.println(basicAuth);


        try {
            url = new URL(HOST + strings[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(strings[3]);
            con.setRequestProperty("Authorization", basicAuth);
            status = con.getResponseCode();

            System.out.println("STATUS CODE: " + status);
            if(status == 500 || status == 401){
                return status + "";
            }

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

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
        mCallback.onUrlReaderFinished(result);
    }

    public ServiceController(UrlReaderCallback callback){
        mCallback = callback;
    }


}
