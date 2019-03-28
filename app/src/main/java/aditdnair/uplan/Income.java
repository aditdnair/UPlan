package aditdnair.uplan;

import android.app.Activity;
import android.database.Cursor;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Income extends Activity {
    ListView listView;
    DatabaseHelper db;
    private static final String TAG="Income";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        listView=(ListView)findViewById(R.id.list);
        db=new DatabaseHelper(this);
        show();
    }

   public void show(){
       Log.d(TAG,"Display");
       Cursor data=db.income1();
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
