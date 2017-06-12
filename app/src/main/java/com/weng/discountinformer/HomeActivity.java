package com.weng.discountinformer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * Created by Weng Anxiang on 2017/3/11.
 */
public class HomeActivity extends AppCompatActivity {
    private EditText editText;
    private Button search_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        editText = (EditText) findViewById(R.id.edit_text_search) ;
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
    }
}
