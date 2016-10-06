package cn.edu.nju.mr.client.model;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by alpaca on 16-10-6.
 */
public class CameraAccessor {

    public CameraAccessor(Context context, SurfaceView target) {
        this.mContext = context;
        this.mTargetView = target;
    }

    public void startCamera() throws IOException {
        if(Camera.getNumberOfCameras() >= 2){
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        }else{
            camera = Camera.open(0);
        }

        Parameters parameters = camera.getParameters();
        parameters.setPreviewFpsRange(5, 10);
        camera.setParameters(parameters);
        camera.setPreviewDisplay(mTargetView.getHolder());
        camera.startPreview();
    }

    public void stopCamera() {
        camera.stopPreview();
        camera.release();
    }

    private SurfaceView mTargetView;
    private Camera camera;
    private Context mContext;
}
