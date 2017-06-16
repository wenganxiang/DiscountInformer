/**
 * Created by Weng Anxiang on 2017/6/13.
 */
package com.weng.discountinformer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.weng.discountinformer.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> searchRecordList = new ArrayList<>();//把搜索记录存到这里将导致程序崩溃

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("SearchActivity", "Destroyed");
    }

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
            }
        });

        SharedPreferences prefs = getSharedPreferences("key_word_history", 0);
        String savedHistoryKeywords = prefs.getString("KeywordHistory", "");
        final String keywordsArray[] = savedHistoryKeywords.split(",");
        for (int i = 0; i < keywordsArray.length; i++)
        {
            searchRecordList.add(keywordsArray[i]);
        }
        adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, searchRecordList);//将keywordsArray换成了searchRecordList
        listView.setAdapter(adapter);
        //listView.setFooterDividersEnabled(true);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    //隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);

                    String keyword = editText.getText().toString();
                    if (keyword.trim().equals(""))
                    {
                        Toast.makeText(SearchActivity.this, "搜索词不能为空", Toast.LENGTH_SHORT).show();
                        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                        return false;
                    }
                    LogUtil.d("keyword", keyword);

                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchWord", keyword);
                    startActivity(intent);
                    LogUtil.d("Start Activity", "SearchResultActivity");
                    finish();
                    saveSearchWord(keyword);
                    LogUtil.d("keyword", "saved");
                }
                return false;
            }
        });
        /*
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    String keyword = editText.getText().toString();
                    if (keyword.trim().equals(""))
                    {
                        Toast.makeText(SearchActivity.this, "搜索词不能为空", Toast.LENGTH_SHORT).show();
                        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                        return false;
                    }
                    LogUtil.d("keyword", keyword);

                    saveSearchWord(keyword);
                    LogUtil.d("keyword", "saved");
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchWord", keyword);
                    startActivity(intent);
                    LogUtil.d("Start Activity", "SearchResultActivity");
                    finish();
                }
                return false;
            }
        });
        */
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSearch();
            }
        });
        buttonClearSearchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空listview的内容
                LogUtil.d("listView", "removed");
              //  AlertDialog.Builder dialog = new AlertDialog.Builder(SearchActivity.this);
               // dialog.setMessage("确定要删除历史搜索吗？");
               // dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                  //  @Override
                 //   public void onClick(DialogInterface dialog, int which) {
                  //
                  //  }
               // });
               // dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                  //  @Override
                  //  public void onClick(DialogInterface dialog, int which) {
                   //     return;
                  //  }
              //  });
              //  dialog.show();
                clearSearchHistory();

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

    private void saveSearchWord(String keyWord)
    {
        SharedPreferences pres = getSharedPreferences("key_word_history", 0);
        String oldText = pres.getString("KeywordHistory","");
        String[] hisArrays = oldText.split(",");
        for (int i=0; i<hisArrays.length; i++ )
        {
            if (keyWord.trim().equals(hisArrays[i]))
            {
                return;
            }
        }
        searchRecordList.add(keyWord.trim());
        SharedPreferences.Editor editor = getSharedPreferences("key_word_history", 0).edit();
        StringBuilder stringBuilder = new StringBuilder(oldText);
        stringBuilder.append(keyWord + ",");
        editor.putString("KeywordHistory", stringBuilder.toString());
        editor.apply();
    }

    private void startSearch()
    {
        String keyword = editText.getText().toString();
        if (keyword.trim().equals(""))
        {
            Toast.makeText(SearchActivity.this, "搜索词不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        LogUtil.d("keyword", keyword);

        saveSearchWord(keyword);
        LogUtil.d("keyword", "saved");
        Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
        intent.putExtra("searchWord", keyword);
        startActivity(intent);
        LogUtil.d("Start Activity", "SearchResultActivity");
        finish();
    }

    //清空搜索历史
    private void clearSearchHistory()
    {
        LogUtil.d("Enter", "clearSearchHistory()");
        SharedPreferences.Editor editor = getSharedPreferences("key_word_history", 0).edit();
        editor.putString("KeywordHistory", "");
        editor.apply();
        searchRecordList.clear();
        adapter.notifyDataSetChanged();
    }


}
