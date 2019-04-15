package HaydnG.co.uk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.uk.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginOnClick(View view){
        TextView username = (TextView) this.findViewById(R.id.Username);
        TextView password = (TextView) this.findViewById(R.id.Password);
        System.out.println(username.getText() + " "+ password.getText());

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
