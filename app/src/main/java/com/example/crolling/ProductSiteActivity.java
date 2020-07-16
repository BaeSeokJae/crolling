package com.example.crolling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductSiteActivity extends AppCompatActivity {

    WebView mWebView;
    int pos;
    int product;
    String siteLink;
    Button button;
    String img;
    String link;
    private ArrayList<ChartDTO> listData;
    private static final int send = 1;
    private ChartDTO chartDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_site);

        Intent intent = new Intent(this.getIntent());
        pos = intent.getIntExtra("Key", pos);
        siteLink = intent.getStringExtra("uri");
//        img = intent.getStringExtra("img");
        link = intent.getStringExtra("link");
//        TextView textview = findViewById(R.id.test);
//        textview.setText(siteLink);
//        ImageView imageView = findViewById(R.id.img);
//        Glide.with(imageView.getContext()).load(img).into(imageView);

        this.SiteListData();

        mWebView = (WebView) findViewById(R.id.product_webView);
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings mWebSettings = mWebView.getSettings();
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDisplayZoomControls(false);
        // 웹뷰 멀티 터치 가능하게 (줌기능)
        mWebSettings.setBuiltInZoomControls(true);   // 줌 아이콘 사용
        mWebSettings.setSupportZoom(true);

        // 웹뷰 사이즈에 맞게 화면 출력
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.loadUrl("https://www.wizwid.com/CSW/handler/wizwid/kr/" + link);

        button = findViewById(R.id.select1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ProductSiteActivity.this, SelectListActivity.class);
//
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shoe);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArray = stream.toByteArray();
//                intent.putExtra("image3", byteArray);
//                setResult(send, intent);
//                finish();
            }
        });
    }

    public void SiteListData() {
    }
}