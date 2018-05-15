package com.poolcar.utils;

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
}
