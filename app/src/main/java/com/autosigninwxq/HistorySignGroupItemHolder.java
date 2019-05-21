package com.autosigninwxq;

import android.content.Context;
import android.view.View;
import android.widget.TextView;



public class HistorySignGroupItemHolder extends HistorySignNomalItemHolder {

    public TextView group_item_time;

    public HistorySignGroupItemHolder(Context context, View itemView, HistorySignAppItemClickListener listener,
                           HistorySignAppItemLongClickListener longClickListener) {
        super(context, itemView, listener, longClickListener);
        group_item_time = (TextView) itemView.findViewById(R.id.group_item_time);
    }
}