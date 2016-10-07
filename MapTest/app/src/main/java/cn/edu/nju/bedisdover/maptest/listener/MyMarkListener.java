package cn.edu.nju.bedisdover.maptest.listener;

import android.content.Context;
import android.widget.Toast;

import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.model.Marker;

/**
 * Created by song on 16-10-7.
 * 
 * 景点监听事件
 */
public class MyMarkListener implements OnMarkerClickListener {

    private Context context;

    public MyMarkListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(context, marker.getTitle(), Toast.LENGTH_SHORT).show();

        return false;
    }
}
