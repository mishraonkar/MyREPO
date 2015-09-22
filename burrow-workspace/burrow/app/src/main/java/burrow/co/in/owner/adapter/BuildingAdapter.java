package burrow.co.in.owner.adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import burrow.co.in.R;
import burrow.co.in.owner.activities.BuildingDetailActivity;
import burrow.co.in.owner.beanclass.BuildingData;

@SuppressLint("NewApi")
public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder> {

    Activity activity;
    List<BuildingData> buildings;
    File mydir;
    int sdk;
    
    public BuildingAdapter(Activity activity, List<BuildingData> buildings) {
        this.activity = activity;
        this.buildings = buildings;
        mydir = activity.getDir("OwnerBuilding", Context.MODE_PRIVATE);
        sdk = android.os.Build.VERSION.SDK_INT;
    }

    @Override
    public BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View flatItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_building_list_item, parent, false);
        return new BuildingViewHolder(flatItem);

    }


	@Override
    public void onBindViewHolder(BuildingViewHolder holder, int position) {
    	holder.buildingName.setText(buildings.get(position).getBuilding_name());
    	holder.buildingType.setText(buildings.get(position).getBuilding_type());
    	holder.totalFlats.setText(buildings.get(position).getTotalFlats());
    	holder.vacantFlats.setText(buildings.get(position).getVacantFlats());    
    	diplayimage(holder.imageHolder, buildings.get(position).getBuilding_url());
 
    	holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(activity, ProfileTenantActivity.class);
                    //activity.startActivity(intent);
                    Intent intent = new Intent(activity, BuildingDetailActivity.class);
                    
/*         		    Pair<View, String> p1 = Pair.create(v, "profile");
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1);
                    startActivity(intent, options.toBundle());
*/                    
                    activity.startActivity(intent);
                }
            });
        
    }

    @SuppressWarnings("deprecation")
	private void diplayimage(LinearLayout imageHolder, String building_url) {
		// TODO Auto-generated method stub
    	if (building_url.length() > 0) {
    		String url = building_url.replace(":", "_");
    		url = url.replace("/", "_");
    		File file = new File(mydir, url);
    		if (file.exists()) {
    	        long length = file.length();
    	        if (length < 1) {
    				imageHolder.setBackgroundResource(R.drawable.ic_building);
    				
    				new DownloadAndSave(imageHolder, activity).execute(building_url);
    			} else {
    				Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
    				BitmapDrawable background = new BitmapDrawable(activity.getResources(), myBitmap);

    				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
    					imageHolder.setBackgroundDrawable(background);
    				} else {
    					imageHolder.setBackground(background);
    				}
    			}

    		} else {
    			imageHolder.setBackgroundResource(R.drawable.ic_building);
    			new DownloadAndSave(imageHolder, activity).execute(building_url);
    		}
		}else{
			imageHolder.setBackgroundResource(R.drawable.ic_building);
		}

	}

	@Override
    public int getItemCount() {
        return buildings.size();
    }

    public class BuildingViewHolder extends RecyclerView.ViewHolder {

        public CardView view;
        public TextView totalFlats, vacantFlats, buildingName, buildingType;
        public ImageButton broadcast, notification, edit;
        public LinearLayout imageHolder;

        public BuildingViewHolder(View itemView) {
            super(itemView);
            this.view = (CardView) itemView;
            this.totalFlats = (TextView) itemView.findViewById(R.id.textView_totalFlats);
            this.vacantFlats = (TextView) itemView.findViewById(R.id.textView_vacantFlats);
            this.buildingName = (TextView) itemView.findViewById(R.id.textView_buildingName);
            this.buildingType = (TextView) itemView.findViewById(R.id.textView_buildingType);
            this.broadcast = (ImageButton) itemView.findViewById(R.id.imageButton_broadcast);
            this.notification = (ImageButton) itemView.findViewById(R.id.imageButton_notification);
            this.edit = (ImageButton) itemView.findViewById(R.id.imageButton_editBuilding);
            this.imageHolder = (LinearLayout) itemView.findViewById(R.id.linarLayout_imageHolder);

        }
    }
    
	class DownloadAndSave extends AsyncTask<String, Void, String> {

		LinearLayout imv;
		Activity activity;

		public DownloadAndSave(LinearLayout imv, Activity activity) {
			super();
			this.imv = imv;
			this.activity = activity;
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String filepath = null;
			try {
				URL url = new URL(params[0]);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(false);
				urlConnection.connect();
				File mydir = activity.getDir("OwnerBuilding",Context.MODE_PRIVATE);
				String furl = params[0].replace(":", "_");
				furl = furl.replace("/", "_");
				File file = new File(mydir, furl);
				if (file.createNewFile()) {
					file.createNewFile();
				}
				FileOutputStream fileOutput = new FileOutputStream(file);
				InputStream inputStream = urlConnection.getInputStream();
				byte[] buffer = new byte[1024];
				int bufferLength = 0;
				while ((bufferLength = inputStream.read(buffer)) > 0) {
					fileOutput.write(buffer, 0, bufferLength);
				}
				fileOutput.close();
				filepath = file.getPath();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				filepath = null;
				e.printStackTrace();
			}
			return filepath;
						
			
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {
				Bitmap myBitmap = BitmapFactory.decodeFile(result);
				BitmapDrawable background = new BitmapDrawable(activity.getResources(), myBitmap);
				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
					imv.setBackgroundDrawable(background);
				} else {
					imv.setBackground(background);
				}
			}
		}

	}
}
