package com.example.sns_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.sns_project.R;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.sns_project.Util.showToast;

public class LoginActivity extends BasicActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolbarTitle("로그인");

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoPasswordResetButton).setOnClickListener(onClickListener);
    }



    View.OnClickListener onClickListener = v -> {
        switch (v.getId()) {
            case R.id.checkButton:
                LoginActivity.this.login();
                break;
            case R.id.gotoPasswordResetButton:
                LoginActivity.this.myStartActivity(PasswordResetActivity.class);
                break;
        }
    };

    private void login() {
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0){
            final RelativeLayout loaderLayout = findViewById(R.id.loaderLayout);
            loaderLayout.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        loaderLayout.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            showToast(LoginActivity.this, "로그인에 성공하였습니다. ");
                            myStartActivity(MainActivity.class);
                        } else {
                            if(task.getException() != null){
                                showToast(LoginActivity.this, task.getException().toString());
                            }
                        }
                    });
        }else {
            showToast(LoginActivity.this, "이메일 또는 비밀번호를 입력해 주세요. ");
        }
    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}