package burrow.co.in.owner.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import burrow.co.in.R;
import burrow.co.in.owner.adapter.HistoryAdapter;


public class OwnerSendHistory extends AppCompatActivity {
    HistoryAdapter historyAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_send_history);
        mtoolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        
        mtoolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	finish();
            }
        });

        expListView = (ExpandableListView) findViewById(R.id.historyExpandableListView);

        // preparing list data
        prepareListData();

        historyAdapter = new HistoryAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(historyAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Building 1");
        listDataHeader.add("Building 2");
        listDataHeader.add("Building 3");

        // Adding child data
        List<String> building1 = new ArrayList<String>();
        building1.add("Water Alert");
        building1.add("New Plumber Hired");
        building1.add("Lift Damaged");

        List<String> building2 = new ArrayList<String>();
        building2.add("Water Alert");
        building2.add("New Plumber Hired");
        building2.add("Lift Damaged");

        List<String> building3 = new ArrayList<String>();
        building3.add("Water Alert");
        building3.add("New Plumber Hired");
        building3.add("Lift Damaged");

        listDataChild.put(listDataHeader.get(0), building1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), building2);
        listDataChild.put(listDataHeader.get(2), building3);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_owner_building, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
