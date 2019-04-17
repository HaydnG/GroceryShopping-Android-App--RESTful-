package uk.co.HaydnG.RESTful.Services;

import android.content.Intent;
import android.widget.Toast;

import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.HomeActivity;
import uk.co.HaydnG.MainActivity;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class ProductService implements UrlReaderCallback {

    private MainActivity Main;
    private UserDTO User;

    public ProductService(MainActivity m, UserDTO User){
        this.Main = m;

    }

    public void GetProducts(String search, int StoreID){

        ServiceController SC = new ServiceController(this);

        if(StoreID >= -1){
            SC.execute("Product/All/"+search, this.User.getUsername(),this.User.getPassword().getPassword(), SC.GET);

        }else{
            SC.execute("Product/All/"+search+"/Store/"+StoreID, this.User.getUsername(),this.User.getPassword().getPassword(), SC.GET);

        }


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

                    if (User != null) {
                        System.out.println("UserDTO: " + User.getFName() + " " + User.getLName());

                        Intent intent = new Intent(Main, HomeActivity.class);
                        Main.startActivity(intent);


                    }

                    break;
            }
        }




    }
}
