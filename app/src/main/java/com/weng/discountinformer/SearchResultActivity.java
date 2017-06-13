package com.weng.discountinformer;
/**
 * Created by Weng Anxiang on 2017/3/11.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private List<DiscountAbstractInfo> discountAbstractInfos = new ArrayList<>();
    String searchResult;
    Button button_back_to_homepage;
    Button button_search2;
    EditText editText2;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        //一下一行代码仅做测试用
        initDiscountList();
        Intent intent = getIntent();//获得上一个页面的intent
        searchResult = intent.getStringExtra("searchWord");//获取上一个活动的搜索框的文字
        //从服务器中调用带有搜索文字的数据返回到界面中来

        button_back_to_homepage = (Button) findViewById(R.id.button_back_to_homepage);
        button_back_to_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_search2 = (Button) findViewById(R.id.button_search_result_search);
        editText2 = (EditText) findViewById(R.id.edit_text_search2);
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//根据搜索框的内容更新recyclerview
                String s = editText2.getText().toString();
                //下面的代码应该根据s更新recyclerview

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.serch_result_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DiscountListTitleAdapter adapter = new DiscountListTitleAdapter(discountAbstractInfos);
        recyclerView.setAdapter(adapter);
    }
    private void initDiscountList()
    {
        for (int i = 0; i < 3; i++)
        {
            discountAbstractInfos.add(new DiscountAbstractInfo("东门安踏体育精选鞋品买一送一（至10.16）", R.drawable.anta));
            discountAbstractInfos.add(new DiscountAbstractInfo("华润万家微信支付优惠！逢周五用微信满50元减5元", R.drawable.vanga));
            discountAbstractInfos.add(new DiscountAbstractInfo("天虹超市惊喜感谢妈妈大放价", R.drawable.tianhong));
            discountAbstractInfos.add(new DiscountAbstractInfo("永旺购物中心第二件半价！", R.drawable.aeon));
            discountAbstractInfos.add(new DiscountAbstractInfo("Nike全场五折起！", R.drawable.nike));
            discountAbstractInfos.add(new DiscountAbstractInfo("Adidas龙华天虹店6折起", R.drawable.adidas));
            discountAbstractInfos.add(new DiscountAbstractInfo("以纯全市满300减50", R.drawable.yishion));

        }
    }

}
