package com.devfatani.daggerwithmitch.network.main;

import com.devfatani.daggerwithmitch.models.Post;
import com.devfatani.daggerwithmitch.models.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(@Query("userId") int id);
}
