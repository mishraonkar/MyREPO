package burrow.co.in.owner.api;

import java.io.File;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class UploadImage{


    private String ACCESS_KEY;
    private String SECRET_KEY;
    private String MY_BUCKET;
    private String OBJECT_KEY;
    
    private android.app.Activity activity;
    private String fileUrl;
    
    
    
	public UploadImage(android.app.Activity activity, String fileUrl) {
		super();
	    ACCESS_KEY="AKIAIXTZAODYGKTQFZBQ";
	    SECRET_KEY="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";
	    MY_BUCKET="burrowappuserdata/buildingimg";
	    OBJECT_KEY="burrow_1792015_7";
	    
		this.activity = activity;
		this.fileUrl = fileUrl;
	}

	@SuppressLint("DefaultLocale")
	public void run() {
		// TODO Auto-generated method stub
		File  UPLOADING_FILE=new File(fileUrl);
        try{
            AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
            AmazonS3 s3 = new AmazonS3Client(credentials);
            java.security.Security.setProperty("networkaddress.cache.ttl" , "60");
            s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
            s3.setEndpoint("https://s3-ap-southeast-1.amazonaws.com/");
            TransferUtility transferUtility = new TransferUtility(s3, activity);
            TransferObserver observer = transferUtility.upload(MY_BUCKET,OBJECT_KEY,UPLOADING_FILE);
            observer.setTransferListener(new TransferListener() {
                @Override
                public void onStateChanged(int id, TransferState state) {
                    // do something
                	if (!(state.name().toString().toUpperCase().equals("COMPLETED"))) {
                		Snackbar.make(activity.findViewById(android.R.id.content), "Image upoad failed", Snackbar.LENGTH_LONG).setActionTextColor(Color.RED).show();
					}
                }

                @Override
                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

                }

                @Override
                public void onError(int id, Exception ex) {

                }

            });
        } catch (Exception e){

        }
	}

}
