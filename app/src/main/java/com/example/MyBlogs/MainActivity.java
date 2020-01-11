package com.example.MyBlogs;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.MyBlogs.Blogs_data.PostList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcview;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcview=findViewById(R.id.recyclerview);
        rcview.setLayoutManager(new LinearLayoutManager(this));


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
        NotificationChannel channel=new NotificationChannel("mynotification","pushup notification",
                NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager=getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }

        //Firebase Topic Subsciption method
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = ("welcome to Alerty Notify");
                        if (!task.isSuccessful()) {
                            msg =("failed");
                        }

                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        getdata();

    }

    //function for getting data from blog
    public void getdata(){

        Call<PostList> postlist=Blogs_Post.get_servieses().getpostlist();
        postlist.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list=response.body();
                rcview.setAdapter(new Post_adapter(MainActivity.this,list.getItems()));

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error occur check internet connection first", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
