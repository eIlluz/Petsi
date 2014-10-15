package com.eitan.petsi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TakePicture extends Activity implements View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView petPicture;
    private static Boolean done = false;
    private String mCurrentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_ad);

        petPicture = (ImageView)findViewById(R.id.picture);
        petPicture.setScaleType(ImageView.ScaleType.FIT_CENTER);
        petPicture.setOnClickListener(this);
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

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

                handleError(ex.getMessage());
            }

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(photoFile));

            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(getApplicationContext(),"On Result!",Toast.LENGTH_LONG).show();

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //petPicture.setImageBitmap(imageBitmap);

            petPicture.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Picasso.with(getApplicationContext()).load(mCurrentPhotoPath)
                    .placeholder(R.drawable.ic_dog)
                    .error(R.drawable.ic_launcher)
                    .centerCrop().fit()
                    .into(petPicture);
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

    }

    @Override
    public void onClick(View view) {
        startCamera();
    }
}
