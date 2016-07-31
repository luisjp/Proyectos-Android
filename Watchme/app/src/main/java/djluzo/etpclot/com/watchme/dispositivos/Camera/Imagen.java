package djluzo.etpclot.com.watchme.dispositivos.Camera;

import android.hardware.Camera;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by djluzo on 14/7/15.
 */
public class Imagen {

    public static Camera mCamera;

    public static Camera.PictureCallback capturePhoto = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            FileOutputStream outStream = null;
                try {
                    outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));
                    outStream.write(data);
                    outStream.close();
                    Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
        };
    };

    public static void tomar_Foto()
    {
        mCamera.takePicture(null, null, capturePhoto);
    }

}
