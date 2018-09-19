package com.example.week3bu.utils;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface RequestCallBack {
    void failure(Call call, IOException e);
    void onResponse(Call call, Response response);
}
