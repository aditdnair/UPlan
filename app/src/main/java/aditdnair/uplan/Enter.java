package aditdnair.uplan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import static aditdnair.uplan.Dashboard.dash;
import static aditdnair.uplan.Date.date1;


public class Enter extends Activity implements  AdapterView.OnItemSelectedListener  {
    EditText ename,eamount,edate;
    Button add,delete,show,update;
    DatabaseHelper db;
    java.util.Date date = new java.util.Date();

    public String ecategory;
    public static Enter enter;
    String tdate = new SimpleDateFormat("dd/MM/YYYY").format(date);

        String[] category = { "Income", "Food", "Health", "Hosuing", "Loans", "Recreation", "Bills", "Travel", "Education", "Miscellaneous"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        db=new DatabaseHelper(this);
        ename=(EditText)findViewById(R.id.name);
        eamount=(EditText)findViewById(R.id.amount);
        //edate=(EditText)findViewById(R.id.date);
        //ecategory=(EditText)findViewById(R.id.category);
        add=(Button)findViewById(R.id.add);

        enter = this;

        TextView textView = findViewById(R.id.todate);
        textView.setText(tdate);

        Spinner spinner = findViewById(R.id.spin_category);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
        }


    //Performing action onItemSelected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
            ecategory = category[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void displayd() {
        TextView textView = findViewById(R.id.todate);
        tdate = date1.getCurrentDate();
        textView.setText(tdate);
    }

    public void addData(View view) {
        String newName = ename.getText().toString();
        String newAmount = eamount.getText().toString();
        String newDate = tdate;
        String newCategory = ecategory;
        newCategory = newCategory.toUpperCase();
        if (ename.length() != 0 && eamount.length() != 0 && newDate.length() != 0 && ecategory.length() != 0) {
            db.addData1(newName, newAmount, newDate, newCategory);
            ename.setText("");
            eamount.setText("");
            //edate.setText("");
            //ecategory.setText("");
            ToastMessage("Data inserted");
            dash.displayexpense();
        } else {
            ToastMessage("Enter Data");
        }
    }

    public void pick(View view){
        Intent picker = new Intent("aditdnair.uplan.Date");
        startActivity(picker);
    }

    public void ToastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
