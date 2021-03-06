package uk.co.HaydnG.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.example.uk.R;

import java.util.ArrayList;

import uk.co.HaydnG.Activity.Template.ActivityTemplate;
import uk.co.HaydnG.Activity.Template.ProdActivityTemplate;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Navigation.Navigation;
import uk.co.HaydnG.RESTful.Services.GetProductsService;
import uk.co.HaydnG.ViewAdapter.ProductViewAdapter;

public class HomeActivity extends ProdActivityTemplate {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setNavigation(new Navigation(this, Navigation.HOME_ACTIVITY).SetupNaviugation());


        Intent intent = getIntent();
        setUser((UserDTO)intent.getParcelableExtra("User"));

        recyclerView = findViewById(R.id.product_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);




        TextView search = findViewById(R.id.search_box);
        GetProductsService PS = new GetProductsService(HomeActivity.this ,getUser());
        PS.GetProducts("", -1);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                GetProductsService PS = new GetProductsService(HomeActivity.this ,getUser());
                PS.GetProducts(s.toString(), -1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });







    }

    @Override
    public void loadProductList(final ArrayList<ProductDTO> products) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProductViewAdapter padapter = new ProductViewAdapter(products,HomeActivity.this, R.layout.product);
                recyclerView.setAdapter(padapter);
            }
        });
    }
}
