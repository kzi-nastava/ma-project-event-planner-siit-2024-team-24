package com.example.eventio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class HomePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_page, container, false);

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

        Button btnOpenSearchEvent = view.findViewById(R.id.buttonSearchEvents);
        btnOpenSearchEvent.setOnClickListener(v -> {
            EventFilterFragment dialogFragment = new EventFilterFragment();
            dialogFragment.show(getParentFragmentManager(), "EventFilterFragment");
        });

        Button btnOpenSearchProductsServices = view.findViewById(R.id.buttonSearchProductsServices);
        btnOpenSearchProductsServices.setOnClickListener(v -> {
            ProductServiceFilerFragment dialogFragment = new ProductServiceFilerFragment();
            dialogFragment.show(getParentFragmentManager(), "ProductServiceFilerFragment");
        });

        return view;
    }

    private void loadFragment(int frameId, Fragment fragment) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(frameId, fragment)
                .commit();

        Log.d("FragmentLoad", "Fragment loaded into container: " + frameId);
    }
}
