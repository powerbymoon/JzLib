package com.example.administrator.jzlib.jzlib.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.dd.CircularProgressButton;
import com.example.administrator.jzlib.R;
import com.example.administrator.jzlib.jzlib.Been.StudentInfo;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleMeth;
import com.example.administrator.jzlib.jzlib.Util.JsoupUtil;


import org.apache.http.*;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


import java.util.HashMap;
import java.util.Map;





public class LoginActivity extends AppCompatActivity {
        private EditText captcha;
        private ImageView imageView;
        public static Activity ActivityLogin;
        private CircularProgressButton loginButton;
        private EditText loginNumber;
        private EditText loginKey;
        private CheckBox recd;
       // private ProgressDialog mypDialog;
        private Map<String,String> map = new HashMap<String,String>();

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            ActivityLogin = this;
            init();
            if (!GlobleMeth.hasInternet(LoginActivity.this)) {
                GlobleMeth.showToast(getApplicationContext(), "网络连接失败，请检查网络！");
            }
            else {
                new InitCaptcha().execute();
            }
            imageView = (ImageView) this.findViewById(R.id.captche_pic);
            // button = (Button) this.findViewById(R.id.button);
            imageView.setOnClickListener
                    (new View.OnClickListener()//�ӿ�
                     {
                         public void onClick(View v)
                         {
                             if (!GlobleMeth.hasInternet(LoginActivity.this)) {
                                 GlobleMeth.showToast(getApplicationContext(), "网络连接失败，请检查网络！");
                             }
                             else{
                                 //GlobleData.client= new DefaultHttpClient();
                                 new InitCaptcha().execute();
                             }
                         }
                     }
                    );

        }


        class  InitCaptcha extends AsyncTask<String,String, Bitmap> {
            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                System.out.println("onPreExecute");
                super.onPreExecute();
            }
            @Override
            protected Bitmap doInBackground(String... params) {
                // TODO Auto-generated method stub
                System.out.println("doInBackground");
                return initCaptcha();
            }
            @Override
            protected void onPostExecute(Bitmap result) {
                // TODO Auto-generated method stub
                System.out.println("onPostExecute");
                imageView.setImageBitmap(result);
                super.onPostExecute(result);
            }
        }



        Bitmap initCaptcha(){
            HttpParams params= GlobleAtrr.client.getParams();
            Bitmap bitmap=null;
            try{
                HttpPost httpPost = new HttpPost("http://opac.jluzh.com/reader/captcha.php");
                HttpResponse httpResponse = GlobleAtrr.client.execute(httpPost);

                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                   // GlobleData.cookies = ((AbstractHttpClient) GlobleData.client).getCookieStore().getCookies().get(0).getValue();
                    byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "抱歉！网络异常。", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"异常", Toast.LENGTH_SHORT).show();
            }		return bitmap;
        }




        void init(){
            captcha = (EditText) findViewById(R.id.login_et_captcha);
            loginNumber = (EditText) findViewById(R.id.login_et_phone);
            loginKey = (EditText) findViewById(R.id.login_et_code);
            loginNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    // TODO Auto-generated method stub
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                    // TODO Auto-generated method stub
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    // TODO Auto-generated method stub
                    loginKey.setText("");
                }
            });
            loginButton = (CircularProgressButton) findViewById(R.id.login_btn_login);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    loginButton.setProgress(50);
                    String number = loginNumber.getText().toString();
                    String passwd = loginKey.getText().toString();
                    String scaptcha = captcha.getText().toString();
                    //map.put("captcha",scaptcha);
                    map.put("number",number);
                    map.put("passwd",passwd);
                    map.put("captcha",scaptcha);
                    map.put("select","cert_no");
                    map.put("returnUrl",null);
                    GlobleMeth.showToast(getApplication(),map.get("number"));
                    GlobleMeth.showToast(getApplication(),map.get("passwd"));
                    GlobleMeth.showToast(getApplication(),map.get("captcha"));
                    Login login = new Login();
                    login.execute(map);
                }
            });
        }
        class Login extends AsyncTask<Map<String,String>, Integer, Boolean> {
            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
               // mypDialog.show();
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                // TODO Auto-generated method stub
                super.onProgressUpdate(values);
            }
            @Override
            protected Boolean doInBackground(Map<String, String>...map) {
                // TODO Auto-generated method stub
                return JsoupUtil.loginUrl(map[0]);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                // TODO Auto-generated method stub
               // mypDialog.cancel();
                if (result) {
                    loginButton.setProgress(100);
                    GlobleAtrr.flag = true;
                    GlobleMeth.showToast(getApplication(), StudentInfo.name);
                    //Intent i = new Intent();
                    //i.setClass(getApplicationContext(),MainActivity.class);

                   // LoginActivity.this.setResult(RESULT_OK, i);   //RESULT_OK是返回状态码
                    LoginActivity.this.finish();
                    // Intent i = new Intent();
                   // i.setClass(getApplicationContext(), StartActivity.class);
                   // LoginActivity.this.setResult(RESULT_OK, i);   //RESULT_OK是返回状态码
                  //  LoginActivity.this.finish();
                    map.clear();
                } else {
                    loginButton.setProgress(-1);
                    GlobleMeth.showToast(getApplicationContext(), "请检查是否输入有误");
                }
                super.onPostExecute(result);
            }
        }
    }

