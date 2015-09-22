package burrow.co.in.owner.api;

import java.io.File;
import java.util.List;

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
import com.amazonaws.services.s3.model.Bucket;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ImageUploadService extends IntentService {


    private String ACCESS_KEY;
    private String SECRET_KEY;
    private String MY_BUCKET;
    private String OBJECT_KEY;

    private Context context;
    private String fileUrl;
    
	public ImageUploadService() {
		super("ImageUploadService");
		
	    ACCESS_KEY="AKIAIXTZAODYGKTQFZBQ";
	    SECRET_KEY="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";
	    MY_BUCKET="burrowappuserdata/buildingimg";
	    OBJECT_KEY="burrow_1792015_5";
	    
		this.context = ImageUploadService.this;
		this.fileUrl = fileUrl;
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		File  UPLOADING_FILE=new File(fileUrl);
        try{
           
            AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
            AmazonS3 s3 = new AmazonS3Client(credentials);
            java.security.Security.setProperty("networkaddress.cache.ttl" , "60");
            s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
            s3.setEndpoint("https://s3-ap-southeast-1.amazonaws.com/");
            TransferUtility transferUtility = new TransferUtility(s3, context);
            TransferObserver observer = transferUtility.upload(MY_BUCKET,OBJECT_KEY,UPLOADING_FILE);
            observer.setTransferListener(new TransferListener() {
                @Override
                public void onStateChanged(int id, TransferState state) {
                    // do something
                	if (state.name().toString().toUpperCase().equals("COMPLETED")) {
                		  System.out.println("image uploaded");
					}
                }

                @Override
                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                    int percentage = (int) (bytesCurrent / bytesTotal * 100);
                    //Display percentage transfered to user
                }

                @Override
                public void onError(int id, Exception ex) {
                    // do something
                    Log.e("Error  ",""+ex );
                }

            });
            Log.d("Test", observer.getId() + " " + observer.getBytesTransferred());
        } catch (Exception e){
            Log.e("Error",""+e);
        }
	}
}