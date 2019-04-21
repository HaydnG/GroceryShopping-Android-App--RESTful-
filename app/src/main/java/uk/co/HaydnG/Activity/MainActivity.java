package uk.co.HaydnG.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uk.R;

import uk.co.HaydnG.RESTful.Services.UserService;


public class MainActivity extends ActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginOnClick(View view){
        TextView username = (TextView) this.findViewById(R.id.Username);
        TextView password = (TextView) this.findViewById(R.id.Password);
        System.out.println(username.getText() + " "+ password.getText());

        if(username.getText().toString().length() >= 30){

            Toast.makeText(getApplicationContext(), "Username or Password incorrect", Toast.LENGTH_SHORT).show();
        }else if(password.getText().toString().length() >= 40){
            Toast.makeText(getApplicationContext(), "Username or Password incorrect", Toast.LENGTH_SHORT).show();

        }else{
            UserService US = new UserService(this); //On request finished, Goto HomeActivity
            US.Login(username.getText().toString(), password.getText().toString());

        }
    }
}
