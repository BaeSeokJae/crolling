package com.example.crolling;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Activity_MelonChart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    private GridLayoutManager mGridLayoutManager;
    private ArrayList<ChartDTO> listData = new ArrayList<>();
    String melon_chart_url = "https://www.wizwid.com/CSW/handler/wizwid/kr/BrandCatalog-Start?BrandID=007110&RootCategoryID=&UpCategoryID=&ThirdCategoryID=&OrderType=&MaxRowNum=4258&PageNO=1&RPrice=&CouponYn=&SaleYn=&Delivery1=&Delivery2=#brand-category";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        int numberOfColumns = 3; // 한줄에 3개의 컬럼을 추가합니다.
        mGridLayoutManager = new GridLayoutManager(Activity_MelonChart.this, numberOfColumns);
        recyclerView.setLayoutManager(mGridLayoutManager);

        adapter = new RecyclerAdapter(Activity_MelonChart.this, listData);
        recyclerView.setAdapter(adapter);

        getData();

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int pos) {

                Intent intent = new Intent(Activity_MelonChart.this, ProductSiteActivity.class);
                String brandText = listData.get(pos).getTitle();
                String brandImage = listData.get(pos).getImageUrl();
                String brandLink = listData.get(pos).getLink();
                intent.putExtra("img", brandImage);
                intent.putExtra("uri", brandText);
                intent.putExtra("link", brandLink);
                intent.putExtra("Key", pos);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        ProductJsoup jsoupAsyncTask = new ProductJsoup();
        jsoupAsyncTask.execute();
    }

    private class ProductJsoup extends AsyncTask<Void, Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> listLink = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(melon_chart_url).get();
                final Elements rank_list1 = doc.select("ul.thumbCatalog.clearfix dl.goodsInfo dd.goods h4");
                final Elements image_list1 = doc.select("ul.thumbCatalog.clearfix a.thumbnails img.thumb");
                final Elements link_list1 = doc.select("ul.thumbCatalog.clearfix dl.goodsInfo dd.goods h4 a");

                Handler handler = new Handler(Looper.getMainLooper()); // 객체생성
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //순위정보
                        for (Element element : rank_list1) {
                            listTitle.add(element.text());
                        }
                        // 이미지정보
                        for (Element element : image_list1) {
                            listUrl.add(element.attr("src"));
                        }
                        for (Element element : link_list1) {
                            listLink.add(element.attr("href"));
                        }
                        for (int i = 0; i < 430; i++) {
                            ChartDTO data = new ChartDTO();
                            data.setTitle(listTitle.get(i));
                            data.setImageUrl(listUrl.get(i));
                            data.setLink(listLink.get(i));

                            listData.add(data);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            if(Activity_MelonChart.this != null){
//                recyclerView.setAdapter(adapter);
//
//                adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View v, int pos) {
//
//                    }
//
////                    @Override
////                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        String clickedUrl = data.get(pos).getPageUrl();
//
//                        // 브라우져 열기
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(clickedUrl));
//                        startActivity(intent);
//
//                        // 웹뷰 열기
//                        //Intent intent = new Intent(MainActivity.this, Webview.class);
//                        //intent.putExtra("pageUrl", clickedUrl);
//                        //startActivity(intent);
//
//                    }
//                });
//            }
//
//            mProgressDialog.dismiss();
//        }
    //ㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹ234243243
}