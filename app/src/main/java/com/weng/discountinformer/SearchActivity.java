/**
 * Created by Weng Anxiang on 2017/6/13.
 */
package com.weng.discountinformer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.weng.discountinformer.util.LogUtil;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchActivity extends AppCompatActivity  {
    private Button buttonBack;
    private EditText editText;
    private CircleImageView clearEditText;
    private Button buttonSearch;
    private ListView listView;
    private Button buttonClearSearchHistory;
    private ArrayAdapter<String> adapter;
    String testString[] = {"a", "b", "c", "d", "e"};
    private ArrayList<String> searchRecordList;//把搜索记录存到这里将导致程序崩溃
    SharedPreferences savedKeyWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        buttonBack = (Button) findViewById(R.id.button_search_back);
        editText = (EditText) findViewById(R.id.edit_text_search);
        clearEditText = (CircleImageView) findViewById(R.id.clear_edit_text);
        buttonSearch = (Button) findViewById(R.id.button_search_search);
        listView = (ListView) findViewById(R.id.search_list_view);
        buttonClearSearchHistory = (Button) findViewById(R.id.button_clear_history);

        clearEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                //clearEditText.setVisibility(View.INVISIBLE);
            }
        });



        SharedPreferences prefs = getSharedPreferences("key_word_history", 0);
        String savedHistoryKeywords = prefs.getString("KeywordHistory", "");
        final String keywordsArray[] = savedHistoryKeywords.split(",");
        adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, keywordsArray);
        listView.setAdapter(adapter);

        //editText.clearFocus();  //隐藏键盘

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editText.getText().toString();
                if (keyword.equals(""))
                {
                    Toast.makeText(SearchActivity.this, "搜索词不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                LogUtil.d("keyword", keyword);

                saveSearchWord(keyword);
                LogUtil.d("keyword", "saved");
                //把keyWord保存到搜索记录中,但是不能保存到searchRecordList中，否则SearchRessultActivity打不开
               // searchRecordList.add(keyWord);
                //System.arraycopy(keyWord, 0, testString, testString.length, 1);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra("searchWord", keyword);
                startActivity(intent);
                LogUtil.d("Start Activity", "SearchResultActivity");
                finish();
            }
        });
        buttonClearSearchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空listview的内容
               // listView.removeAllViewsInLayout();
               // clearSearchHistory();
                listView.removeAllViewsInLayout();
                LogUtil.d("listView", "removeAllViewsInLayout");
                clearSearchHistory();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // String keyWord = searchRecordList.get(position);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                //intent.putExtra("searchWord", keyWord);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveSearchWord(String keyWord)
    {
        if (keyWord.equals("")) //关键字不为空
        {
            return;
        }
        SharedPreferences pres = getSharedPreferences("key_word_history", 0);
        String oldText = pres.getString("KeywordHistory","");
        String[] hisArrays = oldText.split(",");
        for (int i=0; i<hisArrays.length; i++ )
        {
            if (keyWord.equals(hisArrays[i]))
            {
                return;
            }
        }
        SharedPreferences.Editor editor = getSharedPreferences("key_word_history", 0).edit();
        StringBuilder stringBuilder = new StringBuilder(oldText);
        stringBuilder.append(keyWord + ",");
        editor.putString("KeywordHistory", stringBuilder.toString());
        editor.apply();
        adapter.notifyDataSetChanged();
    }

    //清空搜索历史
    private void clearSearchHistory()
    {
        LogUtil.d("Enter", "clearSearchHistory()");
        SharedPreferences.Editor editor = getSharedPreferences("key_word_history", 0).edit();
        editor.putString("KeywordHistory", "");
        editor.apply();

        adapter.notifyDataSetChanged();
        //listView.removeAllViewsInLayout();
       // Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
        //startActivity(intent);
       // finish();
        //onCreate(null);
    }


}
