package cn.edu.nju.bedisdover.maptest.util;

import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import cn.edu.nju.bedisdover.maptest.model.Scenic;

/**
 * Created by song on 16-10-7.
 * <p>
 * 景点工具类,获取景点列表
 */
public class ScenicUtil {

    private static List<Scenic> scenicList;

    static {
        scenicList = new ArrayList<>();
    }

    private ScenicUtil() {
    }

    /**
     * 获取景点列表
     */
    public static List<Scenic> getScenicList(View view) {
        try {
            InputStream inputStream = view.getResources().getAssets().open("data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                if (tokenizer.hasMoreTokens()) {
                    String name = tokenizer.nextToken();
                    double longitude = Double.parseDouble(tokenizer.nextToken());
                    double latitude = Double.parseDouble(tokenizer.nextToken());
                    String description = tokenizer.nextToken();

                    Scenic scenic = new Scenic(name, longitude, latitude, description);
                    scenicList.add(scenic);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scenicList;
    }

    public static Scenic getScenicByName(String name) {
        for (Scenic scenic : scenicList) {
            if (scenic.getName().equals(name)) {
                return scenic;
            }
        }

        return null;
    }

    public static void setScenicList(List<Scenic> scenicList) {
        ScenicUtil.scenicList = scenicList;
    }
}
