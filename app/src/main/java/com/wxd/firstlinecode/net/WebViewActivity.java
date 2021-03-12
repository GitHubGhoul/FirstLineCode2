package com.wxd.firstlinecode.net;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxd.firstlinecode.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "WebViewActivity";

    private WebView webView;
    private Button send;
    private TextView content;
    private final static String xml = "<apps>\n" +
            "\t<app>\n" +
            "\t\t<id>1</id>\n" +
            "\t\t<name>Google Maps</name>\n" +
            "\t\t<version>1.0</version>\n" +
            "\t</app>\n" +
            "\t<app>\n" +
            "\t\t<id>2</id>\n" +
            "\t\t<name>Chrome</name>\n" +
            "\t\t<version>2.1</version>\n" +
            "\t</app>\n" +
            "\t<app>\n" +
            "\t\t<id>3</id>\n" +
            "\t\t<name>Google Play</name>\n" +
            "\t\t<version>2.3</version>\n" +
            "\t</app>\n" +
            "</apps>";

    private final static String json =
            "[{'id':'5','version':'5.5','name':'Clash of class'}," +
                    "{'id':'6','version':'7.5','name':'Boom Beach'}," +
                    "{'id':'7','version':'3.5','name':'Clash Royale'}]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        send = findViewById(R.id.send);
        content = findViewById(R.id.content);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendRequestHttpURLConnection();
                sendRequestOkHttp();
            }
        });
    }

    private void sendRequestHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                }
            }
        }).start();
        HttpUtil.sendHttpRequest("", new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void sendRequestOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().
                        url("http://www.baidu.com").build();
                try {
                    Response response = client.newCall(request).execute();
                    showResponse(response.body().string());
                    parseXmlPull(xml);
                    //parseXmlSax(xml);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HttpUtil.sendOkHttpRequest("", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void parseXmlPull(String data) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(data));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            Log.e(TAG, "id:" + id);
                            Log.e(TAG, "name:" + name);
                            Log.e(TAG, "version:" + version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseXmlSax(String data){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(data)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJSONObject(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.e(TAG, "id: " + id);
                Log.e(TAG, "name: " + name);
                Log.e(TAG, "version: " + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseGson(String data) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(data, new TypeToken<List<App>>() {
        }.getType());
        for (App app : appList
        ) {
            Log.e(TAG, "id: " + app.getId());
            Log.e(TAG, "name: " + app.getName());
            Log.e(TAG, "version: " + app.getVersion());
        }
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                content.setText(response);
            }
        });
    }
}