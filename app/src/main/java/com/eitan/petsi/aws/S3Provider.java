package com.eitan.petsi.aws;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transfermanager.Download;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.mobileconnectors.s3.transfermanager.model.UploadResult;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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

    public String getPicUrl(UploadResult uploadResult){

        return ("http://" + uploadResult.getBucketName() + ".s3.amazonaws.com/" + uploadResult.getKey());
    }

    public void uploadImage(String fileName,File file, FileUploadCallBack fileUploadCallBack){

        String fileKey = IMAGES_PREFIX + fileName;

        UploadToS3AsynchTask uploadToS3AsynchTask = new UploadToS3AsynchTask(fileUploadCallBack);
        uploadToS3AsynchTask.execute(new FileToUpload(IMAGES_BUCKET_NAME,fileKey,file));
    }

    private class FileToUpload{
        public String bucketName;
        public String key;
        public File file;

        private FileToUpload(String bucketName, String key, File file) {
            this.bucketName = bucketName;
            this.key = key;
            this.file = file;
        }
    }

    private class UploadToS3AsynchTask extends AsyncTask<FileToUpload,Integer,UploadResult>{

        private FileUploadCallBack fileUploadCallBack;

        public UploadToS3AsynchTask(FileUploadCallBack fileUploadCallBack) {
            this.fileUploadCallBack = fileUploadCallBack;
        }

        @Override
        protected UploadResult doInBackground(FileToUpload... fileToUploads) {

            //Upload upload = transferManager.upload(fileToUploads[0].bucketName,fileToUploads[0].key,fileToUploads[0].file);

            PutObjectRequest putObjectRequest = new PutObjectRequest(fileToUploads[0].bucketName,fileToUploads[0].key,fileToUploads[0].file);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

            Upload upload = transferManager.upload(putObjectRequest);
//            transferManager.upload(PutObjectRequest)
            try {
                UploadResult uploadResult = upload.waitForUploadResult();

                if (uploadResult.getKey().isEmpty())
                    return null;
                else return uploadResult;

            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(UploadResult uploadResult) {

            fileUploadCallBack.onUploadToS3Completed(uploadResult);
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

            File file = new File(context.getFilesDir(), fileParamses[0].filename);

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
