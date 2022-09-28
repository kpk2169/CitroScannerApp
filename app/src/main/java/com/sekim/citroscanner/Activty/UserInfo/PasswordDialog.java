package com.sekim.citroscanner.Activty.UserInfo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sekim.citroscanner.R;
import com.sekim.citroscanner.Retrofit.RetrofitBuilder;
import com.sekim.citroscanner.Retrofit.User.GetUserInfoResult;
import com.sekim.citroscanner.Retrofit.User.PasswordParams;
import com.sekim.citroscanner.Retrofit.User.UserAPI;
import com.sekim.citroscanner.Utils.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    private Context context;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_password);

        findViewById();
    }

    private void findViewById(){
        try{
            retrofit = RetrofitBuilder.RetrofitClient();

            btnSubmit = findViewById(R.id.btn_submit_password);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        // API 통신 결과 확인
                        firstPassword = etFirst.getText().toString();
                        secondPassword = etSecond.getText().toString();

                        UserAPI userAPI = retrofit.create( UserAPI.class );
                        Call<GetUserInfoResult> getUserInfoResultCall = userAPI.changePassword( userToken, new PasswordParams(firstPassword, secondPassword ));
                        getUserInfoResultCall.enqueue(new Callback<GetUserInfoResult>() {
                            @Override
                            public void onResponse(Call<GetUserInfoResult> call, Response<GetUserInfoResult> response) {
                                if(response.isSuccessful()){
                                    GetUserInfoResult infoResult = response.body();
                                    if( infoResult.getStatus().equals("success")){
                                        Toast.makeText( context, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT ).show();
                                    }else{
                                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                                    }
                                    dismiss();
                                }else{
                                    Toast.makeText(context, "인터넷 연결 상태를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                    dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<GetUserInfoResult> call, Throwable t) {
                                Toast.makeText(context, R.string.api_err_msg , Toast.LENGTH_SHORT).show();
                                dismiss();
                            }
                        });


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            btnCancel = findViewById(R.id.btn_exchange_cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            etFirst = findViewById(R.id.et_first_password);
            etSecond = findViewById(R.id.et_second_password);

            userToken = PreferenceManager.getString( context, PreferenceManager.USER_TOKEN );

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
