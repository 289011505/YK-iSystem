package com.yksys.isystem.service.system;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.URL;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-13 14:51
 **/
public class JavaTest {
    public static void main(String[] args) throws IOException {
        // 通过jsoup将对应url转为document
        Document doc = Jsoup.parse(new URL("http://s.weibo.com/top/summary?cate=realtimehot"), 10000);
//        System.out.println(doc);
        //获取script标签对应的Element list
        Elements elements = doc.select("div[class=data] tbody tr");
        for (Element element : elements) {
            System.out.println(element);
//            String text = element.select("td[class=td-02] a").text();
//            String span = element.select("td[class=td-02] span").text();
//            String href = element.select("td[class=td-02] a").attr("href");
//            String href = element.select("td[class=td-03] i").text();

//            String type = element.select("")
        }
    }
}