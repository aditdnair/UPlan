package aditdnair.uplan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static aditdnair.uplan.Dashboard.dash;

public class Settings extends Activity {
    DatabaseHelper db;
    public Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        db = new DatabaseHelper(this);
        Button out = findViewById(R.id.signout);

        settings = this;

    }

    public void deleteData(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(settings);
        builder.setTitle("Delete Data");
        builder.setMessage("Are you sure you want to delete data?");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deleteData();
                        ToastMessage("Data deleted successfully");
                        dialog.cancel();
                        dash.displayexpense();
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert1 = builder.create();
        alert1.show();
    }

    public void signout(View view){
        FirebaseAuth.getInstance().signOut();
        ToastMessage("User signed out successfully");
        Intent back = new Intent("aditdnair.uplan.LoginActivity");
        startActivity(back);
        dash.finish();
        finish();
    }

    public void ToastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        dash.displayexpense();
        super.onBackPressed();
    }
}
