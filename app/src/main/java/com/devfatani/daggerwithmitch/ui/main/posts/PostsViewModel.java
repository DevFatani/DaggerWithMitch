package com.devfatani.daggerwithmitch.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.devfatani.daggerwithmitch.SessionManager;
import com.devfatani.daggerwithmitch.models.Post;
import com.devfatani.daggerwithmitch.network.main.MainApi;
import com.devfatani.daggerwithmitch.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";

    private final SessionManager sessionManager;
    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;

        Log.d(TAG, "PostsViewModel: viewmodel is working ....");
    }


    public LiveData<Resource<List<Post>>> observePosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>) null));
            LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(mainApi.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId()).onErrorReturn(new Function<Throwable, List<Post>>() {
                @Override
                public List<Post> apply(Throwable throwable) throws Exception {
                    Log.d(TAG, "apply: ", throwable);
                    Post post = new Post();
                    post.setId(-1);
                    ArrayList<Post> posts = new ArrayList<>();
                    posts.add(post);
                    return posts;
                }
            }).map((Function<List<Post>, Resource<List<Post>>>) posts -> {
                if (posts.size() > 0) {
                    if (posts.get(0).getId() == -1) {
                        return Resource.error("Somthing went wrong", null);
                    }
                }
                return Resource.success(posts);
            }).subscribeOn(Schedulers.io()));
            posts.addSource(source, listResource -> {
                posts.setValue(listResource);
                posts.removeSource(source);
            });
        }

        return posts;
    }
}
