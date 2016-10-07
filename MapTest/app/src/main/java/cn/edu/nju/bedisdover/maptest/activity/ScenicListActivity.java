package cn.edu.nju.bedisdover.maptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.listener.TabItemListener;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;

/**
 * Created by song on 16-10-7.
 * <p>
 * 景点列表
 */
public class ScenicListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sceniclist);
    }

    private void initTab() {
        PagerBottomTabLayout bottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab_scenic);

        Controller bottomTab = bottomTabLayout.builder()
                .addTabItem(android.R.drawable.ic_menu_camera, "相机")
                .addTabItem(android.R.drawable.ic_menu_compass, "位置")
                .addTabItem(android.R.drawable.ic_menu_search, "搜索")
                .addTabItem(android.R.drawable.ic_menu_help, "帮助")
                .build();

        bottomTab.addTabItemClickListener(new TabItemListener(this));
    }
}
