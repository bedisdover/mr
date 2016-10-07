package cn.edu.nju.bedisdover.maptest.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.amap.api.maps.AMap;

import cn.edu.nju.bedisdover.maptest.R;

/**
 * Created by song on 16-10-6.
 *
 * fragment map
 */
public class BaseMapFragmentActivity extends FragmentActivity {

    private AMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_map);
    }
}
