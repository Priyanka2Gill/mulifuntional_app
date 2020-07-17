package com.example.admim.bottomnavigation.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admim.bottomnavigation.fragment.DashboardFragment;
import com.example.admim.bottomnavigation.fragment.HomeFragment;
import com.example.admim.bottomnavigation.fragment.NotificationFragment;
import com.example.admim.bottomnavigation.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigation_bottom;
    public ImageView back;
    Fragment fragment;
    TextView txt_title;
    AppBarLayout appBarLayout;
    private boolean doubleBackToExitPressedOnce = false;
    int navItemIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mTextMessage = (TextView) findViewById(R.id.message);


        txt_title = findViewById(R.id.toolbarTitle_);

        if (savedInstanceState == null) {

            fragment = new HomeFragment();
            loadFragment(fragment);
            txt_title.setText("Home");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = findViewById(R.id.appbarlayout);

        back = findViewById(R.id.back);
       /* back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SelectedItem = navigation_bottom.getSelectedItemId();
                if (R.id.navigation_notifications == SelectedItem) {
                    fragment = new NotificationFragment();
                    loadFragment(fragment);
                } else if (R.id.navigation_home == SelectedItem) {
                    fragment = new NotificationFragment();
                    loadFragment(fragment);

                }
                //onBackPressed();
            }
        });*/
        navigation_bottom = findViewById(R.id.navigation);
        navigation_bottom.setOnNavigationItemSelectedListener(itemSelectedListener);



    }
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    txt_title.setText("Home");
                    break;
                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
                    txt_title.setText("Dashboard");
                    break;
                case R.id.navigation_notifications:
                    fragment = new NotificationFragment();
                    txt_title.setText("Notications");
              /*      break;
                case R.id.bottom_navigation_contact_us:
                    fragment = new NotificationFragment();
//                    txt_title.setText("Contact Us");
                    break;
                case R.id.bottom_navigation_more:
                    fragment = new NotificationFragment();
//                    txt_title.setText("ACC");
                    break;*/

            }


            return loadFragment(fragment);
        }
    };


    @Override
    public void onBackPressed() {


        int SelectedItem = navigation_bottom.getSelectedItemId();

        if (R.id.navigation_home == SelectedItem) {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 1000);


        } else if (R.id.navigation_notifications == SelectedItem) {
            if (back.getVisibility() == View.VISIBLE) {
                fragment = new NotificationFragment();
                loadFragment(fragment);
            } else {
                fragment = new NotificationFragment();
                loadFragment(fragment);
                navigation_bottom.getMenu().getItem(0).setChecked(true);

            }
        } /*else if (R.id.navigation_home == SelectedItem) {
            if (back.getVisibility() == View.VISIBLE) {
                fragment = new NotificationFragment();
                loadFragment(fragment);
            } else {
                fragment = new NotificationFragment();
                loadFragment(fragment);
                navigation_bottom.getMenu().getItem(0).setChecked(true);

            }

        }*/ else {

            fragment = new HomeFragment();
            loadFragment(fragment);
            navigation_bottom.getMenu().getItem(0).setChecked(true);
        }


    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    private boolean addFragment(Fragment fragment) {
        fragment = new NotificationFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        return true;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*if(id == R.id.nav_view){
            return true;
        }*/

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void setTitleName(String title) {
        txt_title.setText(title);
    }



    public void setBackButtonVisible(boolean backButtonVisible) {
        if (backButtonVisible)
            back.setVisibility(View.VISIBLE);
        else
            back.setVisibility(View.GONE);
    }
    public void setToolbarImage(int drawableRes) {
        appBarLayout.setBackgroundResource(drawableRes);
    }
}
