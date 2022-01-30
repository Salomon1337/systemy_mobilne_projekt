package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button make_a_new_account_button;
    private Button log_in_button;
    private EditText mainAccountLoginEditText;
    private EditText mainAccountPasswordEditText;
    private MainAccountViewModel mainAccountViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        make_a_new_account_button = findViewById(R.id.make_a_new_account_button);
        log_in_button = findViewById(R.id.log_in_button);
        mainAccountLoginEditText =findViewById(R.id.main_account_login);
        mainAccountPasswordEditText = findViewById(R.id.main_account_password);
        mainAccountViewModel = new ViewModelProvider(this).get(MainAccountViewModel.class);
        make_a_new_account_button.setOnClickListener((view) -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });
        log_in_button.setOnClickListener((view) -> {

            if (TextUtils.isEmpty(mainAccountLoginEditText.getText()) || TextUtils.isEmpty(mainAccountPasswordEditText.getText())) {
                Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_LONG).show();
            }
            MainAccount account = mainAccountViewModel.findMainAccountByLogin(mainAccountLoginEditText.getText().toString());
            if (account != null && account.getPassword().equals(mainAccountPasswordEditText.getText().toString())){

                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                intent.putExtra(MainMenuActivity.EXTRA_MAIN_ACCOUNT_ID,account.getId());
                startActivity(intent);
            }
            else{
                Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_LONG).show();
            }

        });

    }
}