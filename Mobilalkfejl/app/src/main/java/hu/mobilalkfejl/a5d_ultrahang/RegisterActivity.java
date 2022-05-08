package hu.mobilalkfejl.a5d_ultrahang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    EditText lnameEditText;
    EditText fnameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;
    RadioGroup accountGenderGroup;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Bundle bundle = getIntent().getExtras();
        //int secret_key = bundle.getInt("SECRET_KEY");
        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if(secret_key != 99){
            finish();
        }
        lnameEditText = findViewById(R.id.fnameEditText);
        fnameEditText = findViewById(R.id.lnameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordAgainEditText = findViewById(R.id.passwordAgainEditText);
        accountGenderGroup = findViewById(R.id.accountGenderGroup);
        accountGenderGroup.check(R.id.menRadioButton);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String email = preferences.getString("emailStr", "");
        String password = preferences.getString("passwordStr", "");

        emailEditText.setText(email);
        passwordEditText.setText(password);

        mAuth = FirebaseAuth.getInstance();

        Log.i(LOG_TAG, "onCreate");
    }


    public void register(View view) {
        String lnameStr = lnameEditText.getText().toString();
        String fnameStr = fnameEditText.getText().toString();
        String emailStr = emailEditText.getText().toString();
        String passwordStr = passwordEditText.getText().toString();
        String passwordAgainStr = passwordAgainEditText.getText().toString();

        if(!passwordStr.equals(passwordAgainStr)){
            Log.e(LOG_TAG, "Nem egyezik a jelszó");
        }

        int checkedGender = accountGenderGroup.getCheckedRadioButtonId();
        RadioButton radioButton = accountGenderGroup.findViewById(checkedGender);
        String accountGender = radioButton.getText().toString();

        Log.i(LOG_TAG, "Regisztrált " + lnameStr + " " + fnameStr + " email: " + emailStr + " jelszó: " + passwordStr + " jelszóMegerősítés: " + passwordAgainStr);
        //TODO: a regisztracios funkcionalitast meg kellene valositani
        //startBooking();

        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(LOG_TAG, "User created successfully!");
                    startBooking();
                } else{
                    Log.d(LOG_TAG, "User wasn't created successfully!");
                    Toast.makeText(RegisterActivity.this, "User wasn't created successfully "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void cancel(View view) {
        finish();
    }

    private void startBooking(/* registered user data*/){
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }

}