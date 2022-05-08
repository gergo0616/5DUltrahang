package hu.mobilalkfejl.a5d_ultrahang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AppActivity extends AppCompatActivity {
    private static final String LOG_TAG = AppActivity.class.getName();
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Log.d(LOG_TAG, "Authetnicated user!");
        } else{
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }


    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public void booking(View view) {
        Intent intent = new Intent(this, BookingActivity.class);
        //intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void cancel(View view) {
        finish();
    }

}