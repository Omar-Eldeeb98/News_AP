package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Detailed extends AppCompatActivity {

    private New  aNew ;
    private ImageView imageView;
   private TextView title , source , date , description;
  private   WebView webView;
  private ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detailed );

        aNew = new New(  );
        title = findViewById( R.id.title_text_view );
        source = findViewById( R.id.source_text_view );
        date = findViewById( R.id.date_text_view );
        description = findViewById( R.id.description_text_view );
        webView = findViewById( R.id.web_view );
        imageView = findViewById( R.id.new_image );

        loader = findViewById( R.id.webViewLoader );
        loader.setVisibility( View.VISIBLE );


        aNew = (New) getIntent().getSerializableExtra( "new" );
        String mtitle =aNew.getTitle();
        String msource = aNew.getSource();
        String mdate = aNew.getDate();
        String mdecription = aNew.getDescription();
        String mImage = aNew.getImageUrl();
        String webView_ = aNew.getWebView();

        title.setText( mtitle );
        source.setText( msource );
        date.setText(dateTime( mdate )  );
        description.setText( mdecription );
        Picasso.get().load( mImage ).fit().centerInside().into( imageView );



        webView.getSettings().setDomStorageEnabled( true );
        webView.getSettings().setJavaScriptEnabled( true );
        webView.getSettings().setLoadsImagesAutomatically( true );
        webView.setScrollBarStyle( View.SCROLLBARS_INSIDE_OVERLAY );
        webView.setWebViewClient( new WebViewClient() );
        webView.loadUrl( webView_ );
        if (webView.isShown())
        {

            loader.setVisibility( View.INVISIBLE );

        }



    }

    public String dateTime(String t)
    {
        PrettyTime prettyTime  =new PrettyTime();
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:" );
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format( date );

        }
        catch (Exception e)
        {
            e.printStackTrace( );

        }
        return time;
    }
}
