package com.example.vuphu.app.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuphu.app.AcsynHttp.NetworkConst;
import com.example.vuphu.app.R;
import com.example.vuphu.app.object.Product;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailProductActivity extends AppCompatActivity {

    private TextView desprition, price ;

    private ImageView imageView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Intent in = getIntent() ;
        Product title = (Product) in.getSerializableExtra("data");

        setTitle(title.getName());


        imageView = findViewById(R.id.img_detail_product);
        Picasso.get().load(NetworkConst.network+"/"+title.getProductImage().replace("\\","/")).error(R.drawable.ic_terrain_black_24dp).placeholder(R.drawable.mypham).into(imageView);

        price = findViewById(R.id.tv_price);
        price.setText("Giá: "+title.getPrice()+" đ");

        desprition = findViewById(R.id.tv_description);

        desprition.setText(title.getDescription());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BuyProductActivity.class));
            }
        });
    }

    private Bitmap showImage(String iurl) {
        URL url = null;

        Bitmap bitmap = null;
        try {
            url = new URL(iurl);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            httpConn.connect();
            int resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConn.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);

                return bitmap;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
