package com.poolcar.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.poolcar.R;
import com.poolcar.activity.common.BaseActivity;
import com.poolcar.callbacks.KeyboardCallBack;
import com.poolcar.component.Keyboard;
import com.poolcar.component.PhoneNumberField;
import com.poolcar.utils.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OTPFragment extends Fragment {


    private TextView changePhoneText;

    public OTPFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        changePhoneText = getActivity().findViewById(R.id.changePhoneNumber);
        changePhoneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhoneNumberChangeDialog();
            }
        });
    }

    private void showPhoneNumberChangeDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.fragment_phone_verify_input);

        final PhoneNumberField phoneNumber =dialog.findViewById(R.id.phoneNumberField);
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




        dialog.show();
    }





}
