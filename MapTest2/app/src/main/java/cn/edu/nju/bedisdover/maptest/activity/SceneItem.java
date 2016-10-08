package cn.edu.nju.bedisdover.maptest.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.edu.nju.bedisdover.maptest.R;

/**
 * Created by alpaca on 16-10-8.
 */
public class SceneItem extends RelativeLayout {

    public SceneItem(Context context, String name, int distance) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.scene_item, null);
        initComponents();
        this.name = name;
        this.distance = distance;
    }

    private void initComponents() {
        txtName = (TextView) findViewById(R.id.txtName);
        txtDistance = (TextView) findViewById(R.id.txtDistance);
        txtName.setText(name);
        txtDistance.setText("距离" + distance + "m");
    }

    private String name;
    private int distance;
    private TextView txtName;
    private TextView txtDistance;
}
