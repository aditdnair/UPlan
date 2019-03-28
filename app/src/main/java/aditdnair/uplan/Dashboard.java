package aditdnair.uplan;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Dashboard extends Activity {
    DatabaseHelper db;
    public static String currency = "INR";

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
    String strdate =(String) formatter.format(date);

    public static Dashboard dash;
    public static float dfood= 0 ;
    public static float dhealth= 0;
    public static float dtravel= 0;
    public static float dloans= 0;
    public static float dbills= 0;
    public static float deducation= 0;
    public static float drecreation= 0;
    public static float dhousing= 0;
    public static float dmisc= 0;
    public static float dincome= 0;
    public static float dexpense= 0;
    public static float diff= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dash = this;
        db = new DatabaseHelper(this);

        displayexpense();
        //graph();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_button:
                open_settings();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void displayexpense(){
        dfood= db.food();
        dhealth=db.health();
        dtravel=db.travel();
        dloans=db.loans();
        dbills=db.bills();
        deducation=db.education();
        drecreation=db.recreation();
        dhousing=db.housing();
        dmisc=db.misc();
        dincome = db.income();
        dexpense = db.expenses1();
        diff = db.difference();

        TextView f1 = findViewById(R.id.tfood);
        f1.setText(currency+String.valueOf(dfood));

        TextView h1 = findViewById(R.id.thealth);
        h1.setText(currency+String.valueOf(dhealth));

        TextView t1 = findViewById(R.id.ttravel);
        t1.setText(currency+String.valueOf(dtravel));

        TextView b1 = findViewById(R.id.tbills);
        b1.setText(currency+String.valueOf(dbills));

        TextView e1 = findViewById(R.id.teducation);
        e1.setText(currency+String.valueOf(deducation));

        TextView r1 = findViewById(R.id.trecreation);
        r1.setText(currency+String.valueOf(drecreation));

        TextView ho1 = findViewById(R.id.thousing);
        ho1.setText(currency+String.valueOf(dhousing));

        TextView l1 = findViewById(R.id.tloans);
        l1.setText(currency+String.valueOf(dloans));

        TextView m1 = findViewById(R.id.tmisc);
        m1.setText(currency+String.valueOf(dmisc));

        TextView se = findViewById(R.id.sexpense);
        se.setText("Expense: "+currency+String.valueOf(dexpense));

        TextView si = findViewById(R.id.sincome);
        si.setText("Income: "+currency+String.valueOf(dincome));

        TextView sav = findViewById(R.id.ssavings);
        sav.setText("Savings: "+currency+String.valueOf(diff));

        PieChartView pieChartView = findViewById(R.id.piechart);
        pie_chart(pieChartView);
    }

    public void showexpenses(View view){
        Intent expense = new Intent("aditdnair.uplan.Expenses");
        startActivity(expense);
    }

    public void showincome(View view){
        Intent income = new Intent("aditdnair.uplan.Income");
        startActivity(income);
    }

    public void open_settings() {
        Intent settings = new Intent("aditdnair.uplan.Settings");
        startActivity(settings);
    }

    public void enterdata(View view){
        Intent enterd = new Intent("aditdnair.uplan.Enter");
        startActivity(enterd);
    }

    public void pie_chart(PieChartView pieChartView){

        List pieData = new ArrayList<>();

        pieData.add(new SliceValue(dfood, Color.parseColor("#09ff17")).setLabel("Food"));
        pieData.add(new SliceValue(dhealth, Color.parseColor("#00ffff")).setLabel("Health"));
        pieData.add(new SliceValue(deducation, Color.parseColor("#00aaff")).setLabel("Education"));
        pieData.add(new SliceValue(dloans, Color.parseColor("#995bcc")).setLabel("Loans"));
        pieData.add(new SliceValue(dbills, Color.parseColor("#ee00ee")).setLabel("Bills"));
        pieData.add(new SliceValue(drecreation, Color.parseColor("#ff0000")).setLabel("Recreation"));
        pieData.add(new SliceValue(dhousing, Color.parseColor("#ffaa00")).setLabel("Housing"));
        pieData.add(new SliceValue(dtravel, Color.parseColor("#ffff00")).setLabel("Travel"));
        pieData.add(new SliceValue(dmisc, Color.parseColor("#555555")).setLabel("Misc"));


        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true);
        pieChartView.setPieChartData(pieChartData);
    }

   //public void graph(){

   //    GraphView graph = findViewById(R.id.income_graph);
   //    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
   //            new DataPoint(1, 1200),
   //            new DataPoint(2, 1100),
   //            new DataPoint(3, 1200),
   //            new DataPoint(4, 1050)
   //    });
   //    graph.addSeries(series);
   //    series.setBackgroundColor(0x4409ff17);
   //    graph.getViewport().setScalable(true);
   //    graph.getViewport().setScrollable(true);
   //    series.setColor(0xff09ff17);
   //    series.setDrawBackground(true);
   //    series.setThickness(10);
   //    graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);

   //    GraphView savings = findViewById(R.id.savings_graph);
   //    LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[] {
   //            new DataPoint(1, 1200),
   //            new DataPoint(2, 1100),
   //            new DataPoint(3, 1200),
   //            new DataPoint(4, 1050)
   //    });
   //    savings.addSeries(series1);
   //    series1.setBackgroundColor(0x4400aeff);
   //    savings.getViewport().setScalable(true);
   //    savings.getViewport().setScrollable(true);
   //    series1.setColor(0xff00aeff);
   //    series1.setDrawBackground(true);
   //    series1.setThickness(10);
   //    savings.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
   //}
}
