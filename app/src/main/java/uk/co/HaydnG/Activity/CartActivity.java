package uk.co.HaydnG.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.uk.R;

import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Navigation.Navigation;

public class CartActivity extends ActivityTemplate {



    private UserDTO User = null;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private Navigation nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setNavigation(new Navigation(this, Navigation.CART_ACTIVITY).SetupNaviugation());
        Intent intent = getIntent();
        setUser((UserDTO)intent.getParcelableExtra("User"));





    }
}
