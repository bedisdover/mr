package cn.edu.nju.bedisdover.maptest.vo;

/**
 * 屏幕位置。
 * 返回值是一个0-1的小数，表示坐标占在屏幕的分位点数
 *
 * Created by alpaca on 16-10-6.
 */
public class ScreenLocation {

    public ScreenLocation(float px, float py) {
        setPx(px);
        setPy(py);
    }

    public float getPx() {
        return px;
    }

    public void setPx(float px) {
        this.px = px;
    }

    public float getPy() {
        return py;
    }

    public void setPy(float py) {
        this.py = py;
    }

    private float px, py;
}
