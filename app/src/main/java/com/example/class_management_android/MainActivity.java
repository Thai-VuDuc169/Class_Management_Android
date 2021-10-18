package com.example.class_management_android;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    // Initialize variable
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign variable
        bottomNavigation = findViewById(R.id.bottom_navigation);
        // Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_about));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // Initialize fragment
                Fragment fragment = null;
                // Check condition
                switch (item.getId()){
                    case 1:
                        // When id is 1, initialize notification fragment
                        fragment = new NotificationFragment();
                        break;
                    case 2:
                        // When id is 2, initialize home fragment
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        // When id is 3, initialize about fragment
                        fragment = new AboutFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        // Set notification count
        bottomNavigation.setCount(1, "10");
        // Set home fragment initially selected
        bottomNavigation.show(2, true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item)
            {
                // Display toast
                Toast.makeText(getApplicationContext(),"You clicked " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item)
            {
                // Display toast
                Toast.makeText(getApplicationContext(),"You reselected " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment)
    {
        // Replace fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}