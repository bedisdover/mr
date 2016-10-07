package cn.edu.nju.bedisdover.maptest.listener;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import cn.edu.nju.bedisdover.maptest.activity.MainActivity;
import cn.edu.nju.bedisdover.maptest.activity.ScenicListActivity;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

/**
 * Created by song on 16-10-7.
 *
 * 监听底部导航栏切换
 */
public class TabItemListener implements OnTabItemSelectListener {

    private Context context;

    public TabItemListener(Context context) {
        this.context = context;
    }

    @Override
    public void onSelected(int index, Object tag) {
        Intent intent = null;

        switch (index) {
            case 0:
                intent = new Intent(context, MainActivity.class);
                break;
            case 1:
                intent = new Intent(context, ScenicListActivity.class);
                break;
        }

        context.startActivity(intent);
    }

    @Override
    public void onRepeatClick(int index, Object tag) {

    }
}
