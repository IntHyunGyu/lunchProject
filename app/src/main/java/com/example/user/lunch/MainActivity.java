package com.example.user.lunch;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private String htmlPageUrl;
    private DatePickerDialog dialog;
    private Adapter adapter;
    private ArrayList<MenuItem> data;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        this.context = this;
        final LunchCrawler lunch = new LunchCrawler();

        data = new ArrayList<MenuItem>();
        recyclerView = findViewById(R.id.list_view);
        adapter = new Adapter(this, data);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfDay, int dayOfMonth) {
                monthOfDay += 1;
                String date = "" + year + (monthOfDay < 10 ? "0" + monthOfDay : monthOfDay) + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
                htmlPageUrl =  "http://y-y.hs.kr/lunch.view?date=" + date;
                System.out.println(htmlPageUrl);
                data.clear();
                data.addAll(lunch.getLunchList(htmlPageUrl));
                adapter.notifyDataSetChanged();
            }
        }, 2019, 05, 30);

        Button dateBtn = findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

}
