package djluzo.etpclot.com.watchme.sockets;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

/**
 * Created by djluzo on 12/6/15.
 */
public class ClientThread implements  Runnable{

    private static final int SERVERPORT = 8080;
    private static final String SERVER_IP = "http://192.168.1.3";

    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;
    private SocketIO socketio;

    public ClientThread()
    {

    }
    @Override
    public void run() {
        try {

            socketio = new SocketIO(SERVER_IP + ":" + SERVERPORT);
            socketio.connect(new IOCallback() {
                @Override
                public void on(String event, IOAcknowledge ack, Object... args) {
                    if ("echo back".equals(event) && args.length > 0) {
                        Log.d("SocketIO", "" + args[0]);
                        // -> "hello"
                    }
                }

                @Override
                public void onMessage(JSONObject json, IOAcknowledge ack)
                {
                    try {
                        Log.d("Server said onMessageJson","Server said:" + json.toString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onMessage(String data, IOAcknowledge ack) {
                    Log.d("Server said onMessageData","Server said: " + data);

                }
                @Override
                public void onError(SocketIOException socketIOException) {
                    Log.d("Server Error","an Error Occured");
                    socketIOException.printStackTrace();
                }
                @Override
                public void onDisconnect() {
                    Log.d("Server state","Disconnected");

                }
                @Override
                public void onConnect() {
                    Log.d("Server state","Connected");

                }
            });
            socketio.send("Hello Server!");
            try {
                JSONObject json = new JSONObject();
                json.putOpt("message", "mesnsajge");
                socketio.emit("user message", json);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            socketio.emit("my other event", "hello");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void send_image(String pathImage)
    {
        File file = new File(pathImage);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Log.e("Error FILE", e.toString());
        }
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        try {
            object.put("file", encodedString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        array.put(object);


        socketio.emit("uploadFile", array);
    }
    public void send_byte(byte[] bytes)
    {

        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        try {
            object.put("file", encodedString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        array.put(object);


        socketio.emit("uploadFile", array);
    }

    public void send_byte_audioMicro(byte[] bytes)
    {

        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        try {
            Log.i("bytes_audioMicro", encodedString);
            object.put("file", encodedString);
            array.put(object);
            socketio.emit("AudioJSON", array);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException ex)
        {
            ex.printStackTrace();
        }

    }
}
