package com.autosigninwxq;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class HistorySignNomalItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView appname;

    public TextView time;//签到的具体的时间
    public TextView today;//年月日时间
    public ImageView apppic;//app图片


    private HistorySignAppItemClickListener listener;
    private HistorySignAppItemLongClickListener longClickListener;
    private Context context;

    public HistorySignNomalItemHolder(Context context, View itemView, HistorySignAppItemClickListener listener, HistorySignAppItemLongClickListener longClickListener) {
        super(itemView);
        this.context = context;
        this.listener = listener;
        this.longClickListener = longClickListener;
        time = (TextView) itemView.findViewById(R.id.time);
        appname = (TextView) itemView.findViewById(R.id.appname);
        today = (TextView) itemView.findViewById(R.id.today);
        apppic= (ImageView) itemView.findViewById(R.id.apppic);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            itemView.setBackgroundResource(R.drawable.recycler_bg);
            listener.setClickListtener(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longClickListener != null) {
            itemView.setBackgroundResource(R.drawable.recycler_bg);
            longClickListener.setItemLongClick(v, getAdapterPosition());
        }
        return true;
    }
}