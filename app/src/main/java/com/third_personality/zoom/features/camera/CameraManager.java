package com.third_personality.zoom.features.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

public class CameraManager {
    private static CameraManager cameraManager;

    private CameraManager() {
    }

    public static CameraManager getCameraManager() {
        if (cameraManager == null) {
            cameraManager = new CameraManager();
        }
        return cameraManager;
    }

    public boolean checkCameraUsable(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    public Camera getCamera() { // 카메라를 안드로이드에 보내서 받아옴.
        Camera camera = null;

        try {
            camera = Camera.open(); //내부에 카메라를 요청하는 과정.
            Camera.Parameters cameraParameters = camera.getParameters();
            if (cameraParameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                cameraParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                camera.setParameters(cameraParameters);
            }
        } catch (Exception ex) {
            Log.e("CamerManager", ex.toString());
            System.exit(1);
        }
        return camera;
    }
}