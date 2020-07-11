package com.nihar.learning.videoservice.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class testMain {
    public static void main(String[] args) {
        List<Video> myVideoList = new ArrayList<>();
        Video video = new Video();
        video.setId("nihar");
        myVideoList.add(video);
        Video video1 = new Video();
        video1.setId("niahr1");
        myVideoList.add(video1);
        long x = myVideoList.stream().filter(e -> e.getId().equals("nihar")).count();
        System.out.println(x);
    }
}
