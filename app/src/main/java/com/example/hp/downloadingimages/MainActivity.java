package com.example.hp.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    boolean download_clicked=true;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=(ImageView)findViewById(R.id.imageView);
        button=(Button)findViewById(R.id.button);
    }

    public void download_image_function(View view)
    {
        DownloadImage downloadImage=new DownloadImage();
        if(download_clicked==true)
        {
            try
            {
                Bitmap Image = downloadImage.execute("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQtQPKr1fVY1b9GbQD6sD5xCZQmqQjFk4qq6-rj_e8jkC6HTVf7").get();
                imageView.setImageBitmap(Image);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            download_clicked=false;
            button.setText("Remove");
        }
        else if(download_clicked==false)
        {
            try
            {
                Bitmap Image=downloadImage.execute("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTKu9iai4aYT1VnchuMy-ftCbiuAI1l_z3VXmThuuOlXbH-y60E").get();
                imageView.setImageBitmap(Image);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            download_clicked=true;
            button.setText("Download");
        }
    }
    public class DownloadImage extends AsyncTask<String,Void, Bitmap>
    {
        URL url;
        @Override
        protected Bitmap doInBackground(String... urls) {
            try
            {
                url=new URL(urls[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();

                InputStream in=connection.getInputStream();//store the stream from browser
                Bitmap bitmap=BitmapFactory.decodeStream(in);
                return bitmap;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
