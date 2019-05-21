package com.autosigninwxq;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HistorySignAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL_ITEM = 0;
    private static final int GROUP_ITEM = 1;

    private Context mContext;
    private List<HistorySignAppModel> mDataList;
    private LayoutInflater mLayoutInflater;

    private HistorySignAppItemClickListener listener;

    private HistorySignAppItemLongClickListener longClickListener;

    private LinearLayout linearLayout;


    public HistorySignAdapter(Context mContext, List<HistorySignAppModel> mDataList, LinearLayout linearLayout) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.linearLayout = linearLayout;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == NORMAL_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_view_item, parent, false);
            HistorySignNomalItemHolder holder = new HistorySignNomalItemHolder(mContext, itemView, listener, longClickListener);
            return holder;
        } else if (viewType == GROUP_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_view_list, parent, false);
            HistorySignGroupItemHolder holder = new HistorySignGroupItemHolder(mContext, itemView, listener, longClickListener);
            return holder;
        }
        return null;//
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        HistorySignAppModel entity = mDataList.get(position);

        if (null == entity)
            return;

        if (viewHolder instanceof HistorySignGroupItemHolder) {
            bindGroupItem(entity, (HistorySignGroupItemHolder) viewHolder);
        } else {
            HistorySignNomalItemHolder holder = (HistorySignNomalItemHolder) viewHolder;
            bindNormalItem(entity, holder.appname, holder.time, holder.today, holder.apppic);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("mDataList 的长度：", mDataList.size() + "");
//        if (mDataList.size() == 0)
//            linearLayout.setBackgroundResource(R.mipmap.null_record);
//        else
//            linearLayout.setBackgroundResource(0);
        return mDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        //第一个要显示时间
        if (position == 0)
            return GROUP_ITEM;

        String currentDate = mDataList.get(position).getTime();
        int prevIndex = position - 1;
        boolean isDifferent = !mDataList.get(prevIndex).getTime().equals(currentDate);
        return isDifferent ? GROUP_ITEM : NORMAL_ITEM;
    }

    void bindNormalItem(HistorySignAppModel entity, TextView appname,  TextView time, TextView today, ImageView apppic) {

        appname.setText(entity.getAppname());
        time.setText(entity.getTimeend());
        today.setText(entity.getTime());

        //依据entity.getAppname()获取appname并通过SharedPreferences获取appname对应的图片
        //apppic.setBackgroundResource(获取的图片);
//        Bitmap bitmap
//        entity.get
        //利用SharedPreferences获取appname对应的图片
        //暂时只用微信的图片替代所有的图片
        apppic.setBackgroundResource(R.mipmap.pay_icon_weixin);

//        if (entity.getPaytype().equals("微信支付")) {
//            imageType.setBackgroundResource(R.mipmap.pay_icon_weixin);
//        } else if (entity.getPaytype().equals("支付宝支付")) {
//            imageType.setBackgroundResource(R.mipmap.pay6_normal);
//        } else if (entity.getPaytype().equals("QQ支付")) {
//            imageType.setBackgroundResource(R.mipmap.pay_qq_default);
//        } else {
//            imageType.setBackgroundResource(R.mipmap.pay_shuffle);
//        }
    }

    void bindGroupItem(HistorySignAppModel entity, HistorySignGroupItemHolder holder) {
        bindNormalItem(entity, holder.appname, holder.time, holder.today, holder.apppic);
        holder.group_item_time.setText(entity.getTime());
    }

    public void setClickListener(HistorySignAppItemClickListener clickListener) {
        this.listener = clickListener;
    }

    public void setLongClickListener(HistorySignAppItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }


}