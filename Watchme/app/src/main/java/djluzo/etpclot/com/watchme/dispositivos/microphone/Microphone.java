package djluzo.etpclot.com.watchme.dispositivos.microphone;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;
import java.net.UnknownHostException;

import djluzo.etpclot.com.watchme.sockets.ClientThread;

/**
 *
 * Clases que trabaja con el dispositivo del microfono del movil Android
 *
 * Created by djluzo on 14/7/15.
 */
public class Microphone {



    AudioRecord recorder;
    ClientThread client;
    private int sampleRate = 16000 ; // 44100 for music
    private int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    int minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
    private boolean status = true;

    public Microphone()
    {
        client = new ClientThread();
        Log.d("Estado Micro", "Iniciando Micro");
        new Thread(new Runnable() {

            @Override
            public void run() {

                int minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
                Log.d("VS", "Socket Created");

                byte[] buffer = new byte[minBufSize];

                Log.d("VS","Buffer created of size " + minBufSize);
                Log.d("VS", "Address retrieved");


                recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRate,channelConfig,audioFormat,minBufSize);
                Log.d("VS", "Recorder initialized");

                recorder.startRecording();


                while(status == true) {


                    //reading data from MIC into buffer
                    minBufSize = recorder.read(buffer, 0, buffer.length);
                    Log.i("microBytes", buffer.toString());
                    client.send_byte_audioMicro(buffer);

                }
            }



        }).start();
    }

}
