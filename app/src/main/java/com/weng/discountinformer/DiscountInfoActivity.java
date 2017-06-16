package com.weng.discountinformer;

/**
 * Created by Weng Anxiang on 2017/3/11.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.weng.discountinformer.util.LogUtil;

import org.w3c.dom.Text;

public class DiscountInfoActivity extends AppCompatActivity {
    private TextView discountTitle;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("DiscountInfoActivity", "Destroyed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_discount_info);
        setSupportActionBar(toolbar);

        //在toolbar左侧添加返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiscountInfoActivity.this, SearchResultActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent= getIntent();
        String title = intent.getStringExtra("title");
        discountTitle = (TextView) findViewById(R.id.title_text_discount_info);
        discountTitle.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.disount_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_share:
                Toast.makeText(DiscountInfoActivity.this, "点此按钮分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_collect:
                Toast.makeText(DiscountInfoActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_collect_store:
                Toast.makeText(DiscountInfoActivity.this, "关注本店", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
