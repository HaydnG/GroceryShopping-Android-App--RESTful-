package uk.co.HaydnG.RESTful.Services;

import com.google.gson.Gson;

import uk.co.HaydnG.Activity.Template.ActivityTemplate;
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


        User.getPassword().setPassword2(null);
        Gson g = new Gson();


        SC.execute("User/Register", SC.NO_AUTH,SC.POST,null, null ,g.toJson(User));
    }

    @Override
    public void onUrlReaderFinished(String result) {

        if(result != null) {
            System.out.println("ECHO: " + result);

        }
    }
}
