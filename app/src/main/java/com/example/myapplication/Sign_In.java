package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.regex.Pattern;

public class Sign_In extends AppCompatActivity implements   View.OnClickListener {
    private EditText edtemail, edtpass;
    private Button btnsignin, btnsingup;
    private FirebaseAuth mAuth;
    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaplication-12e26-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        anhxa();
        btnsingup.setOnClickListener(this);
        btnsignin.setOnClickListener(this);

    }
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.buttonSignup:
                startActivity(new Intent(this, Sign_up.class));
                break;
            case R.id.buttonSignin:
                userSignIn();
                break;
        }
}

    private void userSignIn() {
        String email = edtemail.getText().toString().trim();
        String pass = edtpass.getText().toString().trim();
        if(email.isEmpty()){
            edtemail.setError("Nhập Email");
            edtemail.requestFocus();
            return;

        } else if (pass.isEmpty()){
            edtemail.setError("Nhập Password");
            edtemail.requestFocus();

        }
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(Sign_In.this, MainActivity.class));
                            Toast.makeText(Sign_In.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Sign_In.this,"Đăng nhập không thành công, vui lòng đăng nhập lại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void anhxa() {
        btnsignin = findViewById(R.id.buttonSignin);
        edtemail = findViewById(R.id.edittextUser);
        edtpass = findViewById(R.id.edittextPassword);
        btnsingup = (Button) findViewById((R.id.buttonSignup));
    }


}
