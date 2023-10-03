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
                    dataEntry17.clear();

                    for (int i = 0; i < feeds.length(); i++) {
                        JSONObject feed = feeds.getJSONObject(i);

                        boolean field1Value = feed.has("field1") && !feed.isNull("field1") && !feed.getString("field1").equals("0");
                        boolean field2Value = feed.has("field2") && !feed.isNull("field2") && !feed.getString("field2").equals("0");
                        boolean field3Value = feed.has("field3") && !feed.isNull("field3") && !feed.getString("field3").equals("0");
                        boolean field4Value = feed.has("field4") && !feed.isNull("field4") && !feed.getString("field4").equals("0");
                        boolean field5Value = feed.has("field5") && !feed.isNull("field5") && !feed.getString("field5").equals("0");
                        boolean field6Value = feed.has("field6") && !feed.isNull("field6") && !feed.getString("field6").equals("0");
                        boolean field7Value = feed.has("field7") && !feed.isNull("field7") && !feed.getString("field7").equals("0");
                        boolean field8Value = feed.has("field8") && !feed.isNull("field8") && !feed.getString("field8").equals("0");

                        if (feed.getInt("entry_id") == 17) {
                            dataEntry17.add(field1Value);
                            dataEntry17.add(field2Value);
                            dataEntry17.add(field3Value);
                            dataEntry17.add(field4Value);
                            dataEntry17.add(field5Value);
                            dataEntry17.add(field6Value);
                            dataEntry17.add(field7Value);
                            dataEntry17.add(field8Value);
                        }
                    }

                    System.out.println("Data Entry 17:");
                    for (boolean value : dataEntry17) {
                        System.out.println("\n"+value+"BBAA155555");
                    }


                    for (boolean value : dataEntry17) {
                        if (value) {
                            trueCount17++;
                        }
                    }

                    textView2.setText(String.valueOf(trueCount17));

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