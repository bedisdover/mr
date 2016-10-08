package cn.edu.nju.bedisdover.maptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.bedisdover.maptest.fragment.MapFragment;
import cn.edu.nju.bedisdover.maptest.fragment.NavigatorFragment;
import cn.edu.nju.bedisdover.maptest.fragment.ScenicListFragment;
import cn.edu.nju.bedisdover.maptest.fragment.SearchFragment;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList;

    /**
     * 导航栏控制器
     */
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomBar();
        initFragment();
    }

    private void initBottomBar() {
        int color = 0xFF00796B;

        PagerBottomTabLayout pagerBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);

        //用TabItemBuilder构建一个导航按钮
        TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
                .setDefaultIcon(android.R.drawable.ic_menu_view)
                .setText("探索")
                .setSelectedColor(color)
                .build();

        //构建导航栏,得到Controller进行后续控制
        controller = pagerBottomTabLayout.builder()
                .addTabItem(tabItemBuilder)
                .addTabItem(android.R.drawable.ic_menu_compass, "位置", color)
                .addTabItem(android.R.drawable.ic_menu_search, "搜索", color)
                .addTabItem(android.R.drawable.ic_menu_help, "帮助", color)
                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();

        controller.addTabItemClickListener(listener);
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();

        fragmentList.add(0, new NavigatorFragment());
        fragmentList.add(1, new MapFragment());
        fragmentList.add(2, new ScenicListFragment());
        fragmentList.add(3, new SearchFragment());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_main_content, fragmentList.get(0));
        transaction.commit();
    }

    private OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {
            // 去掉景点名称信息
            if (index == 1) {
                Intent intent = getIntent();

                if (intent.hasExtra("scenicName")) {
                    intent.removeExtra("scenicName");
                }
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_main_content, fragmentList.get(index));
            transaction.commit();
        }

        @Override
        public void onRepeatClick(int index, Object tag) {
        }
    };

    /**
     * 跳转到指定景点的地图位置
     */
    public void jumpToScenic(String scenicName) {
        Intent intent = getIntent();
        intent.putExtra("scenicName", scenicName);

        // 切换导航栏
        controller.setSelect(1);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_content, fragmentList.get(1));
        transaction.commit();
    }
}
