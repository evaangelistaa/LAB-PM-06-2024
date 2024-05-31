package com.example.coba6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnClickListener {
    private ApiService apiService;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
    private TextView connectionLost;
    private ProgressBar progressBar,progressBar1;
    private Button btn_load, btn_retry;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        progressBar1 = findViewById(R.id.progressBar2);
        btn_load = findViewById(R.id.btn_load);
        btn_retry = findViewById(R.id.retry);
        connectionLost = findViewById(R.id.connection);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiService = RetrofitClient.getClient().create(ApiService.class);

        btn_retry.setVisibility(View.GONE);
        connectionLost.setVisibility(View.GONE);

        userList = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        if (!isNetworkAvailable()) {
            btn_retry.setVisibility(View.VISIBLE);
            connectionLost.setVisibility(View.VISIBLE);
            progressBar1.setVisibility(View.GONE);
            btn_load.setVisibility(View.GONE);

            btn_retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isNetworkAvailable()) {
                        btn_retry.setVisibility(View.GONE);
                        connectionLost.setVisibility(View.GONE);
                        progressBar1.setVisibility(View.VISIBLE);
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar1.setVisibility(View.GONE);
                                        loadUserList(1);
                                        btn_load.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                        });
                        btn_load.setOnClickListener(view -> {
                            btn_load.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                            executor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.setVisibility(View.GONE);
                                            loadUserList(2);
                                        }
                                    });
                                }
                            });
                        });
                    } else {
                        progressBar1.setVisibility(View.VISIBLE);
                        btn_retry.setVisibility(View.GONE);
                        connectionLost.setVisibility(View.GONE);
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar1.setVisibility(View.GONE);
                                        btn_retry.setVisibility(View.VISIBLE);
                                        connectionLost.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
        if (isNetworkAvailable()) {
            btn_retry.setVisibility(View.GONE);
            connectionLost.setVisibility(View.GONE);
            progressBar1.setVisibility(View.VISIBLE);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar1.setVisibility(View.GONE);
                            loadUserList(1);
                            btn_load.setVisibility(View.VISIBLE);
                        }
                    });
                }
            });
            btn_load.setOnClickListener(v -> {
                btn_load.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                loadUserList(2);
                            }
                        });
                    }
                });
            });
        }
    }

    private void loadUserList(int page) {
        Call<UserResponse> call = apiService.getUsers(page);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body().getData();
                    userList.addAll(users);
                    if (userAdapter == null) {
                        userAdapter = new UserAdapter(userList, MainActivity.this);
                        recyclerView.setAdapter(userAdapter);
                    } else {
                        userAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load user list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public void onImageClick(int position) {
        User user = userList.get(position);
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}

