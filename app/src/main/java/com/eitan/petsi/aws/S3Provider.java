package com.eitan.petsi.aws;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.eitan.petsi.R;

import java.io.File;

/**
 * Created by eitan on 25/10/2014.
 */
public class S3Provider {

    private static S3Provider s3Provider;
    private AWSCredentials awsCredentials; //= new BasicAWSCredentials()
    private Context context;
    TransferManager transferManager;

    public S3Provider(Context context) {

        this.context = context;
        awsCredentials = new BasicAWSCredentials(context.getString(R.string.am_u), context.getString(R.string.am_u));

        transferManager  = new TransferManager(awsCredentials);
    }

    public void getFileForKey(String key, FileDownloadCallBack callback){


        String filename = key.substring(key.lastIndexOf("/"),key.length());
        String bucketName = key.substring(0,key.indexOf(":"));
        String path = key.substring(key.indexOf(":"),key.length());
        System.out.println("filename=" + filename);
        System.out.println("bucketName=" + bucketName);
        System.out.println("path=" + path);

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
            transferManager.download(fileParamses[0].bucketName,fileParamses[0].path,file);
            return file;
        }

        @Override
        protected void onPostExecute(File file) {

            fileDownloadCallBack.onFileDowloaded(file);
        }


    }


}
