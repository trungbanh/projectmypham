package com.example.vuphu.app.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.AcsynHttp.NetworkConst;
import com.example.vuphu.app.R;
import com.example.vuphu.app.RetrofitAPI.ApiUtils;
import com.example.vuphu.app.object.Product;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminEditProductActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;



    private EditText edt_name_product, edt_price, edt_desc, edt_quantity;
    private TextView select ;
    private Spinner type_product;
    private ImageView img_product;
    private Product product;
    private FloatingActionButton btn_update_img;
    private ArrayAdapter<String> listType ;
    private Button btn_update;

    private SharedPreferences pre;
    private String arr [] = {
            "lotion",
            "hair care",
            "skin care cosmetics",
            "perfume",
            "lipstick"};

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
        type_product.setAdapter(listType);
        pre =getSharedPreferences("data", MODE_PRIVATE);
        Intent intent = getIntent();

        product = new Product();
        if (intent!=null) {
            product.setId(intent.getStringExtra("productID"));
            product.setName(intent.getStringExtra("productNAME"));
            product.setPrice(intent.getIntExtra("productPRICE",0));
            product.setQuatity(intent.getIntExtra("productQUATITY",0));
            product.setDescription(intent.getStringExtra("productDES"));
            product.setProductImage(intent.getStringExtra("productIMAGE"));
        }
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
        select = findViewById(R.id.tv_select);
        listType = new ArrayAdapter<>(this.getApplicationContext(),android.R.layout.simple_spinner_item,arr);
        listType.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
    }

    private void setDataType() {
        setTitle(product.getName());
        edt_name_product.setText(product.getName());
        edt_price.setText (String.valueOf(product.getPrice()));
        edt_quantity.setText(String.valueOf(product.getQuatity()));
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
                UpdateProduct();
            }
        });
        type_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select.setText(arr[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
    private void UpdateProduct () {
        File im = new File (getRealPathFromURI(uri));
        RequestBody image = RequestBody.create(MediaType.parse("image/*"),im);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"),edt_name_product.getText().toString());
        RequestBody price = RequestBody.create(MediaType.parse("text/plain"),edt_price.getText().toString());
        RequestBody quatity = RequestBody.create(MediaType.parse("text/plain"),edt_quantity.getText().toString());
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"),edt_desc.getText().toString());
        RequestBody type = RequestBody.create(MediaType.parse("text/plain"),select.getText().toString());
        ApiUtils.getAPIService().upDateProduct(
                "Bearer "+ pre.getString(NetworkConst.token,""),
                image,name,price,quatity,description,type).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("se",response.message());
                Log.i("se",response.isSuccessful()+"");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("sf",t.getMessage());
            }
        });
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}
