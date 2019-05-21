package com.autosigninwxq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

//import com.autosigninwxq.HistorySignAdapter;
//import com.autosigninwxq.HistorySignAppModel;

public class HistorySignActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<HistorySignAppModel> mDataList = new ArrayList<HistorySignAppModel>();

    private HistorySignAdapter mAdapter;
    private List<HistorySignAppModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_main);
        initView();
    }

    private void initView() {
        mDataList = getData();
        mAdapter = new HistorySignAdapter(this, mDataList, null);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

    }

    //从数据库中获取签到信息并按照时间分别依次添加进入list中


    private List<HistorySignAppModel> getData() {
        for (int i = 0; i < 3; i++) {
            HistorySignAppModel bean = new HistorySignAppModel();
            bean.setTimeend("2014-12-12 12:45:60");
            bean.setTime("2016-02-02");
            bean.setAppname("微信");
            mDataList.add(bean);

        }

        for (int i = 0; i < 3; i++) {
            HistorySignAppModel bean = new HistorySignAppModel();
            bean.setTimeend("2016-02-06 12:45:60");
            bean.setTime("2016-02-06");
            bean.setAppname("支付宝");
            mDataList.add(0, bean);
        }
        for (int i = 0; i < 3; i++) {
            HistorySignAppModel bean = new HistorySignAppModel();
            bean.setTimeend("2016-02-06 12:45:60");
            bean.setTime("2016-02-06");
            bean.setAppname("唐人支付");
            mDataList.add(0, bean);
        }
        for (int i = 0; i < 3; i++) {
            HistorySignAppModel bean = new HistorySignAppModel();
            bean.setTimeend("2016-02-08 12:45:60");
            bean.setTime("2016-02-08");
            bean.setAppname("QQ支付");
            mDataList.add(0, bean);
        }

        return mDataList;
    }
}