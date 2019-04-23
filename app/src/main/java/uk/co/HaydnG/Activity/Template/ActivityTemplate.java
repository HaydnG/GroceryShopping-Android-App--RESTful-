package uk.co.HaydnG.Activity.Template;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.Navigation.Navigation;

public class ActivityTemplate  extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private Navigation navigation;
    private UserDTO User = null;

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public UserDTO getUser() {
        return User;
    }

    public void setUser(UserDTO user) {
        User = user;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return navigation.getActionBarDrawerToggle().onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
