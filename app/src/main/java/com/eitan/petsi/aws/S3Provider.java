package com.eitan.petsi.aws;

import android.content.Context;
import android.os.AsyncTask;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transfermanager.Download;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.mobileconnectors.s3.transfermanager.model.UploadResult;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.eitan.petsi.R;


import java.io.File;
import java.io.IOException;

/**
 * Created by eitan on 25/10/2014.
 */
public class S3Provider {

    private final String IMAGES_BUCKET_NAME = "petsi-bucket";
    private final String IMAGES_PREFIX = "pets-pictures/";


    private static S3Provider s3Provider;
    private AWSCredentials awsCredentials; //= new BasicAWSCredentials()
    private Context context;
    TransferManager transferManager;

    public S3Provider(Context context) {

        this.context = context;
        awsCredentials = new BasicAWSCredentials(context.getString(R.string.am_u), context.getString(R.string.am_p));

        transferManager  = new TransferManager(awsCredentials);
    }

    public String uploadImage(String file_name,File file){

        String file_key = IMAGES_PREFIX + file_name;
        Upload upload = transferManager.upload(IMAGES_BUCKET_NAME,file_key,file);

        try {
            UploadResult uploadResult = upload.waitForUploadResult();

            if (uploadResult.getKey().isEmpty())
                return null;
            else return uploadResult.getKey();

        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public void getFileForKey(String key, FileDownloadCallBack callback){


        String filename = key.substring(key.lastIndexOf("/") + 1,key.length());
        String bucketName = key.substring(0,key.indexOf(":"));
        String path = key.substring(key.indexOf(":") + 1 ,key.length());

        FileParams fileParams = new FileParams(bucketName,path,filename);

        GetFileTask getFileTask = new GetFileTask(callback);
        getFileTask.execute(fileParams);
    }

    private class FileParams{
        public String bucketName;
        public String path;
        public String filename;

        private FileParams(String bucketName, String path, String filename) {
            this.bucketName = bucketName;
            this.path = path;
            this.filename = filename;
        }
    }

    private class GetFileTask extends AsyncTask<FileParams,Integer,File>{

        private FileDownloadCallBack fileDownloadCallBack;

        private GetFileTask(FileDownloadCallBack fileDownloadCallBack) {
            this.fileDownloadCallBack = fileDownloadCallBack;
        }

        @Override
        protected File doInBackground(FileParams... fileParamses) {

//            System.out.println("#@#@#@#@#@#@#" + context.getFilesDir().getAbsolutePath() + "#@#@#@#@#@#@#");
            File file = new File(context.getFilesDir(), fileParamses[0].filename);

//            File cachFile;
//
//            try {
//                cachFile = File.createTempFile(fileParamses[0].filename,null,context.getCacheDir());
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }

                    System.out.println("path = " + fileParamses[0].path);
            System.out.println("bucket = " + fileParamses[0].bucketName);
            Download download = transferManager.download(fileParamses[0].bucketName, fileParamses[0].path, file);
            try {
                download.waitForCompletion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return file;


        }

        @Override
        protected void onPostExecute(File file) {

            fileDownloadCallBack.onFileDowloaded(file);
        }


    }


}
