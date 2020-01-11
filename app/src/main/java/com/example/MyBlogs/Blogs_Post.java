package com.example.MyBlogs;
import com.example.MyBlogs.Blogs_data.PostList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Blogs_Post {

    private static final String key="AIzaSyDwTmNAdpG3MJsriecLjhgBaMIPkdBeqwI";
    private static final String url="https://www.googleapis.com/blogger/v3/blogs/2258234842541055960/posts/";

    public static Post_servieses post_servieses=null;

    public static Post_servieses get_servieses(){

        if (post_servieses==null){

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            post_servieses=retrofit.create(Post_servieses.class);

        }

        return post_servieses;
    }



    public interface Post_servieses{

        @GET("?key="+key)
        Call<PostList> getpostlist();
    }
}
