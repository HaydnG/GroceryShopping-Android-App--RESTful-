package uk.co.HaydnG.RESTful.Services;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import uk.co.HaydnG.Activity.HomeActivity;
import uk.co.HaydnG.Activity.OrderActivity;
import uk.co.HaydnG.Activity.Template.ProdActivityTemplate;
import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.OrderDTO;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class PlaceOrderService extends UrlReaderCallback {

    private ProdActivityTemplate Main;
    private UserDTO User;

    public static final int INCREMENT_CART = 1;
    public static final int DECREMENT_CART = 0;

    public PlaceOrderService(ProdActivityTemplate m, UserDTO User){
        this.Main = m;
        this.User = User;

    }

    public void PlaceOrder(ArrayList<ProductDTO> Products){
        ServiceController SC = new ServiceController(this, Main);

        OrderDTO order = new OrderDTO();
        order.setOrderProducts(Products);
        order.setUser(User);
        order.setOrderDate(new java.sql.Timestamp(new java.util.Date().getTime()));

        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();



        SC.execute("Order/Place", SC.AUTH,SC.POST,User.getUsername(), User.getPassword().getPassword(),g.toJson(order));
    }

    @Override
    public void onUrlReaderFinished(String result) {

        if(result != null) {
            System.out.println("ECHO: " + result);

            DTOConverter DTOCon = new DTOConverter();
            OrderDTO Order = DTOCon.JsonArrayToOrderDTO(result);

            if(Order.getOrderID() > 0){

                Intent intent = new Intent(Main, OrderActivity.class);
                intent.putExtra("Order", Order);
                intent.putExtra("User", User);
                Main.startActivity(intent);

            }





        }
    }
}
