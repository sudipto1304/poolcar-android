package com.poolcar.utils;

public interface AppConstant {
    String NOTIFICATION_TYPE = "notificationType";
    String ERROR_NOTIFICATION = "ERROR";
    String SUCCESS_NOTIFICATION = "SUCCESS";
    String CUSTOM_NOTIFICATION = "CUSTOM";
    String NOTIFICATION_TEXT = "notificationText";
    String PHONE_VALIDATOR_REGEX = "\\(\\d{3}\\)-\\d{3}-\\d{4}";
    String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    String SIGNED_USER_ID = "signedInUserId";
    String SIGNED_EMAIL_ID = "signedInEmailId";
    String PASSWORD = "clientPassword";
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

}
