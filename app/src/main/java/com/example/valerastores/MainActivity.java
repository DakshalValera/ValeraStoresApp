package com.example.valerastores;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    LinearLayout shampoo,bodylotion,facewash,bodywash,facecream,logout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        shampoo=findViewById(R.id.shampoo);
        bodylotion=findViewById(R.id.bodylotion);
        facewash=findViewById(R.id.facewash);
        bodywash=findViewById(R.id.bodywash);
        facecream=findViewById(R.id.facecream);

        logout=findViewById(R.id.logout);

        auth=FirebaseAuth.getInstance();

        logout.setOnClickListener(v -> {

            auth.signOut();
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        });

        shampoo.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, ShampooActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        bodylotion.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, BodyLotionActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        bodywash.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, BodyWashActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        facewash.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, FaceWashActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        facecream.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, FaceCreamActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}