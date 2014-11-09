package com.eitan.petsi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.s3.transfermanager.model.UploadResult;
import com.eitan.petsi.aws.FileUploadCallBack;
import com.eitan.petsi.com.eitan.petsi.services.AddAdListener;
import com.eitan.petsi.com.eitan.petsi.services.AddAdTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import info.hoang8f.widget.FButton;
import retrofit.RetrofitError;


public class NewAd extends Activity implements View.OnClickListener, FileUploadCallBack, AddAdListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView petPicture;
    private static Boolean done = false;
    private String mCurrentPhotoPath;
    private File pictureFile = null;
    private FButton saveButton;

    private EditText petNameEditText;
    private EditText petAgeEditText;
    private EditText petDescEditText;
    private EditText petStoryEditText;

    private Spinner sizeSpinner;
    private Spinner typeSpinner;
    private Spinner genderSpinner;

    private View progressView;
    private View newAdFormView;

    private boolean pictureTaken = false;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        app = (App)getApplication();

        setContentView(R.layout.activity_new_ad);

        progressView = findViewById(R.id.new_ad_progress);
        newAdFormView = findViewById(R.id.new_ad_form);

        petPicture = (ImageView)findViewById(R.id.picture);
        petPicture.setScaleType(ImageView.ScaleType.FIT_CENTER);
        petPicture.setOnClickListener(this);

        saveButton = (FButton)findViewById(R.id.save_new_ad);
        saveButton.setOnClickListener(this);

        petNameEditText = (EditText)findViewById(R.id.new_ad_pet_name);
        petAgeEditText = (EditText)findViewById(R.id.new_ad_pet_age);
        petDescEditText = (EditText)findViewById(R.id.new_ad_pet_desc);
        petStoryEditText = (EditText)findViewById(R.id.new_ad_pet_story);

        setAnimalAdapter();
        setSizeAdapter();
        setGenderAdapter();

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            newAdFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            newAdFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    newAdFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            newAdFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    //Set the animal types values set.
    private void setAnimalAdapter()
    {
        typeSpinner = (Spinner)findViewById(R.id.new_ad_animal_spinner);

        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> animalAdapter =
                ArrayAdapter.createFromResource(getBaseContext(), R.array.new_ad_types_array,android.R.layout.simple_spinner_dropdown_item);

        //Specify the layout to use when the list of choices appears
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapter to the spinner
        typeSpinner.setAdapter(animalAdapter);
    }

    //Set the animal types values set.
    private void setSizeAdapter()
    {
        sizeSpinner = (Spinner)findViewById(R.id.new_ad_size_spinner);

        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> animalAdapter =
                ArrayAdapter.createFromResource(getBaseContext(), R.array.new_ad_sizes_array,android.R.layout.simple_spinner_dropdown_item);

        //Specify the layout to use when the list of choices appears
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapter to the spinner
        sizeSpinner.setAdapter(animalAdapter);
    }

    //Set the animal types values set.
    private void setGenderAdapter()
    {
        genderSpinner = (Spinner)findViewById(R.id.new_ad_gender_spinner);

        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> animalAdapter =
                ArrayAdapter.createFromResource(getBaseContext(), R.array.new_ad_gender_array,android.R.layout.simple_spinner_dropdown_item);

        //Specify the layout to use when the list of choices appears
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapter to the spinner
        genderSpinner.setAdapter(animalAdapter);
    }

    private void startCamera(){

            dispatchTakePictureIntent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.take_picture, menu);


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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            pictureFile = null;
            try {
                pictureFile = createImageFile();
            } catch (IOException ex) {

                handleError(ex.getMessage());
            }

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(pictureFile));

            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            //Load the picture to view and cache.
            petPicture.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Picasso.with(getApplicationContext()).load(mCurrentPhotoPath)
                    .placeholder(R.drawable.ic_dog)
                    .error(R.drawable.ic_launcher)
                    .centerCrop().fit()
                    .into(petPicture);

            pictureTaken = true;
        }

    }

    private File createImageFile() throws IOException {

        // Create an image unique file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }



    private void handleError(String errorMessage){
        showProgress(false);
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.picture)
            startCamera();
        else if (view.getId() == R.id.save_new_ad)

            //Check data is valid
            if (validateDetails())
                saveNewAd();
    }

    private boolean validateDetails(){

        if (petNameEditText.getText().toString().isEmpty()){
            petNameEditText.setError(getString(R.string.empty_field_message));
            return false;
        }
        if (petAgeEditText.getText().toString().isEmpty()){
            petAgeEditText.setError(getString(R.string.empty_field_message));
            return false;
        }
        if (petDescEditText.getText().toString().isEmpty()){
            petDescEditText.setError(getString(R.string.empty_field_message));
            return false;
        }
        if (petStoryEditText.getText().toString().isEmpty()){
            petStoryEditText.setError(getString(R.string.empty_field_message));
            return false;
        }

        if (!pictureTaken){
            Toast.makeText(this,getString(R.string.empty_pic),Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void saveNewAd(){

        //Show progress bar until ad saved.
        showProgress(true);

        // First try to upload image to s3
        app.uploadImageToS3(calcPicFileName(),pictureFile,this);
    }

    private String calcPicFileName(){

        java.util.Date date= new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        String currTimestamp =  new SimpleDateFormat("MMddyyyyHHmmss").format(timestamp);

        return (app.getCurrentUser() + currTimestamp + ".jpg");
    }
    @Override
    public void onUploadToS3Completed(UploadResult uploadResult) {

        //After image uploaded, saving the ad
        AddAdTask addAdTask = new AddAdTask(this,petDescEditText.getText().toString(),
                sizeSpinner.getSelectedItem().toString().toUpperCase(),typeSpinner.getSelectedItem().toString().toUpperCase(),
                app.getCurrentUser(),petNameEditText.getText().toString(),petStoryEditText.getText().toString(),
                app.getPicUrl(uploadResult),genderSpinner.getSelectedItem().toString().toUpperCase(),petAgeEditText.getText().toString());

        addAdTask.addAd();
    }

    @Override
    public void onAddSuccess() {

        showProgress(false);
        Toast.makeText(this,getString(R.string.add_success_msg),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddFailed() {
        showProgress(false);
        Toast.makeText(this,getString(R.string.add_ad_error),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRestCallError(RetrofitError error) {
        showProgress(false);
        Toast.makeText(this,getString(R.string.add_ad_error),Toast.LENGTH_LONG).show();
    }
}
