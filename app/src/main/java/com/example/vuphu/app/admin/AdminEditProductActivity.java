package com.example.vuphu.app.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AdminEditProductActivity extends AppCompatActivity {


    private EditText edt_name_product, edt_price, edt_desc, edt_quantity;
    private Spinner type_product;

    private String arr [] = {
            "lotion",
            "hair care",
            "skin care cosmetics",
            "nước hoa",
            "lipstick"

    };

    private ImageView img_product;
    private Product product;
    private FloatingActionButton btn_update_img;
    private ArrayAdapter<String> listType ;
    private Button btn_update;
    private static final int READ_REQUEST_CODE = 42;
    private ProgressDialog progressBar;
    private SharedPreferences pre;

    protected   Uri uri;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        init();
        listType = new ArrayAdapter<>(this.getApplicationContext(),android.R.layout.simple_spinner_item,arr);

        listType.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        type_product.setAdapter(listType);
        pre =getSharedPreferences("data", MODE_PRIVATE);
        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Đang xử lí...");

        Intent intent = getIntent();

        type_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        product = (Product) intent.getSerializableExtra("data");

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
        edt_name_product = findViewById(R.id.edt_admin_name_product);
        edt_price = findViewById(R.id.edt_admin_product_price);
        edt_desc = findViewById(R.id.edt_admin_product_content);
        edt_quantity = findViewById(R.id.edt_admin_quantity_product);
        type_product = findViewById(R.id.spinner_type_product);
        img_product = findViewById(R.id.img_admin_edit_product);
        btn_update_img = findViewById(R.id.btn_admin_edit_image);
        btn_update = findViewById(R.id.btn_admin_edit_product);
    }

    private void setDataType() {
        setTitle(product.getName());
        edt_name_product.setText(product.getName());
        edt_price.setText (product.getPrice()+ "đ");
        edt_quantity.setText("" + product.getQuatity());
        //type_product.setText(product.getType());
        edt_desc.setText(product.getDescription());
        Picasso.get().load(NetworkConst.network + "/" + product.getProductImage()
                .replace("\\", "/")).error(R.drawable.ic_terrain_black_24dp)
                .placeholder(R.drawable.mypham).into(img_product);

        btn_update_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performFileSearch();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.show();
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void performFileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                uri = resultData.getData();
                Log.i("TAG", "Uri: " + uri.toString());
                try {
                    showImage(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    private void showImage(Uri uri) throws IOException {
        img_product.setImageBitmap(getBitmapFromUri(uri));

    }

    private void update() throws IOException {

        RequestParams requestParams = new RequestParams();
        requestParams.put("name", edt_name_product.getText());
//        requestParams.put("price",Integer.parseInt(edt_price.getText().toString()));
        requestParams.put("quatity",Integer.parseInt(edt_quantity.getText().toString()));
        requestParams.put("description",edt_desc.getText());
        requestParams.put("type",type_product.getSelectedItem().toString());
            AsyncHttpApi.put(pre.getString("token", null), "/products/" + product.getId(), requestParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    progressBar.hide();
                    Toast.makeText(AdminEditProductActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                }
            });
//        }
    }
}
