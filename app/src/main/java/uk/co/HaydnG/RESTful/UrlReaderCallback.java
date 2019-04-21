package uk.co.HaydnG.RESTful;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.ProductDTO;

public abstract class UrlReaderCallback {
    public void StatusCodeHandler(String result, AppCompatActivity Main){
        System.out.println(result);
        if(result != null) {

            switch (result) {
                case "500":
                case "401":
                    System.out.println("USER NOT AUTHORIZED");
                    Toast.makeText(Main.getApplicationContext(), "Username or Password incorrect", Toast.LENGTH_SHORT).show();

                    break;
                case "-1":
                    System.out.println("SERVER ISSUE, PLEASE TRY AGAIN LATER");
                    Toast.makeText(Main.getApplicationContext(), "Server currently unavailible, please try again later", Toast.LENGTH_SHORT).show();

                    break;
                default:
                    onUrlReaderFinished(result);

                    break;
            }
        }
    };

    public abstract void onUrlReaderFinished(String string);

}

