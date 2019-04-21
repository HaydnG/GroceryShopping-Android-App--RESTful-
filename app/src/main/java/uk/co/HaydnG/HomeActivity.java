package uk.co.HaydnG;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uk.R;

import java.util.ArrayList;
import java.util.Calendar;

import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.RESTful.Services.ModifyCartService;
import uk.co.HaydnG.RESTful.Services.GetProductsService;
import uk.co.HaydnG.ViewAdapter.ProductViewAdapter;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private ListView DrawerList;
    private DrawerLayout DrawerLayout;
    private ArrayAdapter<String> Adapter;
    private ActionBarDrawerToggle DrawerToggle;
    private String ActivityTitle;
    private boolean open = false;
    private UserDTO User = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerView = findViewById(R.id.product_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);



        Intent intent = getIntent();
        User = intent.getParcelableExtra("User");

        GetProductsService PS = new GetProductsService(this ,User);
        PS.GetProducts("apple", -1);


        drawerLayout = findViewById(R.id.home_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerView.bringToFront();
                drawerView.requestLayout();
            }

        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(34234234);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                System.out.println("Test");
                if(id == R.id.account){
                    Toast.makeText(HomeActivity.this, "Account CLICKED", Toast.LENGTH_SHORT).show();
                    System.out.println("Account");
                }else if(id == R.id.mycart){
                    Toast.makeText(HomeActivity.this, "CART CLICKED", Toast.LENGTH_SHORT).show();
                    System.out.println("Cart");
                }else if(id == R.id.settings){
                    Toast.makeText(HomeActivity.this, "SETTINGS CLICKED", Toast.LENGTH_SHORT).show();
                    System.out.println("Settings");

                }


                return true;
            }
        });





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void loadProductList(final ArrayList<ProductDTO> products) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProductViewAdapter padapter = new ProductViewAdapter(products,getApplicationContext(),getLayoutInflater(), new ProductViewAdapter.ProductAdapterListener(){

                    @Override
                    public void AddToCartOnClick(View v, int position) {
                        ProductDTO product = products.get(position);
                        ModifyCartService PS = new ModifyCartService(HomeActivity.this, User);

                        PS.ModifyCart(product.getID(), PS.INCREMENT_CART);

                        TextView CartNumText = (TextView) v.getTag();
                        product.setNumInCart(product.getNumInCart()+1);
                        CartNumText.setText(product.getNumInCart() + "");

                        if(product.getNumInCart() == 0){
                            Toast.makeText(HomeActivity.this, "Added " + product.getName() + " to cart! ", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(HomeActivity.this, "+1 " + product.getName() + " in cart!" , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void RemoveFromCartOnClick(View v, int position) {
                        ProductDTO product = products.get(position);
                        ModifyCartService PS = new ModifyCartService(HomeActivity.this, User);

                        PS.ModifyCart(product.getID(), PS.DECREMENT_CART);

                        if(!(product.getNumInCart() <= 0)){
                            TextView CartNumText = (TextView) v.getTag();
                            product.setNumInCart(product.getNumInCart()-1);
                            CartNumText.setText(product.getNumInCart() + "");
                        }
                    }
                });
                recyclerView.setAdapter(padapter);
            }
        });
    }
}
