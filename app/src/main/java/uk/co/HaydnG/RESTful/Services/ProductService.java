package uk.co.HaydnG.RESTful.Services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.HomeActivity;
import uk.co.HaydnG.MainActivity;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class ProductService implements UrlReaderCallback {

    private HomeActivity Main;
    private UserDTO User;

    public ProductService(HomeActivity m, UserDTO User){
        this.Main = m;
        this.User = User;

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

                    ArrayList<ProductDTO> Products = Parser.JsonArrayToProductDTOArray(result);

                    if (User != null) {
                        Main.loadProductList(Products);

                        for(ProductDTO p : Products){
                            System.out.println("\n ProductName: " + p.getName());

                        }


                    }

                    break;
            }
        }




    }
}
