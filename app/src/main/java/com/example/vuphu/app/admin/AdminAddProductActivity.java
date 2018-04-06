package com.example.vuphu.app.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.AcsynHttp.NetworkConst;
import com.example.vuphu.app.R;
import com.example.vuphu.app.object.Product;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import cz.msebera.android.httpclient.Header;

public class AdminAddProductActivity extends AppCompatActivity {


    private EditText edt_name_product, edt_price, edt_desc,edt_quantity;
    private EditText edt_type;
    private ImageView img_product;

    private FloatingActionButton btn_add_img;
    private Button btn_add;
    private static final int READ_REQUEST_CODE = 42;
    private SharedPreferences pre;
    protected   Uri uri;
    private ProgressDialog progressBar;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.admin_add_product_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setTitle("Add product");
        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Đang xử lí...");
        pre =getSharedPreferences("data", MODE_PRIVATE);
        init();
        setDataType();
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

    private void init() {
        edt_name_product = findViewById(R.id.edt_admin_add_name_product);
        edt_price = findViewById(R.id.edt_admin_add_product_price);
        edt_desc = findViewById(R.id.edt_admin_add_product_content);
        edt_quantity = findViewById(R.id.edt_admin_add_quantity_product);
        btn_add_img =findViewById(R.id.btn_admin_add_image);
        btn_add = findViewById(R.id.btn_admin_add_product);
        edt_type = findViewById(R.id.edt_admin_add_type_product);
        img_product = findViewById(R.id.img_admin_add_product);
    }

    private void setDataType() {

        btn_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performFileSearch();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.show();
                try {
                    add();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
    }

    public void performFileSearch() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            Log.i("link",uri.toString());
            try {

                final InputStream imageStream = getContentResolver().openInputStream(uri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                img_product.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        InputStream imageStream = getContentResolver().openInputStream(uri);
        Bitmap image = BitmapFactory.decodeStream(imageStream);
        return image;
    }


    private void add() throws IOException {
        RequestParams requestParams = new RequestParams();
        requestParams.put("name", edt_name_product.getText());
        requestParams.put("price",Integer.parseInt(edt_price.getText().toString()));
        requestParams.put("quatity",Integer.parseInt(edt_quantity.getText().toString()));
        requestParams.put("description",edt_desc.getText());
        requestParams.put("type",edt_type.getText());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        getBitmapFromUri(uri).compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        getBitmapFromUri(uri).recycle();
        requestParams.put("productImage",byteArray);
        AsyncHttpApi.post_admin_product(pre.getString("token",null),"/products/",requestParams, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progressBar.hide();
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}