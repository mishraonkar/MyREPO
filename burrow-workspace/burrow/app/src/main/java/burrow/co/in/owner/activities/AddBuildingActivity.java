package burrow.co.in.owner.activities;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import burrow.co.in.Constants;
import burrow.co.in.R;
import burrow.co.in.owner.api.UploadImage;

public class AddBuildingActivity extends AppCompatActivity{
	
	private Activity activity;
	private EditText buildingName, buildingAddress, pincode;
	private TextView addressCount;
	private Spinner buildingType;
	private FloatingActionButton buttonAdd;
	private TextInputLayout til_bildingName;
	private Toolbar toolbar;
	private ImageView uploadImage;
	private String bName ="", bAddress ="", bPincode ="", bType ="", bImage ="" ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_add_building);
        
        activity = AddBuildingActivity.this;
        
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        buildingName = (EditText) findViewById(R.id.editText_buildingName);
        buildingAddress = (EditText) findViewById(R.id.editText_buildingAddress);
        addressCount = (TextView) findViewById(R.id.textView_address_count);
        pincode = (EditText)findViewById(R.id.editText_pincode);
        buildingType = (Spinner) findViewById(R.id.spinner_buildingType);
        buttonAdd = (FloatingActionButton) findViewById(R.id.fab);
        til_bildingName = (TextInputLayout) findViewById(R.id.textInputLayout_buildingName);
        uploadImage = (ImageView) findViewById(R.id.imageView_upload);
        
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	finish();
            }
        });
        
        buttonAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bName  = buildingName.getText().toString().trim();
				bAddress = buildingAddress.getText().toString().trim();
				bPincode = pincode.getText().toString().trim();
				bType  = buildingType.getSelectedItem().toString().trim();
				
				int errorFlag = 0;

            	if (bName.length() < 1) {
            		errorFlag = 1;
            		til_bildingName.setErrorEnabled(true);
                    SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, "Building name is required.");
                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    til_bildingName.setError(ssb);
				}else {
					til_bildingName.setError(null);
					til_bildingName.setErrorEnabled(false);
				}
            	
            	if (errorFlag == 0) {
            		String serverURL = "http://dev.tiger.org.in:9191/api/owner/building";
            		new AddBuildingAsyncTask().execute(serverURL);

				}
				
			}
		});
        
        buildingAddress.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {
					buildingAddress.setSingleLine(false);
					buildingAddress.setMaxLines(5);
					buildingAddress.setText(bAddress);
				} else {
					bAddress = buildingAddress.getText().toString();
					buildingAddress.setSingleLine(true);
					buildingAddress.setText(bAddress.replace("\n", " "));
				}
			}
		});
        
        uploadImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 101);
			}
		});
        
        setAddressTextWatcher();
	}
	
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            uploadImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            bImage = picturePath;
        }


    }
	
	
	private BitmapDrawable getBitmapDrawable(Context context, String str){
    	
    	TextView textView = (TextView) activity.findViewById(R.id.textViewFake);
		textView.setText(str);
		textView.setTextColor(Color.parseColor("#FF3030"));

		int spec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		textView.measure(spec, spec);
		textView.layout(0, 0, textView.getWidth(),textView.getMeasuredHeight());
		Bitmap b = Bitmap.createBitmap(textView.getWidth(),textView.getHeight(), Bitmap.Config.ARGB_8888);
		
		
		Canvas canvas = new Canvas(b);
		canvas.translate(-textView.getScrollX(), -textView.getScrollY());
		textView.draw(canvas);
		textView.setDrawingCacheEnabled(true);
		
		
		Bitmap cacheBmp = textView.getDrawingCache();
		Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
		textView.destroyDrawingCache(); 
		
		BitmapDrawable bmpDrawable = new BitmapDrawable(context.getResources(), viewBmp);
		bmpDrawable.setBounds(0, 0, bmpDrawable.getIntrinsicWidth(),bmpDrawable.getIntrinsicHeight());
		return bmpDrawable;
    }
	
    private void setAddressTextWatcher() {
    	
		TextWatcher address_TextWatcher = new TextWatcher() {

	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	            // TODO Auto-generated method stub
	        }

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,
	                int after) {
	            // TODO Auto-generated method stub
	        }

	        @Override
	        public void afterTextChanged(Editable s) {
	            // TODO Auto-generated method stub	        	
	        	addressCount.setText(String.valueOf(s.length()+"/120"));
	        }
	    };
	    buildingAddress.addTextChangedListener(address_TextWatcher);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_owner_add_building, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {

            case R.id.action_settings :
                //Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                //startActivity(intent);
                return  true;
        }
        return true;
    }
    
    private class AddBuildingAsyncTask extends AsyncTask<String,Void,Integer> {
        @Override
        protected void onPreExecute() {
        	
        }

        @Override
        protected Integer doInBackground(String... parameters) {
            Integer result = 0;
            BufferedReader reader =null;
            StringBuilder sb;
            int statusCode;
                try {
                    URL url=new URL(parameters[0]);

                    Map<String, Object> params = new LinkedHashMap<>();
                    params.put("building_name", bName);
                    params.put("building_address", bAddress);
                    params.put("building_pincode", bPincode);
                    params.put("building_image", "https://s3-ap-southeast-1.amazonaws.com/burrowappuserdata/buildingimg/burrow_1792015_7");
                    params.put("building_type", bType);
                    StringBuilder postData = new StringBuilder();

                    for (Map.Entry<String, Object> param : params.entrySet()) {
                        if (postData.length() != 0) postData.append('&');
                        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                        postData.append('=');
                        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                    }

                    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                    connection.setRequestProperty("email", "amaldev@tigerinnovations.org");
                    connection.setRequestProperty("token", "ya11");
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(postDataBytes);
                    connection.connect();

                    statusCode = connection.getResponseCode();
                    if (statusCode == 200) {
                        sb = new StringBuilder();
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = 1;
                    } else {
                        result = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
			

            return result;
        }

        @Override
        protected void onPostExecute(Integer result){
            //progressBar.setVisibility(View.GONE);

            if (result == 1) {
                Snackbar.make(findViewById(android.R.id.content), "Building added successfully", Snackbar.LENGTH_LONG).setActionTextColor(Color.RED).show();
                
            	Thread t = new Thread(new Runnable() {   				
    				@Override
    				public void run() {
    					// TODO Auto-generated method stub
    		        	if (bImage.length() > 0) {        		
    		        		new UploadImage(activity, bImage).run();
    					}
    				}
    			});
            	t.start();
    			SharedPreferences prefs = activity.getSharedPreferences(Constants.SHARED_PREF_NAME_BUILDING_DETAIL, Context.MODE_PRIVATE);                
    			Editor editor = prefs.edit();
    	        editor.putInt(Constants.SHARED_PREF_NAME_BUILDING_DETAIL_FLAG, Constants.NEED_TO_UPDATE);
    	        editor.commit();
				finish();
            	
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Check your internet connection", Snackbar.LENGTH_LONG).setActionTextColor(Color.RED).show();
            }
        }
    }
    
}
