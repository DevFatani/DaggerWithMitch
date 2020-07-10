package com.devfatani.daggerwithmitch.ui.main.posts;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devfatani.daggerwithmitch.R;
import com.devfatani.daggerwithmitch.util.VerticalSpacingItemDecoration;
import com.devfatani.daggerwithmitch.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {
    private static final String TAG = "PostsFragment";

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    PostsRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.recyclerView = view.findViewById(R.id.recycler_view);
        this.viewModel = new ViewModelProvider(this, providerFactory).get(PostsViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observePosts().removeObservers(this);
        viewModel.observePosts().observe(getViewLifecycleOwner(), listResource -> {
            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING:
                        Log.d(TAG, "subscribeObservers: loading ...");
                        break;
                    case SUCCESS:
                        Log.i(TAG, "subscribeObservers: got posts");
                        adapter.setPosts(listResource.data);
                        break;
                    case ERROR:
                        Log.d(TAG, "subscribeObservers: ERROR... " + listResource.message);
                        break;
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new VerticalSpacingItemDecoration(15));
        recyclerView.setAdapter(adapter);
    }
}