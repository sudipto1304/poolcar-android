package com.poolcar.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.poolcar.R;
import com.poolcar.activity.BaseActivity;
import com.poolcar.activity.OuterBaseActivity;
import com.poolcar.utils.AppConstant;

import java.net.URI;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NotificationFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RelativeLayout mLayout = getActivity().findViewById(R.id.notificationLayout);
        mLayout.bringToFront();
        final LinearLayout notificationLayout = getActivity().findViewById(R.id.notificationTextLayout);
        TextView notificationText = getActivity().findViewById(R.id.notificationText);
        if(this.getArguments().getString(AppConstant.NOTIFICATION_TYPE).equals(AppConstant.ERROR_NOTIFICATION)){
            notificationLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.errorRed));
            notificationText.setText(getArguments().getString(AppConstant.NOTIFICATION_TEXT));
            showBanner(notificationLayout);
        }
        else if(this.getArguments().getString(AppConstant.NOTIFICATION_TYPE).equals(AppConstant.SUCCESS_NOTIFICATION)){
            notificationLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.successGreen));
            notificationText.setText(getArguments().getString(AppConstant.NOTIFICATION_TEXT));
            showBanner(notificationLayout);
        }

    }


    private void showBanner(final LinearLayout notificationLayout){
        slideUp(notificationLayout);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run() {
                slideDown(notificationLayout);
            }
        }, 2000);
    }


    public void slideUp(LinearLayout view){
        Animation bottomUp = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide_up);
        view.startAnimation(bottomUp);
    }

    public void slideDown(LinearLayout view){
        ((BaseActivity)getActivity()).cancelFragment("NOTIFICATION");
    }
}
