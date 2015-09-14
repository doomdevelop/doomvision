package com.akozlowski.doomvision.util;

import com.akozlowski.doomvision.pojo.Assets;
import com.akozlowski.doomvision.pojo.Data;
import com.akozlowski.doomvision.pojo.Preview;
import com.akozlowski.doomvision.pojo.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by and on 14.09.15.
 */
public class DataCreator {
    public static  final String TEST_IMAGE_URL ="https://image.shutterstock.com/display_pic_with_logo/170467/143678257/stock-vector-nature-background-with-green-leafs-and-text-go-green-143678257.jpg";
    public static Response generateResponse(){
        Response response = new Response();
        response.setData(generateDataList());
        return response;
    }

    public static List<Data> generateDataList(){
        List<Data> list = new ArrayList<>();
        for(int i = 0; i<=10;i++){
            list.add(generateData());
        }
        return list;
    }
    public static Data generateData(){
        Data data = new Data();
        data.setAddedDate(new Date(System.currentTimeMillis()).toString());
        Assets assets = new Assets();
        Preview provPreview = new Preview();
        provPreview.setUrl(TEST_IMAGE_URL);
        assets.setPreview(provPreview);
        data.setAssets(assets);
        return data;
    }
}
