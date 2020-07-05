package com.example.uipractice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebViewFragment;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        loadBottomFragment(new BottomHomeFragment());
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        if (id == R.id.nav_item_wish_list) {
            fragment = new NavigationWishlistFragment();
        } else if (id == R.id.nav_item_notification_setting) {
            fragment = new NavigationNotificationSettingFragment();
        } else if (id == R.id.nav_item_privacy_policy) {
            fragment = new NavigationPrivacyPolicyFragment();
        } else if (id == R.id.nav_item_frequently_asked_questions) {
            fragment = new NavigationFrequentlyAskedQuestionsFragment();
        } else if (id == R.id.nav_item_legal_information) {
            fragment = new NavigationLegalInformationFragment();
        } else if (id == R.id.nav_item_rate_our_app) {
            fragment = new NavigationRateOurAppFragment();
        } else if (id == R.id.nav_item_make_a_suggestion) {
            fragment = new NavigationMakeASuggestionFragment();
            fragment.setArguments(bundle);
        } else if (id == R.id.nav_item_profile_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        } else if (id == R.id.action_home){
            fragment = new BottomHomeFragment();
        } else if (id == R.id.action_search){
            fragment = new BottomSearchFragment();
        } else if (id == R.id.action_wishlist){
            fragment = new BottomWishlistFragment();
        } else if (id == R.id.action_more){
            fragment = new BottomNearbyStoresFragment();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private boolean loadBottomFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}
