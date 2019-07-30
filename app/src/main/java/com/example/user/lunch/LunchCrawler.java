package com.example.user.lunch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class LunchCrawler {
    public ArrayList<MenuItem> getLunchList(final String url){
        try{
            final ArrayList<MenuItem> list = new ArrayList<MenuItem>();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Document doc = null;
                    try {
                        doc = Jsoup.connect(url).get();
                        Element test = doc.selectFirst("#morning > div.objContent1 > div.menuName > span");
                        String[] temp = {"오늘은 급식정보가 없습니다."};
                        try{
                            temp = test.text().split("[\\.]");
                        } catch(NullPointerException e1){}


                        for(String item : temp){
                            list.add(new MenuItem(item));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
            return list;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
