package com.sekim.citroscanner.Activty.UserInfo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PasswordDialog extends Dialog {
    public PasswordDialog(@NonNull Context context) {
        super(context);
    }

    public PasswordDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PasswordDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private Button btnSubmit, btnCancel;
    private EditText etFirst, etSecond;
    private String firstPassword, secondPassword;
    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);


    }
}
