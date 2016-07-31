package djluzo.etpclot.com.watchme.dispositivos.Camera;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.hardware.Camera.PictureCallback;

import java.io.IOException;
import java.util.List;

import djluzo.etpclot.com.watchme.sockets.ClientThread;

/**
 * Clase que trabaja con el dispositivo de la cámara Android
 * y permite enviar imagenes en Streaming mediante sockets
 *
 * Created by djluzo on 12/6/15.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    public static boolean tomar_foto = true;
    private SurfaceHolder mHolder;
    private SurfaceView surfaceView;
    private PictureCallback jpegCallback,capturePhoto;
    private ClientThread client;
    private Context ctx;
    private Camera mCamera;

    public CameraPreview(Context context,  Camera camera, SurfaceView surfaceView1, SurfaceHolder mHolder) {
        super(context);
        this.ctx = context;
        this.surfaceView = surfaceView1;
        this.mHolder = mHolder;
        this.mCamera = camera;
        client = new ClientThread();
        new Thread(client).start();

        iniciar();
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.


    }
    public void iniciar()  {
        mHolder = surfaceView.getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        startCamara();
        jpegCallback = new PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                client.send_byte(data);
                refreshCamera();
            }
        };

    }

    private void refreshCamera() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(tomar_foto==true)
                {

                    try {
                        Thread.sleep(3000);
                        mCamera.takePicture(null, null, jpegCallback);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch( RuntimeException e)
                    {
                       e.printStackTrace();
                    }


                }
            }
        }).start();

        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {

            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (Exception e) {

        }
    }
    private void configure(Camera camera) {
        Camera.Parameters params = camera.getParameters();

        // Configure image format. RGB_565 is the most common format.
        List<Integer> formats = params.getSupportedPictureFormats();
        if (formats.contains(PixelFormat.RGB_565))
            params.setPictureFormat(PixelFormat.RGB_565);
        else
            params.setPictureFormat(PixelFormat.JPEG);

        // Choose the biggest picture size supported by the hardware
        List<Camera.Size> sizes = params.getSupportedPictureSizes();
        Camera.Size size = sizes.get(sizes.size()-1);
        params.setPictureSize(size.width, size.height);

        List<String> flashModes = params.getSupportedFlashModes();
        if (flashModes.size() > 0)
            params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);

        // Action mode take pictures of fast moving objects
        List<String> sceneModes = params.getSupportedSceneModes();
        if (sceneModes.contains(Camera.Parameters.SCENE_MODE_ACTION))
            params.setSceneMode(Camera.Parameters.SCENE_MODE_ACTION);
        else
            params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);

        // if you choose FOCUS_MODE_AUTO remember to call autoFocus() on
        // the Camera object before taking a picture
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_FIXED);

        camera.setParameters(params);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // open the camera
            mCamera = Camera.open();
        } catch (RuntimeException e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
        Camera.Parameters param;
        param = mCamera.getParameters();

        // modify parameter
        param.setPreviewSize(352, 288);
        mCamera.setParameters(param);
        try {
            // The Surface has been created, now tell the camera where to draw
            // the preview.
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
            refreshCamera();
        } catch (Exception e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
    }
    private void closeCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

        refreshCamera();

    }
    private void startCamara() {
        //Si la camara esta apagada

            //pillamos el control del hardware de la camara
            mCamera = Camera.open();
            //Enlazamos el hardware con el surfaceHolder
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Encendemos todo
        mCamera.startPreview();

            //paramos la camara
        mCamera.stopPreview();
            //liberamos el hardware
        mCamera.release();
            //Sabemos que ya esta en off
    }
}