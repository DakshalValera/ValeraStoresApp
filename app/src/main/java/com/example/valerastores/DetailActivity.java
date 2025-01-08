package com.example.valerastores;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView productname, productprice,productweight;
    ImageView productimage;

    Button btnemail,addtocart;

    ImageView goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        productname = findViewById(R.id.productname);
        productprice = findViewById(R.id.productprice);
        productweight=findViewById(R.id.productweight);
        productimage = findViewById(R.id.productimage);
        btnemail = findViewById(R.id.btnemail);
        addtocart = findViewById(R.id.btnaddtocard);
        goback = findViewById(R.id.goback);

        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String image = getIntent().getStringExtra("image");
        String weight = getIntent().getStringExtra("weight");

        productname.setText(name);
        productprice.setText(price);
        productweight.setText(weight);
        Picasso.get().load(image).into(productimage);

        btnemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"dakshal152005valera@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Confirm Order to Valera Stores");
                email.putExtra(Intent.EXTRA_TEXT,"I Confirm my Order to Valera Stores For "+name+" With Price "+price+" and Weight are "+weight);
                email.setType("message/rfc822");
                startActivity(email);
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("smsto:7383276866"));
                i.putExtra("sms_body","I Confirm my Order to Valera Stores For "+name+" With Price "+price+" and Weight are "+weight);
                startActivity(i);
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}