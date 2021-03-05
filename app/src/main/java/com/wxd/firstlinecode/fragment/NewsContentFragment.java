package com.wxd.firstlinecode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxd.firstlinecode.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_content_fragment,container,false);
        return view;
    }

    public void refresh(String newsTitle,String newsContent){
        View visibility = view.findViewById(R.id.visibility_layout);
        visibility.setVisibility(View.VISIBLE);
        TextView newsTitleText = view.findViewById(R.id.news_title);
        TextView newsContentText = view.findViewById(R.id.news_content);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
    }
}
