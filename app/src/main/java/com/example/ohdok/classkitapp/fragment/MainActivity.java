package com.example.ohdok.classkitapp.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.activity.Splash_Activity;
import com.example.ohdok.classkitapp.base.BaseAppCompatActivity;
import com.example.ohdok.classkitapp.base.BaseDrawerActivity;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends BaseAppCompatActivity {

    boolean isLogin = false;

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

//        Window window = getWindow();
//
//// clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//// finally change the color
//        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_main);




        Intent i = new Intent(MainActivity.this, Splash_Activity.class);

        startActivity(i);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText pw = (EditText) findViewById(R.id.pw);
        Button button = (Button) findViewById(R.id.loginBtn);

        oAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        oAuthLoginButton.setOAuthLoginHandler(oAuthLoginHandler);
        loginModule = OAuthLogin.getInstance();

        //Context , Client Id, Client Secret , 로그인시 사용될 앱이름 넘겨줌
        loginModule.init(this, "bHz9i96mxyQyPOGewrLB", "KxseSECw8j", "ClassKitApp");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ohdoking4",email.getText().toString()+":"+pw.getText().toString());
                if(checkLogin(email.getText().toString(),pw.getText().toString()) == true){
                    Intent i = new Intent(MainActivity.this, BaseDrawerActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "로그인을 실패 하셨습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean checkLogin(String email, String pw){
        Log.i("ohdoking4",email.equals("test@naver.com")+":"+pw.equals("1234"));
        if(emailsEquals("test@naver.com",email) && pw.equals("1234")){
            return true;
        }
        return false;
    }
    boolean emailsEquals(String email1,String email2) {
        Pattern address=Pattern.compile("([\\w\\.]+)@([\\w\\.]+\\.\\w+)");
        Matcher match1=address.matcher(email1);
        Matcher match2=address.matcher(email2);
        if(!match1.find() || !match2.find()) return false; //Not an e-mail address? Already false
        if(!match1.group(2).equalsIgnoreCase(match2.group(2))) return false; //Not same serve? Already false
        switch(match1.group(2).toLowerCase()) {
            case "gmail.com":
                String gmail1=match1.group(1).replace(".", "");
                String gmail2=match2.group(1).replace(".", "");
                return gmail1.equalsIgnoreCase(gmail2);
            default: return match1.group(1).equalsIgnoreCase(match2.group(1));
        }
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
                Intent intent = new Intent(getApplicationContext(), BaseDrawerActivity .class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
            Log.d("aaaa", result);
        }
    }
}
