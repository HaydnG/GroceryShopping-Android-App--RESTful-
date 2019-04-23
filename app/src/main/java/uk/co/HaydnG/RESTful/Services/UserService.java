package uk.co.HaydnG.RESTful.Services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import uk.co.HaydnG.Activity.ActivityTemplate;
import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.PasswordDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Activity.HomeActivity;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class UserService extends UrlReaderCallback {

    private ActivityTemplate Main;
    private String password = null;

    public UserService (ActivityTemplate m){
        this.Main = m;

    }

    public void Login(String username, String password){
        this.password = password;
        ServiceController SC = new ServiceController(this, Main);

        SC.execute("User",SC.AUTH,SC.GET, username, password);
    }

    @Override
    public void onUrlReaderFinished(String result) {

        if(result != null) {

            DTOConverter Parser = new DTOConverter();

            UserDTO User = Parser.JsonTOUserDTO(result);
            PasswordDTO Password = new PasswordDTO();
            Password.setPassword(this.password);
            User.setPassword(Password);

            if (User != null) {
                System.out.println("UserDTO: " + User.getFName() + " " + User.getLName());

                Intent intent = new Intent(Main, HomeActivity.class);
                intent.putExtra("User", User);
                Main.startActivity(intent);
            }
        }
    }
}
