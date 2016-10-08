package cn.edu.nju.bedisdover.maptest.listener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.edu.nju.bedisdover.maptest.R;

/**
 * Created by song on 16-10-8.
 * <p>
 * ‘去这里’点击监听
 */
public class ContentAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private List<Map<String, ?>> mListItem;

    public ContentAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public ContentAdapter(Context context, List<Map<String, ?>> mListItem) {
        this(context);

        this.mListItem = mListItem;
    }

    @Override
    public int getCount() {
        return mListItem.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.fragment_scenic_list_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.list_scenic_icon);
            holder.name  = (TextView) convertView.findViewById(R.id.list_scenic_name);
            holder.go  = (ImageView) convertView.findViewById(R.id.list_scenic_go);

//            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.icon.setBackgroundResource((Integer) mListItem.get(position).get("icon"));
        holder.name.setText((String) mListItem.get(position).get("name"));
        holder.go.setBackgroundResource((Integer) mListItem.get(position).get("go"));

        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(123);
            }
        });
        return convertView;
    }
}

class ViewHolder {
    ImageView icon;
    TextView name;
    ImageView go;
}

