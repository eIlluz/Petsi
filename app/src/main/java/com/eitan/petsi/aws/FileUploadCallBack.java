package com.eitan.petsi.aws;

import com.amazonaws.mobileconnectors.s3.transfermanager.model.UploadResult;

/**
 * Created by eitan on 26/10/2014.
 */
public interface FileUploadCallBack {

    public void onUploadToS3Completed(UploadResult uploadResult);
}
