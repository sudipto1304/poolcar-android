package com.poolcar.activity.startup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.poolcar.R;
import com.poolcar.activity.common.OuterBaseActivity;
import com.poolcar.component.PCEditText;
import com.poolcar.model.UserProfileData;
import com.poolcar.utils.AppUtils;
import com.poolcar.utils.Validator;

import org.apache.commons.lang3.StringUtils;

public class RegistrationActivity extends OuterBaseActivity {

    private static final String TAG = RegistrationActivity.class.getName();

    private PCEditText userId;
    private PCEditText emailId;
    private PCEditText reEmailId;
    private PCEditText password;
    private PCEditText rePassword;
    private PCEditText firstName;
    private PCEditText lastName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration, R.string.createAccountTitle, true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userId = findViewById(R.id.userIdText);
        userId.setDigit("0123456789_qwertyuiopasdfghjklzxcvbnm");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.continue_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.continueMenu){
            if(!isInactive()) {
                if(validateFields()) {
                    UserProfileData profileData = new UserProfileData();
                    profileData.setUserId(userId.getText());
                    profileData.setEmailId(emailId.getText());
                    profileData.setPassword(password.getText());
                    profileData.setFirstName(firstName.getText());
                    profileData.setLastName(lastName.getText());
                    Intent intent = new Intent(this, AddressAndPhoneActivity.class);
                    intent.putExtra(DATA_USER_PROFILE, profileData);
                    startActivity(intent);
                }

            }
        }else
            onBackPressed();

        return true;
    }


    @Override
    public void onBackPressed() {
        View view = this.getCurrentFocus();
        if(view!=null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
        if(!isInactive()) {
            Intent intent = new Intent(this, AppInitActivity.class);
            startActivity(intent);
        }
    }

    private boolean validateFields(){
        emailId  = findViewById(R.id.emailIdText);
        reEmailId = findViewById(R.id.reEmailIdText);
        password = findViewById(R.id.passwordText);
        rePassword = findViewById(R.id.rePasswordText);
        firstName = findViewById(R.id.firstNameText);
        lastName = findViewById(R.id.lastNameText);
        if(StringUtils.isEmpty(userId.getText()) || StringUtils.isEmpty(emailId.getText()) || StringUtils.isEmpty(reEmailId.getText()) || StringUtils.isEmpty(password.getText()) || StringUtils.isEmpty(rePassword.getText()) || StringUtils.isEmpty(firstName.getText()) || StringUtils.isEmpty(lastName.getText())){
            showError(getResources().getString(R.string.blank_text_error));
            return false;
        }
        if(!emailId.getText().equalsIgnoreCase(reEmailId.getText())){
            reEmailId.setError(getResources().getString(R.string.email_mismatch_error));
            AppUtils.vibrate(this);
            return false;
        }
        if(!password.getText().equals(rePassword.getText())){
            rePassword.setError(getResources().getString(R.string.password_mismatch_error));
            AppUtils.vibrate(this);
            return false;
        }
        if(!Validator.validateMinLength(userId.getText(), 6)){
            userId.setError(getResources().getString(R.string.text_min_length_error)+ "6 "+getResources().getString(R.string.character));
            AppUtils.vibrate(this);
            return false;
        }
        if(!Validator.validateMinLength(password.getText(), 8)){
            password.setError(getResources().getString(R.string.text_min_length_error)+ "8 "+getResources().getString(R.string.character));
            AppUtils.vibrate(this);
            return false;
        }
        if(!Validator.validateEmailId(emailId.getText())){
            emailId.setError(getResources().getString(R.string.email_id_error));
            AppUtils.vibrate(this);
            return false;
        }
        return true;
    }




}
