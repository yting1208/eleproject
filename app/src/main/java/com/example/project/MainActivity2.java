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


public class MainActivity2 extends AppCompatActivity {
    private List<Boolean> dataEntry18 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new MainActivity2.FetchDataTask().execute("https://api.thingspeak.com/channels/776926/feeds.json?results=2");
        Button btn9 = findViewById(R.id.b9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private class FetchDataTask extends AsyncTask<String, Void, String> {
        int trueCount18 = 0;
        TextView t2 = findViewById(R.id.t2);
        TextView t5 = findViewById(R.id.t5);
        Button btn1 = findViewById(R.id.b1);
        Button btn2 = findViewById(R.id.b2);
        Button btn3 = findViewById(R.id.b3);
        Button btn4 = findViewById(R.id.b4);
        Button btn5 = findViewById(R.id.b5);
        Button btn6 = findViewById(R.id.b6);
        Button btn7 = findViewById(R.id.b7);
        Button btn8 = findViewById(R.id.b8);


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
                    dataEntry18.clear();

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

                            dataEntry18.add(field1Value!= null && !field1Value.equals("0"));
                            dataEntry18.add(field2Value!= null && !field2Value.equals("0"));
                            dataEntry18.add(field3Value!= null && !field3Value.equals("0"));
                            dataEntry18.add(field4Value!= null && !field4Value.equals("0"));
                            dataEntry18.add(field5Value!= null && !field5Value.equals("0"));
                            dataEntry18.add(field6Value!= null && !field6Value.equals("0"));
                            dataEntry18.add(field7Value!= null && !field7Value.equals("0"));
                            dataEntry18.add(field8Value!= null && !field8Value.equals("0"));
                        }
                    }

                    System.out.println("Data Entry 17:");
                    for (boolean value : dataEntry18) {
                        System.out.println("\n" + value + "BBAA155555");
                    }

                    for (boolean value : dataEntry18) {
                        if (value) {
                            trueCount18++;
                        }
                    }

                    t2.setText(String.valueOf(trueCount18));
                    t5.setText((String.valueOf(dataEntry18)));

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

            for (int i = 0; i < dataEntry18.size(); i++) {
                boolean value = dataEntry18.get(i);
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