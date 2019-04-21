package uk.co.HaydnG.RESTful.Services;

import android.widget.Toast;

import java.util.ArrayList;

import uk.co.HaydnG.DTO.DTOConverter;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.HomeActivity;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class ModifyCartService extends UrlReaderCallback {

    private HomeActivity Main;
    private UserDTO User;

    public static final int INCREMENT_CART = 1;
    public static final int DECREMENT_CART = 0;

    public ModifyCartService(HomeActivity m, UserDTO User){
        this.Main = m;
        this.User = User;

    }

    public void ModifyCart(int ProductID, int Mode){
        ServiceController SC = new ServiceController(this, Main);

        if(Mode == INCREMENT_CART){
            SC.execute("Cart/Increment/"+ProductID, this.User.getUsername(),this.User.getPassword().getPassword(), SC.POST);

        }else if(Mode == DECREMENT_CART){
            SC.execute("Cart/Decrement/"+ProductID, this.User.getUsername(),this.User.getPassword().getPassword(), SC.POST);

        }

    }


    @Override
    public void onUrlReaderFinished(String result) {

        if(result != null) {


        }
    }
}
