package com.poolcar.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface AppConstant {
    String NOTIFICATION_TYPE = "notificationType";
    String ERROR_NOTIFICATION = "ERROR";
    String SUCCESS_NOTIFICATION = "SUCCESS";
    String ACTION_SHEET = "ACTIONSHEET";
    String CUSTOM_NOTIFICATION = "CUSTOM";
    String NOTIFICATION_TEXT = "notificationText";
    String NOTIFICATION_COMPONENT = "notificationComponent";
    String PHONE_VALIDATOR_REGEX = "\\(\\d{3}\\)-\\d{3}-\\d{4}";
    String EMAIL_PATTERN ="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    String SIGNED_USER_ID = "signedInUserId";
    String SIGNED_EMAIL_ID = "signedInEmailId";
    String PASSWORD = "clientPassword";
    String ADDRESS_DATA_ERROR = "addressDataError";
    String LOCATION_ADDRESS = "locationAddress";
    int LOCATION_ACCESS_CODE = 401;
    int SUCCESS_RESULT = 0;
    int FAILURE_RESULT = 1;
    String PACKAGE_NAME =
            "com.poolcar";
    String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";
    String LOCATION_RESULT_RECEIVER = "locationResultReceiver";
    String ANDROID_APP_ID = "1004813";

    String DATA_USER_ID = "userIdData";
    String DATA_EMAIL_ID = "emailIdData";
    String DATA_PASSWORD = "passwordData";
    String DATA_FIRST_NAME = "firstNameData";
    String DATA_LAST_NAME = "lastNameData";
    String OTP_INTENT_FILTER = "android.poolcar.intent.action.OTP";
    String DATA_OTP_STRING = "OTPString";
    String DATA_OTP_RETURN_LISTENER = "otpReturnListener";
    String DATA_OTP_RETURN_RESPONSE = "otpReturnResponse";
    String DATA_USER_PROFILE = "userProfileData";
    int OTP_REQUEST_CODE = 26;
    int HTTP_SERVER_ERROR = 500;
    int HTTP_AUTH_REQUIRED = 213;
    String DATA_AUTHORIZATION = "authorization";
    String DATA_STATUS = "status";
    String DATA_AUTH_REQUIRED = "isAuthRequired";
    List<Integer> HTTP_SUCCESS_STATUS_CODES = Arrays.asList(new Integer[]{200, 201});

}
