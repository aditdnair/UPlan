package aditdnair.uplan;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Expenses extends Activity {
    DatabaseHelper db;
    ListView listView;
    private static final String TAG = "Expenses";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        db=new DatabaseHelper(this);
        listView=(ListView)findViewById(R.id.view1);
        show();
    }

    public void show(){
        Log.d(TAG,"Display");
        Cursor data=db.expenses();
        ArrayList<String> listData=new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(0)+"\n"+data.getString(1)+"\n"+data.getString(2)+"\n"+data.getString(3));
           /* listData.add(data.getString(1));
            listData.add(data.getString(2));
            listData.add(data.getString(3));*/
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
