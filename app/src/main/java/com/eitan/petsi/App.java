package com.eitan.petsi;

import android.app.Application;
import android.content.Context;

import com.eitan.petsi.aws.FileDownloadCallBack;
import com.eitan.petsi.aws.FileUploadCallBack;
import com.eitan.petsi.aws.S3Provider;
import com.eitan.petsi.data.PetListProvider;

import java.io.File;

/**
 * Created by eitan on 26/07/2014.
 */
public class App extends Application {

    public static final String AGE = "Age";
    public static final String SIZE = "Size";
    public static final String ANIMAL = "Animal";
    public static final String GENDER = "Gender";
    public static final String FROM_AGE = "FromAge";
    public static final String TO_AGE = "ToAge";
    public static final String USER = "user";

    public static final String LOGIN_PARAM = "login_param";
    public static final int LOGIN = 1;
    public static final int SIGN_UP = 2;

    public PetListProvider petListProvider = new PetListProvider();

    private static Context context;

    private S3Provider s3Provider;

    private String currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        s3Provider = new S3Provider(context);
    }

    public static Context getContext(){
        return context;
    }

    public void getFileForS3Key(String key, FileDownloadCallBack fileDownloadCallBack){
        s3Provider.getFileForKey(key,fileDownloadCallBack);
    }

    public void uploadImageToS3(String fileName,File file, FileUploadCallBack fileUploadCallBack){
        s3Provider.uploadImage(fileName,file,fileUploadCallBack);
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}

