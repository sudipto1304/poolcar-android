package com.poolcar.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poolcar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActionMenuFragment extends Fragment {


    public ActionMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_action_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView greeting = getActivity().findViewById(R.id.greetingText);
        greeting.setText("Good evening,");
        TextView greetingName = getActivity().findViewById(R.id.greetingName);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Ubuntu-M.ttf");
        greetingName.setTypeface(face);
        greetingName.setText("Sudipto");
    }
}
