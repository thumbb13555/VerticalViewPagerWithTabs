package com.jetec.verticalviewpagerwithtabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PagerFragment extends Fragment {

    private int pagerId = 0;
    private String info = "";
    private static final String KEY_PAGE_ID = "key_page_id";
    private static final String KEY_INFO = "key_Info";

    /**設置初始化接口，並將指定要載入的資料存起來*/
    public static PagerFragment newInstance(String s, int pageId){
        /**以Bundle存放要被載入的資料，稍後再onCreate中取出*/
        Bundle args = new Bundle();
        args.putString(KEY_INFO,s);
        args.putInt(KEY_PAGE_ID,pageId);
        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**取出儲存的資料*/
        Bundle bundle = getArguments();
        pagerId = bundle.getInt(KEY_PAGE_ID);
        info = bundle.getString(KEY_INFO);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**找到View, 並設置裡面的UI要做的事*/
        View view = inflater.inflate(R.layout.page_item,container,false);
        TextView tvTag = view.findViewById(R.id.textView_PageTag);
        TextView tvInfo = view.findViewById(R.id.textView_Info);

        tvTag.setText("第"+(pagerId+1)+"頁");
        tvInfo.setText(info);

        return view;
    }
}
