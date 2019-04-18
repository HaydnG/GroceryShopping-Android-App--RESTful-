package uk.co.HaydnG.RESTful.Services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.PasswordDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.HomeActivity;
import uk.co.HaydnG.MainActivity;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class UserService implements UrlReaderCallback {

    private AppCompatActivity Main;
    private String password = null;

    public UserService (AppCompatActivity m){
        this.Main = m;

    }

    public void Login(String username, String password){
        this.password = password;
        ServiceController SC = new ServiceController(this);

        SC.execute("User", username, password, SC.GET);
    }

    @Override
    public void onUrlReaderFinished(String result) {
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

                    break;
            }
        }




    }
}
