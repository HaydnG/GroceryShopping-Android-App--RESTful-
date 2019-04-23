package uk.co.HaydnG.RESTful.Services;

import uk.co.HaydnG.Activity.ActivityTemplate;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Activity.HomeActivity;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class GetCartService extends UrlReaderCallback {

    private ActivityTemplate Main;
    private UserDTO User;

    public static final int INCREMENT_CART = 1;
    public static final int DECREMENT_CART = 0;

    public GetCartService(ActivityTemplate m, UserDTO User){
        this.Main = m;
        this.User = User;

    }

    public void ModifyCart(int ProductID, int Mode){
        ServiceController SC = new ServiceController(this, Main);


        SC.execute("Cart/Get",SC.AUTH,SC.GET, this.User.getUsername(),this.User.getPassword().getPassword());


    }


    @Override
    public void onUrlReaderFinished(String result) {
        if(result != null) {


        }
    }
}
