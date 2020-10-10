package com.jetec.verticalviewpagerwithtabs;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class TabsAdapter extends RecyclerView.Adapter<TabsAdapter.ViewHolder> {
    OnTabClick onTabClick;
    private String[] strings;
    private RecyclerView recyclerView;
    private int currentPage = 0;

    /**建構子*/
    public TabsAdapter(String[] strings,RecyclerView recyclerView) {
        this.strings = strings;
        this.recyclerView = recyclerView;
    }
    /**提供一個接口給外部以綁定ViewPager的頁數及Tab顯示*/
    public void setCurrentPage(int position){
        if (currentPage >= 0){
            deSelect(currentPage);
        }
        /**從position中找到itemView*/
        View targetView = recyclerView.getLayoutManager().findViewByPosition(position);
        TextView tvTab = targetView.findViewById(R.id.textView_TabNumber);
        setTextViewSelected(tvTab);
        /**保證RecyclerViewAdapter中的即時頁碼與被滑到的位置相同*/
        currentPage = position;
    }
    /**使其他沒被滑到會點選到的頁面Tab回復原本的顏色*/
    private void deSelect(int position){
        if (recyclerView.getLayoutManager().findViewByPosition(position)!= null){
            View targetView = recyclerView.getLayoutManager().findViewByPosition(position);
            TextView tvTab = targetView.findViewById(R.id.textView_TabNumber);
            setTextViewUnSelected(tvTab);
        }
        currentPage = -1;
    }
    /**設置被選到時的UI變化*/
    private void setTextViewSelected(TextView tvTab){
        tvTab.setTextColor(Color.RED);
    }
    /**設置被未被選到時的UI變化*/
    private void setTextViewUnSelected(TextView tvTab){
        tvTab.setTextColor(Color.WHITE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTab;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTab = itemView.findViewById(R.id.textView_TabNumber);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabs_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvTab.setText(strings[position]);
        if (position == currentPage) setTextViewSelected(holder.tvTab);
        else setTextViewUnSelected(holder.tvTab);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick.onTabClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    interface OnTabClick{
        void onTabClick(int position);
    }
}
