package org.poem.api;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;
import org.poem.code.SpeachCode;

public class AipSpeechApi {

    private static final String APP_ID = "11555627";
    private static final String API_KEY = "oUliG4k2T0VHLtqgrRzYrBQg";
    private static final String SECRET_KEY = "vlAZY5HxLFuBAgV9sXrgc3EDQFCDM9DC";

    /**
     * 转换
     * @param bytes 字节
     * @param format 格式
     */
    public static String translator(byte[] bytes,String format) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        //调用接口
        JSONObject res = client.asr(bytes, format, 16000, null);
        int code = res.getInt("err_no");
        if(null != SpeachCode.message(code)){
            return SpeachCode.message(code);
        }else{
            return res.get("result") == null ? null : res.getString("result");
        }
    }
}
