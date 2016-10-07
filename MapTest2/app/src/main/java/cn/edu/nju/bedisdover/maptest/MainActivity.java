package cn.edu.nju.bedisdover.maptest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.bedisdover.maptest.fragment.MapFragment;
import cn.edu.nju.bedisdover.maptest.fragment.ScenicListFragment;
import cn.edu.nju.bedisdover.maptest.fragment.SearchFragment;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList;

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
        Controller controller = pagerBottomTabLayout.builder()
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

        fragmentList.add(0, new ScenicListFragment());
        fragmentList.add(1, new MapFragment());
        fragmentList.add(2, new ScenicListFragment());
        fragmentList.add(3, new SearchFragment());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_main_content, fragmentList.get(0));
        transaction.commit();
    }

    OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_main_content, fragmentList.get(index));
            transaction.commit();
        }

        @Override
        public void onRepeatClick(int index, Object tag) {
        }
    };
}
