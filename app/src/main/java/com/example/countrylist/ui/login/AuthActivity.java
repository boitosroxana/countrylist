package com.example.countrylist.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.countrylist.MainActivity;
import com.example.countrylist.R;
import com.example.countrylist.models.User;
import com.example.countrylist.utils.DataManager;

public class AuthActivity extends AppCompatActivity {
    private AuthViewModel mViewModel;
    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnAuth;
    private TextView tvAuth;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_fragment);
        edtUsername = findViewById(R.id.edt_username);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnAuth = findViewById(R.id.btn_auth);
        tvAuth = findViewById(R.id.tv_auth);
        setScreenOnClick();
        setActionOnClick();

        mViewModel = new AuthViewModel();
        isLogin = false;

    }

    private User getData() {
        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if (username.isEmpty()) {
            edtUsername.setError("Username cannot be empty!");
            return null;

        } else if (email.isEmpty() && !isLogin) {
            edtEmail.setError("Email cannot be empty!");
            return null;
        } else if (password.isEmpty()) {
            edtPassword.setError("Password cannot be empty!");
            return null;
        }

        return new User(username, email, password);
    }

    private void setAuthViews() {
        if (isLogin) {
            edtEmail.setVisibility(View.GONE);
            btnAuth.setText(R.string.login);
            tvAuth.setText((R.string.sign_in));

        } else {
            edtEmail.setVisibility(View.VISIBLE);
            btnAuth.setText(R.string.sign_in);
            tvAuth.setText((R.string.login));
        }
    }

    private void setScreenOnClick() {
        tvAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = !isLogin;
                setAuthViews();
            }
        });
    }

    private void setActionOnClick() {
        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = getData();
                if (user != null) {
                    if (!isLogin) {
                        // mViewModel.singUp(user);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        DataManager.setString("username","password","email");

                    } else {
                        //DataManager.getInstance().setBoolean("isLoggedIn",true);
                        mViewModel.login(user.getUsername(), user.getPassword());
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

                    }
                } else{
                    Toast.makeText(getApplicationContext(), "User cannot have empty fields! Check your data!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        edtUsername = view.findViewById(R.id.edt_username);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);
        btnAuth = view.findViewById(R.id.btn_auth);
        tvAuth = view.findViewById(R.id.tv_auth);
        setScreenOnClick();
        setActionOnClick();

    }
}