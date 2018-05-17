package com.poolcar.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poolcar.R;
import com.poolcar.activity.common.BaseActivity;
import com.poolcar.callbacks.KeyboardCallBack;
import com.poolcar.component.Keyboard;
import com.poolcar.component.PhoneNumberField;
import com.poolcar.utils.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhoneVerifyInput.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhoneVerifyInput#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhoneVerifyInput extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG = this.getClass().getName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PhoneVerifyInput() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhoneVerifyInput.
     */
    // TODO: Rename and change types and number of parameters
    public static PhoneVerifyInput newInstance(String param1, String param2) {
        PhoneVerifyInput fragment = new PhoneVerifyInput();
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
        return inflater.inflate(R.layout.fragment_phone_verify_input, container, false);
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
        final PhoneNumberField phoneNumber =getActivity().findViewById(R.id.phoneNumberField);
        phoneNumber.getTextBoxObj().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Keyboard keyboard = new Keyboard(getContext(), true, false, new KeyboardCallBack(){

                    @Override
                    public void onType(String key) {
                        phoneNumber.setNumber(phoneNumber.getNumber()+key);
                        phoneNumber.setNumber(AppUtils.formatNumber(AppUtils.getCountryCode(getContext()), phoneNumber.getNumber()));
                    }

                    @Override
                    public void onBackSpace() {
                        if(!phoneNumber.getNumber().isEmpty()) {
                            phoneNumber.setNumber(phoneNumber.getNumber().substring(0, phoneNumber.getNumber().length() - 1));
                            phoneNumber.setNumber(AppUtils.formatNumber(AppUtils.getCountryCode(getContext()), phoneNumber.getNumber()));
                        }
                    }

                    @Override
                    public void onTypeDot() {

                    }

                    @Override
                    public void cancelKeyboard() {
                        ((BaseActivity)getActivity()).cancelFragment("ACTIONSHEET");
                    }
                });
                ((BaseActivity)getActivity()).showActionSheet(keyboard);
            }
        });




    }







}
