package uk.co.HaydnG.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.uk.R;

import java.util.ArrayList;

import uk.co.HaydnG.Activity.Template.ActivityTemplate;
import uk.co.HaydnG.Activity.Template.ProdActivityTemplate;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Navigation.Navigation;
import uk.co.HaydnG.RESTful.Services.GetCartService;
import uk.co.HaydnG.RESTful.Services.GetProductsService;
import uk.co.HaydnG.RESTful.Services.PlaceOrderService;
import uk.co.HaydnG.ViewAdapter.ProductViewAdapter;

public class CartActivity extends ProdActivityTemplate {



    private UserDTO User = null;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<ProductDTO> Products;

    private Navigation nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        setNavigation(new Navigation(this, Navigation.CART_ACTIVITY).SetupNaviugation());

        Intent intent = getIntent();
        setUser((UserDTO)intent.getParcelableExtra("User"));




        recyclerView = findViewById(R.id.product_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);





        GetCartService GS = new GetCartService(this ,getUser());
        GS.GetCart();


    }

    @Override
    public void loadProductList(final ArrayList<ProductDTO> products) {

        setProducts(products);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProductViewAdapter padapter = new ProductViewAdapter(products,CartActivity.this, R.layout.cart_product);
                padapter.UpdateCartFooter();
                recyclerView.setAdapter(padapter);
            }
        });
    }

    public void PlaceOrder(View v){
        int totalnum = 0;
        for(ProductDTO p : Products){
            System.out.println(p.getName() + " Cart: " + p.getNumInCart());
            totalnum += p.getNumInCart();

        }
        if(Products.size() > 0 && totalnum > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Order confirmation");
            builder.setMessage("Are you sure you want to order this?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PlaceOrderService POS = new PlaceOrderService(CartActivity.this, getUser());
                            POS.PlaceOrder(CartActivity.this.Products);

                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void setProducts(ArrayList<ProductDTO> products) {
        Products = products;
    }
}
