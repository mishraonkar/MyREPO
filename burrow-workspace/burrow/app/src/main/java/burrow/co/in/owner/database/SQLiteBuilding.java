package burrow.co.in.owner.database;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import burrow.co.in.owner.beanclass.BuildingData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amma on 9/16/2015.
 */
public class SQLiteBuilding extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "burrow";

    // Contacts table name
    private static final String TABLE_BUILDING = "buildings";

    // Contacts Table Columns names
    private static final String BUILDING_ID = "building_id";
    private static final String BUILDING_NAME = "building_name";
    private static final String BUILDING_ADDRESS = "building_address";
    private static final String BUILDING_PINCODE = "building_pincode";
    private static final String BUILDING_TYPE = "building_type";
    private static final String BUILDING_URL = "building_url";
    String CREATE_BUILDING_TABLE="";
    public SQLiteBuilding(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        CREATE_BUILDING_TABLE = "CREATE TABLE "
                + TABLE_BUILDING
                + "("
                    + BUILDING_ID + " TEXT PRIMARY KEY,"
                    + BUILDING_NAME + " TEXT,"
                    + BUILDING_ADDRESS + " TEXT,"
                    + BUILDING_PINCODE + " TEXT,"
                    + BUILDING_TYPE + " TEXT,"
                    + BUILDING_URL + " TEXT"
                + ");";
//        Log.e("SQL Query : ",CREATE_BUILDING_TABLE);
        db.execSQL(CREATE_BUILDING_TABLE);
    }

//    BuildingData getBuildingData(){
//
//    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDING);
        // Create tables again
        onCreate(db);
    }

    // Adding building details to the database
    public void AddBuilding(BuildingData buildingData){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUILDING_ID, buildingData.getBuilding_id());             // Building ID
        values.put(BUILDING_NAME,buildingData.getBuilding_name());          // Building Name
        values.put(BUILDING_ADDRESS,buildingData.getBuilding_address());    // Building Address
        values.put(BUILDING_PINCODE,buildingData.getBuilding_pincode());    // Building Pincode
        values.put(BUILDING_TYPE,buildingData.getBuilding_type());          // Building Type
        values.put(BUILDING_URL,buildingData.getBuilding_url());            // Building URL
        //String log="ID : "+buildingData.getBuilding_id()+" Name :"+buildingData.getBuilding_name()+" Address: "+buildingData.getBuilding_address()+" Pincode: "+buildingData.getBuilding_pincode()+" Type : "+buildingData.getBuilding_type()+" URL : "+buildingData.getBuilding_url();
        //Log.e("Data ",log);
        // Inserting Row
        db.insert(TABLE_BUILDING, null, values);
        db.close(); // Closing database connection
    }

    // Getting all building stored information
    public List<BuildingData> GetAllBuilding(){
        List<BuildingData> buildingList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BUILDING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BuildingData buildingData = new BuildingData();
                buildingData.setBuilding_id(cursor.getString(0));
                buildingData.setBuilding_name(cursor.getString(1));
                buildingData.setBuilding_address(cursor.getString(2));
                buildingData.setBuilding_pincode(cursor.getString(3));
                buildingData.setBuilding_type(cursor.getString(4));
                buildingData.setBuilding_url(cursor.getString(5));
                buildingList.add(buildingData);
            } while (cursor.moveToNext());
        }
        //Log.e("Address ",buildingList.get(3).getBuilding_address());
        return buildingList;         // return building list
    }

    // Delete all building information
    public void DeleteBuilding(BuildingData buildingData){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUILDING, null,null);
        db.close();
    }

    // Getting building count
    public int GetBuildingsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BUILDING;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count=cursor.getCount();
        cursor.close();
        return count;
    }
}
