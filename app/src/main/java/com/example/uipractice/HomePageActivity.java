package com.example.uipractice;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener , View.OnClickListener {
    private TextView textViewProfileName, textViewProfileEmail, textViewEditProfile;
    private ImageView imageViewProfilePicture;
    private FirebaseDatabase firebaseDatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        loadBottomFragment(new BottomHomeFragment());
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        textViewProfileName = headerView.findViewById(R.id.text_view_name_profile);
        textViewProfileEmail = headerView.findViewById(R.id.text_view_email_profile);
        textViewEditProfile = headerView.findViewById(R.id.text_view_edit_profile);
        imageViewProfilePicture = headerView.findViewById(R.id.image_view_profile);

        firebaseDatabase = FirebaseDatabase.getInstance();
        getData();

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
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HomePageActivity.super.onBackPressed();
                    }
                }).create().show();
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
    private void getData() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        String uid=  currentUser.getUid();

        firebaseDatabase.getReference("Users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    String userName = dataSnapshot.child("Username").getValue(String.class);
                    String userEmail = dataSnapshot.child("Email").getValue(String.class);


                    textViewProfileName.setText(userName);
                    textViewProfileEmail.setText(userEmail);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    @Override
    public void onClick(View v) {

    }
}
