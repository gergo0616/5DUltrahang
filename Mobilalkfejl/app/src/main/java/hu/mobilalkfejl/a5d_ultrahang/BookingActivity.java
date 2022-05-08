package hu.mobilalkfejl.a5d_ultrahang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private static final String LOG_TAG = BookingActivity.class.getName();

    private ArrayList<BookingData> mBookingItemsData;
    private BookingItemAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private int gridNumber = 1;

    private FirebaseUser user;

    Button b;
    TextView text_view_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Log.d(LOG_TAG, "Authetnicated user!");
        } else{
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }
        b=(Button)findViewById(R.id.cancel);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(BookingActivity.this, R.anim.fadeout);
                b.startAnimation(animation);
            }
        });



        //Current Date:
        text_view_date = findViewById(R.id.text_view_date);

        text_view_date.setText(getCurrentDate());

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mBookingItemsData = new ArrayList<>();

        mAdapter = new BookingItemAdapter(this, mBookingItemsData);
        mRecyclerView.setAdapter(mAdapter);

        intializeData();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void intializeData() {
        String[] itemsTime = getResources().getStringArray(R.array.booking_times);
        String itemDate = getCurrentDate();

        //mBookingItemsData.clear();

        for(int i = 0; i < itemsTime.length; i++){
            mBookingItemsData.add(new BookingData(itemsTime[i], itemDate));
        }

        mAdapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_button:
                Log.d(LOG_TAG, "Logout clicked!");
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.settings_button:
                Log.d(LOG_TAG, "Setting clicked!");
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.cart:
                Log.d(LOG_TAG, "Cart clicked!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getCurrentDate(){
        return new SimpleDateFormat("yyyy. MM. dd", Locale.getDefault()).format(new Date());
    }

    public void cancel(View view) {
        finish();
    }


    public void add_to_booking(View view) {
        //TODO
    }
}