package com.example.ohdok.classkitapp.gcmservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.ohdok.classkitapp.fragment.MainActivity;
import com.google.android.gcm.GCMBaseIntentService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

//Project Id = "515610376978"
//GCM API Key = "AIzaSyDK4KjksSYUTZMusN83R2VO5FLm-8lGTUQ"

public class GcmIntentService extends GCMBaseIntentService {
    JSONArray jArray;
    JSONObject jObject;
    Toast toast;

    Intent intentNoti;
    private String url = "GCM 등록을 담당하는 서버 url";
    private String urlDelete = "GCM 등록 해지를 담당하는 서버 url";

    private boolean resultResist = false;
    static private String error = "";

    public GcmIntentService() {
        super("515610376978");
        Log.d("test", "GCM 서비스 생성자 실행");
    }



    @Override
    protected void onMessage(Context context, Intent intent) {
        //인자에있는 intent속 Extra에 서버에서 지정해준 NameValue가 들어있다.
        Log.d("test", intent.getExtras().getString("서버지정 key값"));

        //Notification 생성
        try {
            jObject = new JSONObject(intent.getExtras().getString("서버지정 key값"));
            intentNoti = new Intent(getApplicationContext(),
                    MainActivity.class);
            intentNoti.putExtra("test", jObject.toString());

          PendingIntent pendingIntent = PendingIntent.getActivity(
                    getApplicationContext(), 0, intentNoti,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            Notification.Builder builder = new Notification.Builder(this);
            // 작은 아이콘 이미지
            //builder.setSmallIcon();

            //알림 출력될때 상단에 나오는 문구
            builder.setTicker("미리보기");

            //알림 출력시간
            builder.setWhen(System.currentTimeMillis());

            //알림 제목
            builder.setContentTitle("제목");

            //프로그레스 바
            builder.setProgress(100, 50, false);

            //알림 내용
            builder.setContentText("제목 밑 내용");

            //진동설정
            builder.setDefaults(Notification.DEFAULT_VIBRATE);

            //알림 터치시 할 반응(뭐 다른 액티비티를 켜지게 한다든지...)
            builder.setContentIntent(pendingIntent);

            //알림 눌렀을때 자동으로 사라지게할건지 true/false
            builder.setAutoCancel(true);

            //알림 우선순위 항상 먼저온게 위에
            builder.setPriority(Notification.PRIORITY_MAX);

            // 고유ID로 알림을 생성
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(123, builder.build());
        } catch (Exception e) {
            Log.d("error", e.toString());
        }

    }



    @Override
    protected void onError(Context context, String s) {
        Log.d("error", "GcmIntentService Error is " + s);

    }

    @Override
    protected void onRegistered(Context context, String gcmId) {
        //gcmId 인자로 gcmId가 넘어온다
        //ConnectServer 클래스를 이용해서
        //서버로 값을 넘긴다
        ConnectServer connectServer = new ConnectServer();
        connectServer.run(gcmId);


    }

    @Override
    protected void onUnregistered(Context context, String s) {

    }

    public class ConnectServer {
        String url = "requst하고픈 url";

        //http통신을 위한 client생성
        OkHttpClient client = new OkHttpClient();

        //run 인자에 서버로 전송해야할 데이터를 입력해주고 실행
        public void run(String gcmId){
            //Post로 보낼때는 http body에 인코딩해서 보내기때문에 RequstBody에 add(Name, Value)하고 build()
            RequestBody formBody = new FormBody.Builder().add("gcmId", gcmId).build();

            //url에 추가한 body를 포함하는 request생성
            Request request = new Request.Builder().url(url).post(formBody).build();

            //비동기 방식으로 리퀘스트 시작
            client.newCall(request).enqueue(new okhttp3.Callback() {

                //리퀘스트 실패시 동작
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    Log.d("error", "Request Fail " + e.toString());

                }

                //리퀘스트 성공 후 돌아와서 처리할 부분 구현
                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    //response를 가져와서 (toString()이아닌 string()메서드 사용에 유의!!!)
                    String result = response.body().string();
                    try {
                        JSONArray array = new JSONArray(result);
                        //여기서 받아온 JSON처리
                        //GSON이 편하다면 GSON라이브러리 path잡아두고, 가령 필요하다면 로그인, 회원가입, GCM관련 Info class 생성해두었으니
                        // 편하게 사용하시면 될듯합니다.


                    } catch (Exception e) {
                        Log.d("error", "JSON 변환 중 에러내용은 " + e.toString());
                    }
               /*
               요메서드는 Ui작업 관련 메서드이므로 Activity에서 Requst를보내고 Response에 대해서 UI핸들링이 필요할떄 사용한다.
               현재는 따로 클래스를 파서 만들었으므로 이 클래스를 그대로 복사해서
               원하는 액티비티에서 붙이고 사용하면된다.
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //안드로이드는 멀티쓰레드를 지원하지않기때문에
                        //비동기방식으로 데이터를 가져와서 원하는 값을
                        //UI에 대입하기위해서는 UiThread(MainThread)에서만 핸들링이 가능합니다ㅠㅠ
                        //그래서 이 메서드를 활용해서 Ui쪽 데이터를 업데이트해주면됩니다.
                        //ex) txtName.setText(name); -> 요런 메서드들...
                    }
                });*/


                }
            });
        }


    }
}
