package com.eitan.petsi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import info.hoang8f.widget.FButton;


public class StartActivity extends Activity implements View.OnClickListener{

    private FButton loginButton;
    private FButton signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getActionBar().hide();

        loginButton = (FButton) findViewById(R.id.login);
        loginButton.setOnClickListener(this);

        signUpButton = (FButton) findViewById(R.id.sign_up);
        signUpButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.login)
            startLoginSignUpActivity(App.LOGIN);
        else if (view.getId() == R.id.sign_up)
            startLoginSignUpActivity(App.SIGN_UP);
    }

    private void startLoginSignUpActivity(int mode){

        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra(App.LOGIN_PARAM,mode);

        startActivity(intent);
    }
}
