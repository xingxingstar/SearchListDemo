package com.example.zhuwojia.searchlistdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhuwojia.searchlistdemo.R;
import com.example.zhuwojia.searchlistdemo.adapter.SearchListAdapter;
import com.example.zhuwojia.searchlistdemo.constant.Cities;
import com.example.zhuwojia.searchlistdemo.inter.StringCallBack;
import com.example.zhuwojia.searchlistdemo.utils.ComonUtis;
import com.example.zhuwojia.searchlistdemo.utils.PinYinUtils;

import java.util.ArrayList;

public class SearchListActivity extends AppCompatActivity {

    ImageView iv_back;
    EditText search_city;
    RecyclerView recy_city_list;
    private SearchListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        initView();
        recy_city_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchListAdapter(this);
        recy_city_list.setAdapter(adapter);
        recy_city_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        setListener();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        search_city = (EditText) findViewById(R.id.search_city);
        recy_city_list = (RecyclerView) findViewById(R.id.recy_city_list);
    }


    private void setListener() {
        adapter.setCallBack(new StringCallBack() {
            @Override
            public void typeChangeListener(String value) {
                Toast.makeText(SearchListActivity.this, value, Toast.LENGTH_SHORT).show();
            }
        });
        search_city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
//                    将大写字母转换为小写字母
                    String inputString = ComonUtis.exChange(s.toString());
//                    将输入的汉字转换为拼音
                    String inputPinYin = PinYinUtils.getPingYin(inputString);
                    recy_city_list.setVisibility(View.VISIBLE);
                    ArrayList list = new ArrayList();
                    for (int i = 0; i < Cities.mCitiesStrings.length; i++) {
                        String city = Cities.mCitiesStrings[i];
                        String pingYin = PinYinUtils.getPingYin(city);
                        boolean contains = pingYin.contains(inputPinYin);
                        if (contains) {
                            list.add(city);
                        }
                    }
                    if (list.size() > 0) {
                        adapter.setList(list);
                    } else {
                        adapter.setList(null);
                    }
                    adapter.notifyDataSetChanged();
                } else {
//                    无数据
                    recy_city_list.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
