package uk.co.HaydnG.RESTful.Services;

import android.widget.TextView;

import uk.co.HaydnG.Activity.ActivityTemplate;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Activity.HomeActivity;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;

public class CountCartProdService extends UrlReaderCallback {

    private ActivityTemplate Main;
    private UserDTO User;
    private TextView view;

    public static final int INCREMENT_CART = 1;
    public static final int DECREMENT_CART = 0;

    public CountCartProdService(ActivityTemplate m, UserDTO User, TextView view){
        this.Main = m;
        this.User = User;
        this.view = view;

    }

    public CountCartProdService GetCount(int ProductID){
        ServiceController SC = new ServiceController(this, Main);

        SC.execute("Cart/Get/"+ProductID, this.User.getUsername(),this.User.getPassword().getPassword(), SC.GET);

        return this;
    }


    @Override
    public void onUrlReaderFinished(String result) {
        if(result != null) {
            view.setText(result);
        }
    }
}
