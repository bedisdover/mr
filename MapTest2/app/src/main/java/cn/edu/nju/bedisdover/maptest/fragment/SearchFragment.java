package cn.edu.nju.bedisdover.maptest.fragment;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.nju.bedisdover.maptest.R;

/**
 * Created by song on 16-10-7.
 * <p>
 * 测试输入框
 */
public class SearchFragment extends Fragment {

    private ImageView iv;
    private TextView text;
    private AnimatedVectorDrawable searchToBar;
    private AnimatedVectorDrawable barToSearch;
    private float offset;
    private Interpolator interp;
    private int duration;
    private boolean expanded = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        init(view);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(final View view) {
        iv = (ImageView) view.findViewById(R.id.search);
        text = (TextView) view.findViewById(R.id.text);
        searchToBar = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_search_to_bar);
        barToSearch = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_bar_to_search);
        interp = AnimationUtils.loadInterpolator(view.getContext(), android.R.interpolator.linear_out_slow_in);
        duration = getResources().getInteger(R.integer.duration_bar);

        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        offset = -71f * (int) getResources().getDisplayMetrics().scaledDensity;
        iv.setTranslationX(offset);

        view.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                animate(v);
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                animate(view);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animate(View view) {
        if (!expanded) {
            iv.setImageDrawable(searchToBar);
            searchToBar.start();
            iv.animate().translationX(0f).setDuration(duration).setInterpolator(interp);
            text.animate().alpha(1f).setStartDelay(duration - 100).setDuration(100).setInterpolator(interp);
        } else {
            iv.setImageDrawable(barToSearch);
            barToSearch.start();
            iv.animate().translationX(offset).setDuration(duration).setInterpolator(interp);
            text.setAlpha(0f);
        }
        expanded = !expanded;
    }
}
