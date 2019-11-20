package in.technicalkeeda.downloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    //https://res.cloudinary.com/teepublic/image/private/s--OP_QWiNa--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1537028088/production/designs/3159840_0.jpg
    ImageView imageView;
    public  void download(View view){

        imagedownloader imagedownload= new imagedownloader();
        Bitmap myimage;
        try {
            myimage=imagedownload.execute("https://res.cloudinary.com/teepublic/image/private/s--OP_QWiNa--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1537028088/production/designs/3159840_0.jpg").get();
            imageView.setImageBitmap(myimage);
         } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("msg","button tapped");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         imageView=findViewById(R.id.imageView);

    }
    public  class imagedownloader extends AsyncTask<String,Void, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... strings) {

            URL url= null;
            try {
                url = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpsURLConnection connection=(HttpsURLConnection)url.openConnection();
                connection.connect();
                InputStream ip= connection.getInputStream();
                Bitmap mybitmap= BitmapFactory.decodeStream(ip);
                return  mybitmap;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
