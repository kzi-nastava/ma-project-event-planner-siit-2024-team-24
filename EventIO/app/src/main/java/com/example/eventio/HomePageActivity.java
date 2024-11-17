package com.example.eventio;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        loadFragment(R.id.frame1, new TopEventsFragment());
        loadFragment(R.id.frame2, new TopEventsFragment());
        loadFragment(R.id.frame3, new TopEventsFragment());
        loadFragment(R.id.frame4, new TopEventsFragment());
        loadFragment(R.id.frame5, new TopEventsFragment());

        loadFragment(R.id.frame1ps, new TopProductsAndServicesFragment());
        loadFragment(R.id.frame2ps, new TopProductsAndServicesFragment());
        loadFragment(R.id.frame3ps, new TopProductsAndServicesFragment());
        loadFragment(R.id.frame4ps, new TopProductsAndServicesFragment());
        loadFragment(R.id.frame5ps, new TopProductsAndServicesFragment());


        loadFragment(R.id.frame1ae, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_EVENT));
        loadFragment(R.id.frame2ae, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_EVENT));
        loadFragment(R.id.frame3ae, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_EVENT));
        loadFragment(R.id.frame4ae, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_EVENT));
        loadFragment(R.id.frame5ae, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_EVENT));

        loadFragment(R.id.frame1aps, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_SERVICE));
        loadFragment(R.id.frame2aps, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_SERVICE));
        loadFragment(R.id.frame3aps, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_SERVICE));
        loadFragment(R.id.frame4aps, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_SERVICE));
        loadFragment(R.id.frame5aps, CardViewFragment.newInstance(CardViewFragment.CARD_TYPE_SERVICE));


        Button btnOpenSearchEvent = findViewById(R.id.buttonSearchEvents);
        btnOpenSearchEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventFilterFragment dialogFragment = new EventFilterFragment();
                dialogFragment.show(getSupportFragmentManager(), "EventFilterFragment");

            }
        });

        Button btnOpenSearchProductsServices = findViewById(R.id.buttonSearchProductsServices);
        btnOpenSearchProductsServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductServiceFilerFragment dialogFragment = new ProductServiceFilerFragment();
                dialogFragment.show(getSupportFragmentManager(), "ProductServiceFilerFragment");

            }
        });



    }

    private void loadFragment(int frameId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameId, fragment)
                .commit();

        Log.d("FragmentLoad", "Fragment loaded into container: " + frameId);
    }
}