package toolbarmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appstone.androidbatch8.R;
import com.google.android.material.navigation.NavigationView;

public class ToolbarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        Toolbar toolbar = findViewById(R.id.tl_toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }


        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(ToolbarActivity.this, mDrawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        TextView usernameText = headerView.findViewById(R.id.textView8);
        usernameText.setText("Hello Android Developer");

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if(item.getItemId() == R.id.menu_logout){
//                    Toast.makeText(ToolbarActivity.this, "Menu Logout is clicked", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_toolbar, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            onBackPressed();
//        } else if (item.getItemId() == R.id.menu_logout) {
//            Toast.makeText(ToolbarActivity.this, "Logout is clicked", Toast.LENGTH_SHORT).show();
//        }

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_logout:
                Toast.makeText(ToolbarActivity.this, "Logout is clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.menu_logout){
            Toast.makeText(ToolbarActivity.this, "Menu Logout is clicked", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}