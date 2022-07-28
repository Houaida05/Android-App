package com.example.miniproj;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Spinner type=(Spinner) findViewById(R.id.spinner);
        String[] types = { "Technicien", "Chef" };
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(ad);
        Button register=(Button)findViewById(R.id.ajouter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
                EditText login=(EditText)findViewById(R.id.login1);
                EditText pwd=(EditText)findViewById(R.id.pwd1);
                EditText name=(EditText)findViewById(R.id.desc);
                EditText lastname=(EditText)findViewById(R.id.location);
                Spinner type=(Spinner) findViewById(R.id.spinner);
                String l=login.getText().toString();
                String pw=pwd.getText().toString();
                String nm=name.getText().toString();
                String ln=lastname.getText().toString();
                String tp=type.getSelectedItem().toString();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
               //   String url="http://172.16.110.10/addUser.php";
                //String url="http://192.168.43.122/addUser.php";
               String url="http://172.16.110.10/addUser.php";
             //   String url="http://172.16.110.40/addUser.php";

                // Request parameters and other properties.
                List<NameValuePair> params = new ArrayList<NameValuePair>(2);
                params.add(new BasicNameValuePair("login", l));
                params.add(new BasicNameValuePair("pwd", pw));
                params.add(new BasicNameValuePair("name", nm));
                params.add(new BasicNameValuePair("lastname", ln));
                params.add(new BasicNameValuePair("type", tp));
               // Toast.makeText(getApplicationContext(), tp, Toast.LENGTH_LONG).show();

                HttpPost httppost=new HttpPost(url);
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {

                    HttpClient httpclient=new DefaultHttpClient();
                    //HttpPost httppost=new HttpPost(url);
                    Log.d("gggggg", "rrrrr");
                    HttpResponse response=httpclient.execute(httppost);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Success ", Toast.LENGTH_LONG).show();

                Intent t = new Intent(Register.this,Login.class);
                startActivity(t);



            }
        });


    }


}