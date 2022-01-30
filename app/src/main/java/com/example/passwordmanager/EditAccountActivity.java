package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountActivity extends AppCompatActivity {
    public static final String EXTRA_EDIT_ACCOUNT_NAME = "com.example.passwordmanager.EDIT_ACCOUNT_NAME";
    public static final String EXTRA_EDIT_ACCOUNT_EMAIL = "com.example.passwordmanager.EDIT_ACCOUNT_EMAIL";
    public static final String EXTRA_EDIT_ACCOUNT_LOGIN = "com.example.passwordmanager.EDIT_ACCOUNT_LOGIN";
    public static final String EXTRA_EDIT_ACCOUNT_PASSWORD= "com.example.passwordmanager.EDIT_ACCOUNT_PASSWORD";
    public static final String EXTRA_EDIT_ACCOUNT_URI = "com.example.passwordmanager.EDIT_ACCOUNT_URI";

    private EditText editNameEditText;
    private EditText editLoginEditText;
    private EditText editEmailEditText;
    private EditText editPasswordEditText;
    private EditText editUriEditText;
    private Button generate_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        Intent starter = getIntent();
        editNameEditText = findViewById(R.id.edit_account_name);
        editLoginEditText = findViewById(R.id.edit_account_login);
        editEmailEditText = findViewById(R.id.edit_account_email);
        editPasswordEditText = findViewById(R.id.edit_account_password);
        editUriEditText = findViewById(R.id.edit_account_uri);
        generate_password = findViewById(R.id.button_generate_password);


        generate_password.setOnClickListener(view -> {
            RandomPasswordService service = RetrofitInstance.getInstance().create(RandomPasswordService.class);
            Call<PasswordWrapper> apiCall = service.getPasswords();
            apiCall.enqueue(new Callback<PasswordWrapper>() {

                @Override
                public void onResponse(Call<PasswordWrapper> call, Response<PasswordWrapper> response) {
                    editPasswordEditText.setText(response.body().getPasswords().get(0));
                }

                @Override
                public void onFailure(Call<PasswordWrapper> call, Throwable t) {

                }
            });
        });
        if(starter.hasExtra(EXTRA_EDIT_ACCOUNT_NAME)){
            editNameEditText.setText(starter.getStringExtra(EXTRA_EDIT_ACCOUNT_NAME));
        }
        if(starter.hasExtra(EXTRA_EDIT_ACCOUNT_EMAIL)){
            editEmailEditText.setText(starter.getStringExtra(EXTRA_EDIT_ACCOUNT_EMAIL));
        }
        if(starter.hasExtra(EXTRA_EDIT_ACCOUNT_LOGIN)){
            editLoginEditText.setText(starter.getStringExtra(EXTRA_EDIT_ACCOUNT_LOGIN));
        }
        if(starter.hasExtra(EXTRA_EDIT_ACCOUNT_PASSWORD)){
            editPasswordEditText.setText(starter.getStringExtra(EXTRA_EDIT_ACCOUNT_PASSWORD));
        }
        if(starter.hasExtra(EXTRA_EDIT_ACCOUNT_URI)){
            editUriEditText.setText(starter.getStringExtra(EXTRA_EDIT_ACCOUNT_URI));
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                if(TextUtils.isEmpty(editNameEditText.getText())){
                    Toast.makeText(EditAccountActivity.this,R.string.account_add_failure,Toast.LENGTH_LONG).show();
                }
                else{
                        Intent replyIntent = new Intent();
                    String name = editNameEditText.getText().toString();
                    replyIntent.putExtra(EXTRA_EDIT_ACCOUNT_NAME,name);
                    String login =(editLoginEditText.getText().toString());
                    replyIntent.putExtra(EXTRA_EDIT_ACCOUNT_LOGIN,login);
                    String email = editEmailEditText.getText().toString();
                    replyIntent.putExtra(EXTRA_EDIT_ACCOUNT_EMAIL,email);
                    String password = editPasswordEditText.getText().toString();
                    replyIntent.putExtra(EXTRA_EDIT_ACCOUNT_NAME,name);
                    String uri = editUriEditText.getText().toString();
                    replyIntent.putExtra(EXTRA_EDIT_ACCOUNT_URI,uri);
                    setResult(RESULT_OK,replyIntent);
                    finish();
                }
            }
        });

    }
}