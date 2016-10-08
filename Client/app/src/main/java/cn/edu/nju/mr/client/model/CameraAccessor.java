package cn.edu.nju.mr.client.model;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import cn.edu.nju.mr.client.R;

import java.io.IOException;

/**
 * Created by alpaca on 16-10-6.
 */
public class CameraAccessor {

    public CameraAccessor(Context context, SurfaceView target) {
        this.mContext = context;
        this.mTargetView = target;
    }

    public void startCamera() throws IOException, NoAvailableCameraException {

        final SurfaceHolder sf = mTargetView.getHolder();
        sf.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        sf.setFixedSize(480, 800);
        sf.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                if (Camera.getNumberOfCameras() <= 0)
                    return;
                camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                Parameters parameters = camera.getParameters();
                parameters.setPreviewFpsRange(5, 10);
                parameters.setPreviewSize(640, 480);
                camera.setParameters(parameters);
                camera.startPreview();
                try {
                    camera.setPreviewDisplay(sf);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                camera.startPreview();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                if (camera != null) {
                    camera.stopPreview();
                    camera.release();
                }
            }
        });
    }

    public void stopCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
        }
    }

    private SurfaceView mTargetView;
    private Camera camera;
    private Context mContext;
}
