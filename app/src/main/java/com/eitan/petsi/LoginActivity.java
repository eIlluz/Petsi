package com.eitan.petsi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eitan.petsi.com.eitan.petsi.services.LoginResponse;
import com.eitan.petsi.com.eitan.petsi.services.RegisterResponse;
import com.eitan.petsi.com.eitan.petsi.services.UserLogin;
import com.eitan.petsi.com.eitan.petsi.services.UserLoginRespond;
import com.eitan.petsi.com.eitan.petsi.services.UserRegister;
import com.eitan.petsi.com.eitan.petsi.services.UserRegisterRespond;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;


/**
 * A login screen that offers login via email/password.

 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor>, UserLoginRespond, UserRegisterRespond{

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLogin mAuthTask = null;
    private UserRegister mRegisterTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mUserName;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hide action bar
        getActionBar().hide();

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();



        mPasswordView = (EditText) findViewById(R.id.password);
        mUserName = (EditText) findViewById(R.id.user_name);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button mEmailRegisterButton = (Button) findViewById(R.id.email_register_button);
        mEmailRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        //Check if Login or SignUp
        Bundle extras = getIntent().getExtras();

        int mode = extras.getInt(App.LOGIN_PARAM);

        if (mode == App.SIGN_UP){
            mEmailSignInButton.setVisibility(View.GONE);
            mEmailRegisterButton.setVisibility(View.VISIBLE);
            mUserName.setVisibility(View.VISIBLE);

        }else if (mode == App.LOGIN){
            mEmailRegisterButton.setVisibility(View.GONE);
            mEmailSignInButton.setVisibility(View.VISIBLE);
            mUserName.setVisibility(View.GONE);
        }
        //------------------------------------------------------------------------------
        //------------------------------------------------------------------------------
        mEmailView.setText("mezamern@gmail.com");
        mPasswordView.setText("123456");
        //------------------------------------------------------------------------------
        //------------------------------------------------------------------------------
    }

    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }


    /**
     * Attempts to sign in.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        //Check that authentication is not already running.
        if (mAuthTask != null) {
            return;
        }

        if (checkFieldsValidation(App.LOGIN)) {
            // Store values at the time of the login attempt.
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLogin(email, password, this);
            mAuthTask.getLogin();
        }
    }

    /**
     * Attempts to register user.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptRegister() {

        //Check that authentication is not already running.
        if (mRegisterTask != null) {
            return;
        }

        if (checkFieldsValidation(App.SIGN_UP)) {
            // Store values at the time of the login attempt.
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mRegisterTask = new UserRegister(email, password, mUserName.getText().toString(), this);
            mRegisterTask.registerUser();
        }
    }
    private boolean checkFieldsValidation(int mode){
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mUserName.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String userName = mUserName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (mode == App.SIGN_UP){
            if (TextUtils.isEmpty(userName)) {
                mUserName.setError(getString(R.string.error_field_required));
                focusView = mUserName;
                cancel = true;
            } else if (!isNameValid(userName)) {
                mUserName.setError(getString(R.string.error_invalid_user_name));
                focusView = mUserName;
                cancel = true;
            }
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }

        return (!cancel);
    }


    private boolean isEmailValid(String email) {

        //Check email is valid
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        //Check password is long enough
        return password.length() > 4;
    }

    private boolean isNameValid(String name){
        return name.length() > 3;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                                                                     .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {
        onLoginTryEnd();

        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFailed() {
        onLoginTryEnd();

        mPasswordView.setError(getString(R.string.error_incorrect_password));
        mPasswordView.requestFocus();
    }

    @Override
    public void onRegisterSuccess(RegisterResponse registerResponse) {
        onLoginTryEnd();

        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onRegisterFailed() {
        mEmailView.setError(getString(R.string.error_exist_user_name));
    }

    @Override
    public void onRestCallError(RetrofitError error) {
        onLoginTryEnd();

        Toast.makeText(this,getString(R.string.error_occurred),Toast.LENGTH_LONG).show();
    }

    private void onLoginTryEnd(){
        showProgress(false);
        mAuthTask = null;
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
//    public class UserLoginTask extends AsyncTask<Void, Void, RegResponse> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected RegResponse doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                return new RegResponse(false,getString(R.string.error_occurred));
//            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return new RegResponse(pieces[1].equals(mPassword),getString(R.string.error_incorrect_password));
//                }
//            }
//
//            try {
//                new RegisterNewUser(mEmail,mPassword).RegisterUser();
//            } catch (RegFailedException e) {
//                return new RegResponse(false,e.getMessage());
//            }
//            return new RegResponse(true,"");
//        }
//
//        @Override
//        protected void onPostExecute(final RegResponse response) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (response.isSuccess()) {
//
//                startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                finish();
//
//            } else {
//                mPasswordView.setError(response.getMessage());
//                mPasswordView.requestFocus();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
//        }
//    }
}



