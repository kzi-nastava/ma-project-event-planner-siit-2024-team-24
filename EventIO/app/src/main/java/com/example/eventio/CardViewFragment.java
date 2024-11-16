package com.example.eventio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardViewFragment extends Fragment {

    private static final String ARG_CARD_TYPE = "CARD_TYPE";
    public static final String CARD_TYPE_EVENT = "EVENT";
    public static final String CARD_TYPE_SERVICE = "SERVICE";
    private String cardType;


    public CardViewFragment() {
        // Required empty public constructor
    }
    public static CardViewFragment newInstance(String cardType) {
        CardViewFragment fragment = new CardViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CARD_TYPE, cardType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardType = getArguments().getString(ARG_CARD_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_view, container, false);

        // Dodavanje podfragmenta u svaki kontejner
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        Fragment fragment1;
        Fragment fragment2;
        Fragment fragment3;
        Fragment fragment4;

        if (CARD_TYPE_EVENT.equals(cardType)) {
            fragment1 = new EventCardFragment();
            fragment2 = new EventCardFragment();
            fragment3 = new EventCardFragment();
            fragment4 = new EventCardFragment();
        } else if (CARD_TYPE_SERVICE.equals(cardType)) {
            fragment1 = new ServiceProductCardFragment();
            fragment2 = new ServiceProductCardFragment();
            fragment3 = new ServiceProductCardFragment();
            fragment4 = new ServiceProductCardFragment();
        } else {
            throw new IllegalArgumentException("Unknown card type: " + cardType);
        }

        transaction.replace(R.id.fragmentContainer1, fragment1);
        transaction.replace(R.id.fragmentContainer2, fragment2);
        transaction.replace(R.id.fragmentContainer3, fragment3);
        transaction.replace(R.id.fragmentContainer4, fragment4);

        transaction.commit();

        return view;
    }
}