package uk.co.HaydnG.RESTful.Services;

import android.content.Intent;

import com.google.gson.Gson;

import org.json.JSONObject;

import uk.co.HaydnG.Activity.ActivityTemplate;
import uk.co.HaydnG.Activity.HomeActivity;
import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.PasswordDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class RegisterService extends UrlReaderCallback {

    private ActivityTemplate Main;
    private String password = null;

    public RegisterService(ActivityTemplate m){
        this.Main = m;

    }

    public void Register(UserDTO User){
        this.password = password;
        ServiceController SC = new ServiceController(this, Main);

        Gson g = new Gson();


        SC.execute("User/Register", SC.NO_AUTH,SC.POST,g.toJson(User));
    }

    @Override
    public void onUrlReaderFinished(String result) {

        if(result != null) {
            System.out.println("ECHO: " + result);

        }
    }
}
