package com.example.ohdok.classkitapp.network;

import android.util.Log;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Yang Si Young on 2016-07-31.
 * 이 클래스는 서버와의 통신을 위해 만들어졌으며
 * 이렇게 따로 클래스를 파서 사용하여도되지만
 * 서버로 Request 후 전달된 Response 값을 포매팅하여
 * UI작업이 필요할 시 이 클래스를 그대로 복사해서
 * 네트워크 작업 과 동시에 UI핸들링이 필요한 액티비티에 사용하면 된다.
 * runOnUiThread(new Runnable(){}) 이 함수가 UI핸들링 담당하는 함수이며
 * 아래에 주석처리되어있다
 * 액티비티에서 사용할 경우 주석을 풀고 마음껏 UI를 만지면된다.
 * 클래스 사용법
 *  1) pulbic void run(var) => run메서드의 var에 필요한 데이터를 넣어줌
 *  2) runOnUiThread 사용할꺼면 그쪽 주석 풀기
 *  3) ConnectSignInServer connectSignInServer = new ConnectSignInServer();
 *     connectSignInServer.run(var);
 *     이 두줄로 액티비티에서 실행
 */
public class ConnectSignInServer {
    String url = "requst하고픈 url";

    //http통신을 위한 client생성
    OkHttpClient client = new OkHttpClient();

    //run 인자에 서버로 전송해야할 데이터를 입력해주고 실행
    public void run(String email, String password){
        //Post로 보낼때는 http body에 인코딩해서 보내기때문에 RequstBody에 add(Name, Value)하고 build()
        RequestBody formBody = new FormBody.Builder().add("email", email).add("password", password).build();

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
