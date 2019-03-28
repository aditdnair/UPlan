package aditdnair.uplan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;

import static aditdnair.uplan.Enter.enter;

public class Date extends Activity {

    DatePicker picker;
    public static Date date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        date1 = this;

        picker = findViewById(R.id.datePicker);
    }

    public String getCurrentDate() {
        String str;
        str = (picker.getDayOfMonth() + "/");
        if (picker.getMonth() + 1 < 10) {
            str = str + "0" + (picker.getMonth() + 1) + "/";
        } else {
            str = str + (picker.getMonth() + 1) + "/";//month is 0 based
        }
        str = str + picker.getYear();
        return str;
    }

    public void pickd(View view){
        enter.displayd();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
