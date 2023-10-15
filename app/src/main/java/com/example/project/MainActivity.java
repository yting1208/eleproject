package com.example.project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Boolean> dataEntry17 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate() executed");

        new FetchDataTask().execute("https://api.thingspeak.com/channels/2284744/feeds.json?api_key=2I4C33CCQTWWGEL0&results=2");
        Button btn9 = findViewById(R.id.button9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    private class FetchDataTask extends AsyncTask<String, Void, String> {
        int trueCount17 = 0;
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView4 = findViewById(R.id.textView4);

        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);

        @Override
        protected String doInBackground(String... urls) {
            try {
                return makeHttpRequest(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("FetchDataTask", "Result: " + result);

            if (result != null) {
                try {

                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray feeds = jsonObject.getJSONArray("feeds");
                    JSONObject channel = jsonObject.getJSONObject("channel");

                    int lastEntryId = channel.getInt("last_entry_id");

                    Log.d("Fetch111111", "現在ID：" + lastEntryId);
                    dataEntry17.clear();

                    for (int i = 0; i < feeds.length(); i++) {

                        JSONObject feed = feeds.getJSONObject(i);
                        int entryId = feed.getInt("entry_id");

                        if (entryId == lastEntryId) {
                            String field1Value = feed.isNull("field1")?null : feed.getString("field1");
                            String field2Value = feed.isNull("field2")?null : feed.getString("field2");
                            String field3Value = feed.isNull("field3")?null : feed.getString("field3");
                            String field4Value = feed.isNull("field4")?null : feed.getString("field4");
                            String field5Value = feed.isNull("field5")?null : feed.getString("field5");
                            String field6Value = feed.isNull("field6")?null : feed.getString("field6");
                            String field7Value = feed.isNull("field7")?null : feed.getString("field7");
                            String field8Value = feed.isNull("field8")?null : feed.getString("field8");


                            dataEntry17.add(field1Value!= null && !field1Value.equals("0"));
                            dataEntry17.add(field2Value!= null && !field2Value.equals("0"));
                            dataEntry17.add(field3Value!= null && !field3Value.equals("0"));
                            dataEntry17.add(field4Value!= null && !field4Value.equals("0"));
                            dataEntry17.add(field5Value!= null && !field5Value.equals("0"));
                            dataEntry17.add(field6Value!= null && !field6Value.equals("0"));
                            dataEntry17.add(field7Value!= null && !field7Value.equals("0"));
                            dataEntry17.add(field8Value!= null && !field8Value.equals("0"));
                        }
                    }

                    System.out.println("Data Entry 17:");
                    for (boolean value : dataEntry17) {
                        System.out.println("\n" + value + "BBAA155555");
                    }

                    for (boolean value : dataEntry17) {
                        if (value) {
                            trueCount17++;
                        }
                    }

                    textView2.setText(String.valueOf(trueCount17));

                    textView4.setText(String.valueOf((dataEntry17))); //此行顯示布林值

                    setButtonColor();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        private void setButtonColor() {
            List<Button> buttons = new ArrayList<>();
            buttons.add(btn1);
            buttons.add(btn2);
            buttons.add(btn3);
            buttons.add(btn4);
            buttons.add(btn5);
            buttons.add(btn6);
            buttons.add(btn7);
            buttons.add(btn8);

            for (int i = 0; i < dataEntry17.size(); i++) {
                boolean value = dataEntry17.get(i);
                if (value) {
                    buttons.get(i).setBackgroundColor(getResources().getColor(R.color.black));
                }
            }
        }

    }

    private String makeHttpRequest(String urlString) throws IOException {
        Log.d("FetchDataTask", "HTTP request successful");

        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        byte[] buffer = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();

        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            stringBuilder.append(new String(buffer, 0, bytesRead));
        }

        inputStream.close();
        return stringBuilder.toString();

    }
}
