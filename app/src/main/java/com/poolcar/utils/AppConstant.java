package com.poolcar.utils;

public interface AppConstant {
    String NOTIFICATION_TYPE = "notificationType";
    String ERROR_NOTIFICATION = "ERROR";
    String SUCCESS_NOTIFICATION = "SUCCESS";
    String CUSTOM_NOTIFICATION = "CUSTOM";
    String NOTIFICATION_TEXT = "notificationText";
    String PHONE_VALIDATOR_REGEX = "\\(\\d{3}\\)-\\d{3}-\\d{4}";
    String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}
