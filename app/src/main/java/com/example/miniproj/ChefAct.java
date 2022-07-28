package com.example.miniproj;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChefAct extends AppCompatActivity {
    ListView lv;
    ArrayList<HashMap<String, String>> values = new ArrayList<HashMap<String, String>>();
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        lv = findViewById(R.id.lst);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);

        HttpPost httppost = new HttpPost(url);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("ggggg", url);

        try {
            HttpClient httpclient=new DefaultHttpClient();
            Log.d("gggggg", "rrrrr");
            HttpResponse response=httpclient.execute(httppost);


            InputStream inputStream = response.getEntity().getContent();
            String result=null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line=null;
            while((line=reader.readLine())!= null){
                sb.append(line+"\n");
            }
            inputStream.close();
            result=sb.toString();
            Log.d("ggggg",result);
            //res.setText(result);

            JSONArray jArray=new JSONArray(result);// this statment gives error@Override

            for(int i=0;i<jArray.length();i++){
                JSONObject json=jArray.getJSONObject(i);
                HashMap<String,String> m = new HashMap<String, String>();
                m.put("id",json.getString("id"));
                m.put("description",json.getString("description"));
                m.put("gps",json.getString("gps"));
                m.put("dateI",json.getString("dateI"));
                m.put("etat",json.getString("etat"));
                values.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        SimpleAdapter adapter= new SimpleAdapter(ChefAct.this,values,R.layout.item, new String[]{"id","description","gps","dateI","etat"},new int[]{ R.id.id, R.id.description, R.id.address, R.id.dateIssue,R.id.state});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t = view.findViewById(R.id.id);
                Intent in = new Intent(ChefAct.this, Details.class);
                in.putExtra("id", t.getText().toString());

                startActivity(in);
            }
        });
    }
}
