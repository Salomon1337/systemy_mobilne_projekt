package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private MainAccountViewModel mainAccountViewModel;
    private Button create_account_button;
    private EditText loginEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mainAccountViewModel = new ViewModelProvider(this).get(MainAccountViewModel.class);
        create_account_button = findViewById(R.id.create_account_button);
        loginEditText = findViewById(R.id.register_account_login);
        passwordEditText = findViewById(R.id.register_account_password);

        create_account_button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(loginEditText.getText()) || TextUtils.isEmpty(passwordEditText.getText())) {
                Toast.makeText(RegisterActivity.this, R.string.account_add_failure, Toast.LENGTH_LONG).show();
            }
            else {

                mainAccountViewModel.insert(new MainAccount(loginEditText.getText().toString(),passwordEditText.getText().toString()));
                Toast.makeText(RegisterActivity.this, R.string.account_added_successfully, Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }

}