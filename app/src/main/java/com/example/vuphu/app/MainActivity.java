package com.example.vuphu.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.admin.AdminCatogoriesFragment;
import com.example.vuphu.app.admin.AdminOrdersFragment;
import com.example.vuphu.app.admin.AdminUserFragment;
import com.example.vuphu.app.login_signUp.LoginActivity;
import com.example.vuphu.app.object.users;
import com.example.vuphu.app.user.AddMoneyFragment;
import com.example.vuphu.app.user.CatogriesFragment;
import com.example.vuphu.app.user.ProfileFragment;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MaterialSearchView searchView;

    private SharedPreferences pre;
    private SharedPreferences.Editor edit;

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                AsyncHttpApi.get(pre.getString("token",""),"/products/search/"+query,null,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        android.support.v4.app.FragmentTransaction transaction;
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content, SearchFragment.newInstance(response)).addToBackStack(null);
                        transaction.commit();

                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AsyncHttpApi.get(pre.getString("token",""),"/products/search/"+newText,null,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        android.support.v4.app.FragmentTransaction transaction;
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content, SearchFragment.newInstance(response)).addToBackStack(null);
                        transaction.commit();

                    }
                });
                return false;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        pre =getSharedPreferences("data", MODE_PRIVATE);
        edit=pre.edit();
        String user=pre.getString("type_user", "");
        //set drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (user.equals("admin")) {
            navigationView.inflateMenu(R.menu.activity_main_admin);
            setTitle("Danh mục sản phẩm");
            android.support.v4.app.FragmentTransaction transaction;
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, AdminCatogoriesFragment.newInstance());
            transaction.commit();
        }
        else {
            navigationView.inflateMenu(R.menu.activity_main_drawer);
            android.support.v4.app.FragmentTransaction transaction;
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, CatogriesFragment.newInstance());
            transaction.commit();
        }
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentManager fm = getSupportFragmentManager();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }
        else
            applyExit();

    }
    private void applyExit() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(this,"Press Again to exit", Toast.LENGTH_LONG).show();
        }
        mBackPressed = System.currentTimeMillis();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment temp = null;
        if (id == R.id.nav_admin_logout) {
            edit.clear();
            edit.apply();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;
        }else
        if (id == R.id.nav_logout) {
            edit.clear();
            edit.apply();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;

        } else if (id == R.id.nav_profile) {
            users u = new users();
            temp = ProfileFragment.newInstance(u);
            setTitle("Thông tin tài khoản");
        } else if (id == R.id.nav_cato) {
            setTitle("Danh mục sản phẩm");
            temp = CatogriesFragment.newInstance();

        } else if (id == R.id.nav_add_money) {
            setTitle("Nạp tiền");
            temp = AddMoneyFragment.newInstance();
        } else if (id == R.id.nav_admin_cato){
            setTitle("Danh mục sản phẩm");
            temp = AdminCatogoriesFragment.newInstance();
        }
        else if (id == R.id.nav_admin_orders){
            setTitle("Đơn hàng");
            temp = AdminOrdersFragment.newInstance();
        }
        else if (id == R.id.nav_admin_user){
            setTitle("Người dùng");
            temp = AdminUserFragment.newInstance();
        }
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,temp);;
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
