package uk.co.HaydnG.Navigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.uk.R;

import uk.co.HaydnG.Activity.MainActivity;
import uk.co.HaydnG.Activity.Template.ActivityTemplate;
import uk.co.HaydnG.Activity.CartActivity;
import uk.co.HaydnG.Activity.HomeActivity;

public class Navigation {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ActivityTemplate App;

    public static final int HOME_ACTIVITY = 0;
    public static final int CART_ACTIVITY = 1;
    public static final int SETTINGS_ACTIVITY = 1;
    public static final int ACCOUNT_ACTIVITY = 1;


    private int CurrentPage;

    public Navigation(ActivityTemplate App, int CurrentPage){
        this.App = App;
        this.CurrentPage = CurrentPage;
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return actionBarDrawerToggle;
    }

    public Navigation SetupNaviugation(){



        drawerLayout = App.findViewById(R.id.home_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(App,drawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerView.bringToFront();
                drawerView.requestLayout();
                System.out.println("test");
            }

        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        App.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        App.getSupportActionBar().setHomeButtonEnabled(true);



        NavigationView navigationView = App.findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                System.out.println("Test");
                Intent intent = null;

                switch (id) {
                    case R.id.account:
                        if(CurrentPage ==  ACCOUNT_ACTIVITY){
                            break;
                        }

                        Toast.makeText(App, "Account CLICKED", Toast.LENGTH_SHORT).show();
                        System.out.println("Account");
                        break;
                    case R.id.mycart:
                        if(CurrentPage ==  CART_ACTIVITY){
                            break;
                        }
                        Toast.makeText(App, "CART CLICKED", Toast.LENGTH_SHORT).show();

                        intent = new Intent(App, CartActivity.class);
                        break;
                    case R.id.settings:
                        if(CurrentPage ==  SETTINGS_ACTIVITY){
                            break;
                        }
                        Toast.makeText(App, "SETTINGS CLICKED", Toast.LENGTH_SHORT).show();
                        System.out.println("Settings");

                        break;
                    case R.id.products:
                        if(CurrentPage ==  HOME_ACTIVITY){
                            break;
                        }
                        Toast.makeText(App, "HOME CLICKED", Toast.LENGTH_SHORT).show();

                        intent = new Intent(App, HomeActivity.class);
                        App.startActivity(intent);
                        break;
                    case R.id.logout:
                        intent = new Intent(App, MainActivity.class);
                        App.startActivity(intent);

                        break;

                }

                if(intent != null){
                    intent.putExtra("User", App.getUser());
                    App.startActivity(intent);

                }


                return true;
            }
        });


        return this;
    }

}
