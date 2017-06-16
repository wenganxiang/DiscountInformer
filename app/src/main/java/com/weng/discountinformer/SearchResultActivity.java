package com.weng.discountinformer;
/**
 * Created by Weng Anxiang on 2017/3/11.
 */
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.weng.discountinformer.adapter.DiscountListAdapter;
import com.weng.discountinformer.util.LogUtil;
import com.weng.discountinformer.util.Utility;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private List<DiscountAbstractInfo> discountAbstractInfos = new ArrayList<>();
    private Button button_back_to_homepage;
    //private RecyclerView recyclerView;
    private ListView listViewSearchResult;
    private TextView textViewTop;

    private String searchWord;//从上一个活动获得的搜索词

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("SearchResult", "Destroyed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d("SearchResult", "Start");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //一下一行代码仅做测试用
        initDiscountList();

        Intent intent = getIntent();//获得上一个页面的intent
        searchWord = intent.getStringExtra("searchWord");//获取上一个活动的搜索框的文字
        //从服务器中调用带有搜索文字的数据返回到界面中来
        queryServer(searchWord);

        button_back_to_homepage = (Button) findViewById(R.id.button_back_to_homepage);
        button_back_to_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        textViewTop = (TextView) findViewById(R.id.text_view_search_result);
        textViewTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


        /*
        recyclerView = (RecyclerView) findViewById(R.id.serch_result_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DiscountListTitleAdapter adapter = new DiscountListTitleAdapter(discountAbstractInfos);
        recyclerView.setAdapter(adapter);
        */
        //recyclerView的点击事件在DiscountListTitleAdapter中的onClick()方法中完成

        DiscountListAdapter discountListAdapter = new DiscountListAdapter(SearchResultActivity.this, R.layout.discount_item, discountAbstractInfos);
        listViewSearchResult = (ListView) findViewById(R.id.list_view_search_result);
        listViewSearchResult.setAdapter(discountListAdapter);
        listViewSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiscountAbstractInfo info = discountAbstractInfos.get(position);
                String title = info.getTitle();
                Intent intent  = new Intent(SearchResultActivity.this, DiscountInfoActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });


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

    private void queryServer(String keyword)
    {

    }
}
