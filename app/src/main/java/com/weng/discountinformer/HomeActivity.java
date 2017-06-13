package com.weng.discountinformer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * Created by Weng Anxiang on 2017/3/11.
 */
public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private EditText editText;
    //private Button search_button;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //用户左拉菜单
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.user_menu_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        }
        navigationView.setCheckedItem(R.id.user_profile);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

        editText = (EditText) findViewById(R.id.edit_text_search) ;
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        /*
        search_button = (Button) findViewById(R.id.button_home_search);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchResultActivity.class);
                String edit_text = editText.getText().toString();
                intent.putExtra("from_search_edit", edit_text);//把搜索框的信息保存到intent中
                startActivity(intent);
            }
        });
        */
    }
}
