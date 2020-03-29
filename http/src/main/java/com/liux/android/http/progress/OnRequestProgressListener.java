package com.liux.android.http.progress;

import okhttp3.HttpUrl;

/**
 * 2018/2/27
 * By Liux
 * lx0758@qq.com
 */

public interface OnRequestProgressListener {

    void onRequestProgress(HttpUrl httpUrl, long transmittedLength, long totalLength, boolean completed);
}
