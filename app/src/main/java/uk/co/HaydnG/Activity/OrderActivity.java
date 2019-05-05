package uk.co.HaydnG.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.uk.R;

import java.util.ArrayList;

import uk.co.HaydnG.Activity.Template.ActivityTemplate;
import uk.co.HaydnG.Activity.Template.ProdActivityTemplate;
import uk.co.HaydnG.DTO.OrderDTO;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Navigation.Navigation;
import uk.co.HaydnG.RESTful.Services.GetCartService;
import uk.co.HaydnG.RESTful.Services.PlaceOrderService;
import uk.co.HaydnG.ViewAdapter.ProductViewAdapter;

public class OrderActivity extends ProdActivityTemplate {



    private UserDTO User = null;
    private OrderDTO Order = null;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<ProductDTO> Products;

    private Navigation nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setNavigation(new Navigation(this, Navigation.CART_ACTIVITY).SetupNaviugation());
        Intent intent = getIntent();
        setUser((UserDTO)intent.getParcelableExtra("User"));
        Order = (OrderDTO) intent.getParcelableExtra("Order");



        recyclerView = findViewById(R.id.product_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        TextView orderdate = findViewById(R.id.order_date);
        orderdate.setText(Order.getOrderDate().toString());

        if(Order != null && Order.getOrderProducts() != null){


            loadProductList(Order.getOrderProducts());
        }


    }


    @Override
    public void loadProductList(final ArrayList<ProductDTO> products) {



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProductViewAdapter padapter = new ProductViewAdapter(products,OrderActivity.this, R.layout.order_product);
                padapter.UpdateCartFooter();
                recyclerView.setAdapter(padapter);
            }
        });
    }

}
