package uk.co.HaydnG.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uk.R;

import uk.co.HaydnG.DTO.AddressDTO;
import uk.co.HaydnG.DTO.PasswordDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.RESTful.Services.RegisterService;
import uk.co.HaydnG.RESTful.Services.UserService;


public class RegisterActivity extends ActivityTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);
    }

    public void LoginOnClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void RegisterOnClick(View view){
        TextView email = (TextView) this.findViewById(R.id.email);
        TextView username = (TextView) this.findViewById(R.id.username);
        TextView password1 = (TextView) this.findViewById(R.id.password);
        TextView password2 = (TextView) this.findViewById(R.id.password2);
        TextView first_name = (TextView) this.findViewById(R.id.first_name);
        TextView last_name = (TextView) this.findViewById(R.id.last_name);
        UserDTO User = new UserDTO();
        User.setUsername(username.getText().toString());

        PasswordDTO Password = new PasswordDTO();
        Password.setPassword(password1.getText().toString());
        Password.setPassword2(password2.getText().toString());

        User.setPassword(Password);
        User.setFName(first_name.getText().toString());
        User.setLName(last_name.getText().toString());





        TextView address = (TextView) this.findViewById(R.id.address);
        TextView city = (TextView) this.findViewById(R.id.city);
        TextView county = (TextView) this.findViewById(R.id.county);
        TextView postcode = (TextView) this.findViewById(R.id.postcode);
        AddressDTO Address = new AddressDTO();
        Address.setEmail(email.getText().toString());
        Address.setAddrline1(address.getText().toString());
        Address.setCity(city.getText().toString());
        Address.setCounty(county.getText().toString());
        Address.setZipCode(postcode.getText().toString());

        User.setAddress(Address);

        if(User.getPassword().getPassword().equals(User.getPassword().getPassword2())){

            if(!User.getPassword().getPassword().contains(":") || !User.getUsername().contains(":")){
                if(User.getPassword().getPassword().length() >=6){
                    RegisterService RS = new RegisterService(this);
                    RS.Register(User);

                }else{
                    Toast.makeText(getApplicationContext(), "Password must be longer than 6 characters", Toast.LENGTH_SHORT).show();
                }

            }else{

                Toast.makeText(getApplicationContext(), "Username or password cannot contain ':'", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
        }




    }
}
