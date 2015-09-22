package burrow.co.in.owner.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import burrow.co.in.Constants;
import burrow.co.in.owner.adapter.BuildingAdapter;
import burrow.co.in.owner.beanclass.BuildingData;

public class BuildingDetails {
	
	Activity activity;
	RecyclerView recyclerView;
	SharedPreferences prefs;	
	
	public BuildingDetails(Activity activity, RecyclerView recyclerView) {
		super();
		this.activity = activity;
		this.recyclerView = recyclerView;
		prefs = activity.getSharedPreferences(Constants.SHARED_PREF_NAME_BUILDING_DETAIL, Context.MODE_PRIVATE);
	}

	public void setBuildingAdapter(){
		
    	int status = prefs.getInt(Constants.SHARED_PREF_NAME_BUILDING_DETAIL_FLAG, Constants.NEED_TO_UPDATE);
    	
    	if (status == Constants.UPTO_DATE) {
    		String json = prefs.getString(Constants.SHARED_PREF_NAME_BUILDING_DETAIL_DATA, "");
    		readJson(json);
		} else {
			new AsyncHttpTask().execute(Constants.SHARED_PREF_NAME_BUILDING_DETAIL_GET);
		}

	}

    public class AsyncHttpTask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            BufferedReader reader = null;
            StringBuilder sb = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("email", "amaldev@tigerinnovations.org");
                connection.setRequestProperty("token", "ya11");
                connection.setRequestMethod("GET");
                connection.connect();
                int statusCode = connection.getResponseCode();
                if (statusCode == 200) {
                	    sb = new StringBuilder();
                	    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                             sb.append(line + "\n");
                        }
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
            if (sb != null ) {
				return sb.toString();
			}
            return "";
        }

        @Override
        protected void onPostExecute(String result){
           	if (result.length() > 1) {
                Editor editor = prefs.edit();
                editor.putString(Constants.SHARED_PREF_NAME_BUILDING_DETAIL_DATA, result);
                editor.putInt(Constants.SHARED_PREF_NAME_BUILDING_DETAIL_FLAG, Constants.UPTO_DATE);
                editor.commit();
                readJson(result);
    		}        	
        }
    }
    
    private void readJson(String strJson){
    	
            List<BuildingData> buildings = new ArrayList<BuildingData>();
            try {
                JSONObject rootJsonObject = new JSONObject(strJson);

                JSONArray jsonArray = rootJsonObject.optJSONArray("dev_toast");

                for(int i=0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        BuildingData buildingData=new BuildingData();
                        buildingData.setBuilding_id(jsonObject.optString("_id").toString());
                        buildingData.setBuilding_name(jsonObject.optString("building_name").toString());
                        buildingData.setBuilding_address(jsonObject.optString("building_address"));
                        buildingData.setBuilding_pincode(jsonObject.optString("building_pincode"));
                        buildingData.setBuilding_type(jsonObject.optString("building_type"));
                        buildingData.setBuilding_url(jsonObject.optString("building_image"));
                        buildings.add(buildingData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (NullPointerException e){
          	// no net connection
            }
            recyclerView.setAdapter(new BuildingAdapter(activity, buildings));
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }
}
