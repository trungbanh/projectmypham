package com.example.vuphu.app.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vuphu.app.R;
import com.example.vuphu.app.object.Product;
import com.example.vuphu.app.user.DetailProductActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vuphu on 3/31/2018.
 */

public class ProductApdater {


    private static class productViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_product;
        private TextView tv_name, tv_price;
        public productViewHolder(View itemView) {
            super(itemView);
            img_product = itemView.findViewById(R.id.img_product);
            tv_name = itemView.findViewById(R.id.tv_name_product);
            tv_price = itemView.findViewById(R.id.tv_price);

        }
    }

    public static class productAdap extends RecyclerView.Adapter<productViewHolder>{

        ArrayList<Product> list;
        Context context;
        public productAdap(ArrayList<Product> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public productViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_product, null);
            return new productViewHolder(v);
        }

        @Override
        public void onBindViewHolder(productViewHolder holder, final int position) {
            holder.tv_name.setText(list.get(position).getName());
            holder.tv_price.setText(String.valueOf(list.get(position).getPrice()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    intent.putExtra("data",list.get(position));
                    context.startActivity(intent);
                }
            });

            //holder.img_product.setImageBitmap(showImage ("http://10.0.2.2:3300"+list.get(position).getProductImage()));
            //http://localhost:3300/tmp/2018-03-31T07-55-41.166Z1408855658.jpg
            Glide.with(context).load("http://10.0.2.2:3300"+list.get(position).getProductImage()).into(holder.img_product);
            Log.i("image",list.get(position).getProductImage());
        }

        @Override
        public int getItemCount() {
            return list.size();
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
    }




}
