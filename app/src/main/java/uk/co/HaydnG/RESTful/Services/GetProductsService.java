package uk.co.HaydnG.RESTful.Services;

import java.util.ArrayList;

import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Activity.HomeActivity;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class GetProductsService extends UrlReaderCallback {

    private HomeActivity Main;
    private UserDTO User;


    public GetProductsService(HomeActivity m, UserDTO User){
        this.Main = m;
        this.User = User;

    }



    public void GetProducts(String search, int StoreID){

        ServiceController SC = new ServiceController(this, Main);

        if(StoreID >= -1){
            SC.execute("Product/All/"+search, SC.AUTH,SC.GET,this.User.getUsername(),this.User.getPassword().getPassword());

        }else{
            SC.execute("Product/All/"+search+"/Store/"+StoreID, SC.AUTH,SC.GET,this.User.getUsername(),this.User.getPassword().getPassword());

        }


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
