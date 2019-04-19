package uk.co.HaydnG;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uk.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.RESTful.Services.ProductService;
import uk.co.HaydnG.ViewAdapter.ProductViewAdapter;
import uk.co.HaydnG.ViewHolder.ProductViewHolder;

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

        ProductService PS = new ProductService(this ,User);
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

                ProductViewAdapter padapter = new ProductViewAdapter(products,getApplicationContext(),getLayoutInflater()){

                    @Override
                    public void onClick(View v) {

                        Toast.makeText(HomeActivity.this, "Add item to card ID: " + v.getId() , Toast.LENGTH_SHORT).show();
                    }
                };


                recyclerView.setAdapter(padapter);
            }
        });


    }

}
