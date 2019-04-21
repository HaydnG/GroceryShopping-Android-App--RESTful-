package uk.co.HaydnG.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uk.R;

import java.util.ArrayList;

import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Navigation.Navigation;
import uk.co.HaydnG.RESTful.Services.CountCartProdService;
import uk.co.HaydnG.RESTful.Services.ModifyCartService;
import uk.co.HaydnG.RESTful.Services.GetProductsService;
import uk.co.HaydnG.ViewAdapter.ProductViewAdapter;

public class HomeActivity extends ActivityTemplate  {

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





        GetProductsService PS = new GetProductsService(this ,getUser());
        PS.GetProducts("apple", -1);



    }


    public void loadProductList(final ArrayList<ProductDTO> products) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProductViewAdapter padapter = new ProductViewAdapter(products,HomeActivity.this, new ProductViewAdapter.ProductAdapterListener(){

                    @Override
                    public void AddToCartOnClick(View v, int position) {
                        ProductDTO product = products.get(position);
                        ModifyCartService PS = new ModifyCartService(HomeActivity.this, getUser());

                        PS.ModifyCart(product.getID(), PS.INCREMENT_CART);

                        TextView CartNumText = (TextView) v.getTag();
                        CountCartProdService CountCart = new CountCartProdService(HomeActivity.this, getUser(), CartNumText).GetCount(product.getID());

                        if(product.getNumInCart() == 0){
                            Toast.makeText(HomeActivity.this, "Added " + product.getName() + " to cart! ", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(HomeActivity.this, "+1 " + product.getName() + " in cart!" , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void RemoveFromCartOnClick(View v, int position) {
                        ProductDTO product = products.get(position);
                        ModifyCartService PS = new ModifyCartService(HomeActivity.this, getUser());

                        PS.ModifyCart(product.getID(), PS.DECREMENT_CART);

                        TextView CartNumText = (TextView) v.getTag();
                        CountCartProdService CountCart = new CountCartProdService(HomeActivity.this, getUser(), CartNumText).GetCount(product.getID());
                    }
                });
                recyclerView.setAdapter(padapter);
            }
        });
    }
}
