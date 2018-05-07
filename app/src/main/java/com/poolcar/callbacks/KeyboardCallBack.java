package com.poolcar.callbacks;

public interface KeyboardCallBack {

    void onType(String key);
    void onBackSpace();
    void onTypeDot();
    void cancelKeyboard();
}
