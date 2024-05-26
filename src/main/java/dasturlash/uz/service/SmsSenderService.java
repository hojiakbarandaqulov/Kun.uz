package dasturlash.uz.service;

import dasturlash.uz.entity.history.SmsHistoryEntity;
import dasturlash.uz.repository.SmsHistoryRepository;
import dasturlash.uz.util.RandomUtil;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmsSenderService {
    @Value("${sms.url}")
    private String smsUrl;
    @Value("${my.eskiz.uz.email}")
    private String myEskizUzEmail;

    @Value("${my.eskiz.uz.password}")
//    @Value("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTkyOTczMjksImlhdCI6MTcxNjcwNTMyOSwicm9sZSI6InRlc3QiLCJzaWduIjoiYWRmODk1MDJhZDAyYjBlNDJjNTgwYTNiYmE3NmMyNGQwNjlhYWRmMTQ5NWY2N2Y1ZmEwNjc5OTBlMTE4YjU4NiIsInN1YiI6Ijc0MDIifQ.roNBehHx4NVImf89UQBSbxVNBOqhWuAcv72ZrEytIJY")
    private String myEskizUzPassword;
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;
    public String sendSms(String phone) {
        String code = RandomUtil.getRandomSmsCode();
        String message = "This is test from Eskiz: " + code;
        send(phone, message);
        return null;
    }

    public  void send(String phone, String message) {
        String token = "bearer " + getToken();
        String prPhone = phone;
        if (prPhone.startsWith("+")) {
            prPhone = prPhone.substring(1);
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("mobile_phone", prPhone)
                .addFormDataPart("message", message)
                .addFormDataPart("from", "4546")
                .build();

        okhttp3.Request request = new Request.Builder()
                .url(smsUrl + "api/message/sms/send")
                .method("POST", body)
                .header("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println(response);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private String getToken() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("email", myEskizUzEmail)
                .addFormDataPart("password", myEskizUzPassword)
                .build();
        Request request = new Request.Builder()
                .url(smsUrl + "api/auth/login")
                .method("POST", body)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException();
            } else {
                JSONObject object = new JSONObject(response.body().string());
                JSONObject data = object.getJSONObject("data");
                Object token = data.get("token");
                return token.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}


