/*
package com.example.ohdok.classkitapp.naver_api_test;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.ohdok.classkitapp.fragment.MainActivity;
import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.network.ClientLoginInfo;
import com.example.ohdok.classkitapp.network.ClientSignUpInfo;
import com.example.ohdok.classkitapp.network.NetworkService;
import com.google.gson.Gson;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.data.OAuthErrorCode;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONArray;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    //Login api관련
    OAuthLogin loginModule;
    OAuthLoginButton oAuthLoginButton;
    String accessToken;
    String refreshToken;
    long expiresAt;
    String tokenType;

    private String name;
    private String email;
    private String password;

    //Login 인증 후 넘어오는 xml 파싱관련
    XmlPullParser xmlPullParser;
    XmlPullParserFactory xmlPullParserFactory;

    public final static String url = "http://igrus.mireene.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        oAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        oAuthLoginButton.setOAuthLoginHandler(oAuthLoginHandler);
        loginModule = OAuthLogin.getInstance();

        //Context , Client Id, Client Secret , 로그인시 사용될 앱이름 넘겨줌
        loginModule.init(this, "bHz9i96mxyQyPOGewrLB", "KxseSECw8j", "ClassKitApp");

    }

    //로그인 과정 Handler
    OAuthLoginHandler oAuthLoginHandler = new OAuthLoginHandler() {

        @Override
        public void run(boolean success) {
            //접근 성공하면
            if (success) {
                //접근 토큰 받고
                accessToken = loginModule.getAccessToken(getApplicationContext());

                //갱신 토큰 받고(접근 토큰 만료시 사용)
                refreshToken = loginModule.getRefreshToken(getApplicationContext());

                //접근 토큰 만료 기한
                expiresAt = loginModule.getExpiresAt(getApplicationContext());

                //토큰 타입("Bearer" 로 고정되어있음)
                tokenType = loginModule.getTokenType(getApplicationContext());
                new ConnectServer().execute();

                Toast.makeText(getApplicationContext(), "accessToken = " + accessToken + " " + "refreshToken = " + refreshToken + " " + "tokenType = " + tokenType, Toast.LENGTH_SHORT).show();
            } else {
                String errorCode = loginModule.getLastErrorCode(getApplicationContext()).getCode();
                String errorDesc = loginModule.getLastErrorDesc(getApplicationContext());
                Toast.makeText(getApplicationContext(), "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };


    //로그인 이후 넘어오는 xml파싱
    class ConnectServer extends AsyncTask<Void, Void, Void> {
        String result;

        @Override
        protected Void doInBackground(Void... voids) {
            // 여기서 로그인 정보를 api를 통해 가져오고 api 사용에 접근 토큰이 사용된다.
            // 만료시 갱신 토큰을 접근 토큰으로 바꾸고, 새로운 갱신 토큰을 받아야한다
            //사용자 이름과 email, address를 가져온다
            result = loginModule.requestApi(getApplicationContext(), accessToken, "https://openapi.naver.com/v1/nid/getUserProfile.xml");
            try {
                xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(true);
                xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(new StringReader(result));
                int eventType = xmlPullParser.getEventType();
                String tag;

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            Log.d("aaaa", "Start Parsing");
                            break;

                        case XmlPullParser.START_TAG:
                            tag = xmlPullParser.getName();

                            if (tag.equals("name")) {
                                xmlPullParser.next();
                                name = xmlPullParser.getText();
                                Log.d("aaaa", "Name is " + xmlPullParser.getText());

                            } else if (tag.equals("email")) {
                                xmlPullParser.next();
                                email = xmlPullParser.getText();
                                Log.d("aaaa", "Email is " + xmlPullParser.getText());

                            } else if (tag.equals("resultcode")) {
                                xmlPullParser.next();
                                Log.d("aaaa", "Resultcode is " + xmlPullParser.getText());
                            } else if (tag.equals("id")) {
                                xmlPullParser.next();
                                password = xmlPullParser.getText();
                                Log.d("aaaa", "Client id is " + xmlPullParser.getText());
                            }
                            break;


                    }
                    eventType = xmlPullParser.next();
                }


            } catch (Exception e) {
                Log.d("error", "xml parse error is " + e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            if (!password.isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
            Log.d("aaaa", result);
        }
    }


    OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
                       //요밑에있는거 디자인 구현되고 난 후, view.getId()를 통해 버튼의 아이디를 식별하고
            // EditText안에 있는 값들을 밑의 메서드에 넣어서 실행시켜준다

        }
    };
}
*/
