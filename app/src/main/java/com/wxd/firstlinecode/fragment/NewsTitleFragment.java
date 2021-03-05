package com.wxd.firstlinecode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxd.firstlinecode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_fragment, container, false);
        RecyclerView newsTitleRecyclerview = view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerview.setLayoutManager(manager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerview.setAdapter(adapter);
        return view;
    }

    private List<News> getNews() {
        List<News> news = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news1 = new News();
            news1.setTitle("This is News Title" + i);
            news1.setContent(getRandomLengthContent("this is news content" + i));
            news.add(news1);
        }
        return news;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> newsList;

        public NewsAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = newsList.get(viewHolder.getAdapterPosition());
                    if (isTwoPane) {
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getChildFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }

            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = newsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                newsTitleText = itemView.findViewById(R.id.news_title);
            }
        }
    }
}
