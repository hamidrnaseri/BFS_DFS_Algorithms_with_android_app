package com.example.artificial_intelligence_dfs_bfs;

import android.view.View;
import android.widget.TextView;
import de.blox.graphview.ViewHolder;

class SimpleViewHolder extends ViewHolder {
    TextView textView;

    SimpleViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }
}