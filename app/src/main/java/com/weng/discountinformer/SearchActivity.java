/**
 * Created by Weng Anxiang on 2017/6/13.
 */
package com.weng.discountinformer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private Button buttonBack;
    private EditText editText;
    private Button buttonSearch;
    private ListView listView;
    private Button buttonClearSearchHistory;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> searchRecordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        buttonBack = (Button) findViewById(R.id.button_search_back);
        editText = (EditText) findViewById(R.id.edit_text_search);
        buttonSearch = (Button) findViewById(R.id.button_search_search);
        listView = (ListView) findViewById(R.id.search_list_view);
        buttonClearSearchHistory = (Button) findViewById(R.id.button_clear_history);
        adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, searchRecordList);
        listView.setAdapter(adapter);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = editText.getText().toString();
                searchRecordList.add(keyWord);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra("searchWord", keyWord);
                startActivity(intent);
                finish();
            }
        });
        buttonClearSearchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空listview
                listView.removeAllViews();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyWord = searchRecordList.get(position);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra("searchWord", keyWord);
                startActivity(intent);
                finish();
            }
        });
    }
}
