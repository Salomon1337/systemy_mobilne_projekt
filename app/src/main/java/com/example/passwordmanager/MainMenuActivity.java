package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import lombok.NonNull;

public class MainMenuActivity extends AppCompatActivity {
    public static final int NEW_ACCOUNT_ACTIVITY_REQUEST_CODE = 1;
    private AccountViewModel accountViewModel;
    public static final String EXTRA_MAIN_ACCOUNT_ID = "com.example.MAIN_ACCOUNT_ID";
    private int mainId;
    private Account editAccount;
    public static final int EDIT_ACCOUNT_ACTIVITY_REQUEST_CODE = 2;
    private EditText searchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent starter = getIntent();
        if (starter.hasExtra(EXTRA_MAIN_ACCOUNT_ID)) {
            mainId = starter.getIntExtra(EXTRA_MAIN_ACCOUNT_ID,0);
        }
        FloatingActionButton addAccountButton = findViewById(R.id.add_button);
        addAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainMenuActivity.this, EditAccountActivity.class);
                startActivityForResult(intent, NEW_ACCOUNT_ACTIVITY_REQUEST_CODE);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AccountAdapter adapter = new AccountAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.findAllAccounts(mainId).observe(this, accounts -> adapter.setAccounts(accounts));
        searchTextView = findViewById(R.id.account_search_edit_text);
        searchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Account> accounts = accountViewModel.findAccountsByName(mainId, searchTextView.getText().toString());
                adapter.setAccounts(accounts);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == NEW_ACCOUNT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
           Account account = new Account(
                   data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_LOGIN),
                   data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_PASSWORD),
                   data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_EMAIL),
                   data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_URI),
                   data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_NAME),
                   mainId);
           accountViewModel.insert(account);
            Snackbar.make(findViewById(R.id.main_layout),getString(R.string.account_added),Snackbar.LENGTH_LONG).show();
        }
        if(requestCode == EDIT_ACCOUNT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            editAccount.setLogin(data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_LOGIN));
            editAccount.setEmail(data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_EMAIL));
            editAccount.setName(data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_NAME));
            editAccount.setPassword(data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_PASSWORD));
            editAccount.setUri(data.getStringExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_URI));
            accountViewModel.update(editAccount);
            Snackbar.make(findViewById(R.id.main_layout),getString(R.string.the_data_has_been_changed),Snackbar.LENGTH_LONG).show();
            editAccount = null;
        }

    }

    private class AccountHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnClickListener {
        private TextView accountNameTextBox;
        private Account account;
        public AccountHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.account_list_item, parent, false));
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            accountNameTextBox = itemView.findViewById(R.id.account_name);
        }

        public void bind(Account account) {
            this.account = account;
            accountNameTextBox.setText(account.getName());
        }
        @Override
        public boolean onLongClick(View v) {
            accountViewModel.delete(this.account);
            return true;
        }
        @Override
        public void onClick(View v) {
            editAccount = account;
            Intent intent = new Intent(MainMenuActivity.this,EditAccountActivity.class);
            intent.putExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_NAME,account.getName());
            intent.putExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_LOGIN,account.getLogin());
            intent.putExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_EMAIL,account.getEmail());
            intent.putExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_PASSWORD,account.getPassword());
            intent.putExtra(EditAccountActivity.EXTRA_EDIT_ACCOUNT_URI,account.getUri());
            startActivityForResult(intent,EDIT_ACCOUNT_ACTIVITY_REQUEST_CODE);
        }
    }

    private class AccountAdapter extends RecyclerView.Adapter<AccountHolder> {
        private List<Account> accounts;

        @NonNull
        @Override
        public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AccountHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
            if (accounts != null){
                Account account = accounts.get(position);
                holder.bind(account);
            }
            else{
                Log.d("MainMenuActivity","No accounts");
            }

        }
        @Override
        public int getItemCount(){
            if (accounts != null){
                return accounts.size();
            }
            else {
                return 0;
            }
        }
        void setAccounts(List<Account> accounts){
            this.accounts = accounts;
            notifyDataSetChanged();
        }
    }
}



