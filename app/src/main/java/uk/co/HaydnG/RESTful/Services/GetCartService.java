package uk.co.HaydnG.RESTful.Services;

import java.util.ArrayList;

import uk.co.HaydnG.Activity.Template.ActivityTemplate;
import uk.co.HaydnG.Activity.Template.ProdActivityTemplate;
import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class GetCartService extends UrlReaderCallback {

    private ProdActivityTemplate Main;
    private UserDTO User;

    public static final int INCREMENT_CART = 1;
    public static final int DECREMENT_CART = 0;

    public GetCartService(ProdActivityTemplate m, UserDTO User){
        this.Main = m;
        this.User = User;

    }

    public void GetCart(){
        ServiceController SC = new ServiceController(this, Main);


        SC.execute("Cart/Get",SC.AUTH,SC.GET, this.User.getUsername(),this.User.getPassword().getPassword());


    }


    @Override
    public void onUrlReaderFinished(String result) {
        if(result != null) {


            DTOConverter Parser = new DTOConverter();

            ArrayList<ProductDTO> Products = Parser.JsonArrayToProductDTOArray(result);

            if (User != null) {
                Main.loadProductList(Products);

                for(ProductDTO p : Products){
                    System.out.println("\n ProductName: " + p.getName());

                }


            }



        }
    }
}
