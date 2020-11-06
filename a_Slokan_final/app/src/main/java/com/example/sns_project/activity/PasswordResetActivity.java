package com.example.sns_project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.example.sns_project.R;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.sns_project.Util.showToast;

public class PasswordResetActivity extends BasicActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        setToolbarTitle("비밀번호 재설정");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sendButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.sendButton) {
            PasswordResetActivity.this.send();
        }
    };

    private void send() {
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        if(email.length() > 0 ){
            final RelativeLayout loaderLayout = findViewById(R.id.loaderLayout);
            loaderLayout.setVisibility(View.VISIBLE);
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        loaderLayout.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            showToast(PasswordResetActivity.this,"이메일을 보냈습니다.");
                        }
                    });
        }else {
            showToast(PasswordResetActivity.this,"이메일을 입력해 주세요.");
        }
    }
}