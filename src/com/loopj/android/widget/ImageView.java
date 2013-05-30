package com.loopj.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

public class ImageView extends android.widget.ImageView
{
    private static final String TAG = "ImageView";

    public ImageView(Context context) {
        super(context);
    }

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setImageURL(String url) {
        loadImageUrl(url);
    }

    public void setImageURLAndPlaceholder(String url, Bitmap placeholder) {
        this.setImageBitmap(placeholder);
        loadImageUrl(url);
    }

    public void setImageURLAndPlaceholder(String url, int placeholderId) {
        this.setImageResource(placeholderId);
        loadImageUrl(url);
    }

    public void setImageURLAndPlaceholder(String url, Drawable placeholder) {
        this.setImageDrawable(placeholder);
        loadImageUrl(url);
    }

    private void loadImageUrl(String url)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        String[] allowedContentTypes = new String[] { "image/png", "image/jpeg", "image/jpg", "image/gif" };

        client.get(url, new BinaryHttpResponseHandler(allowedContentTypes) {

            @Override
            public void onSuccess(byte[] fileData) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
                ImageView.this.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "Load image failed!");
            }
        });
    }
}
