package djluzo.etpclot.com.watchme;


import java.io.IOException;

import android.app.Activity;

import android.hardware.Camera;
import android.os.Bundle;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import djluzo.etpclot.com.watchme.dispositivos.Camera.CameraPreview;
import djluzo.etpclot.com.watchme.dispositivos.Camera.Imagen;
import djluzo.etpclot.com.watchme.dispositivos.microphone.Microphone;


public class WatchmeActivity extends Activity  implements SurfaceHolder.Callback {

    private CameraPreview cameraPrev;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera cam;
    private Microphone micro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchme);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        //Activamos la camara
        cameraPrev = new CameraPreview(this, cam, surfaceView, surfaceHolder);

        //Activamos el microfono
        micro = new Microphone();


    }


    public void captureImage(View v) throws IOException {
        //take the picture
        Imagen.tomar_Foto();

    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
