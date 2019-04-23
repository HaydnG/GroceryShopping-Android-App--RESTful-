package uk.co.HaydnG.RESTful.Services;

import uk.co.HaydnG.Activity.Template.ActivityTemplate;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class ModifyCartService extends UrlReaderCallback {

    private ActivityTemplate Main;
    private UserDTO User;

    public static final int INCREMENT_CART = 1;
    public static final int DECREMENT_CART = 0;

    public ModifyCartService(ActivityTemplate m, UserDTO User){
        this.Main = m;
        this.User = User;

    }

    public void ModifyCart(int ProductID, int Mode){
        ServiceController SC = new ServiceController(this, Main);

        if(Mode == INCREMENT_CART){
            SC.execute("Cart/Increment/"+ProductID, SC.AUTH,SC.PUT,this.User.getUsername(),this.User.getPassword().getPassword());

        }else if(Mode == DECREMENT_CART){
            SC.execute("Cart/Decrement/"+ProductID, SC.AUTH,SC.PUT,this.User.getUsername(),this.User.getPassword().getPassword());

        }

    }


    @Override
    public void onUrlReaderFinished(String result) {

        if(result != null) {


        }
    }
}
