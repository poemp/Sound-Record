package org.poem.code;

/**
 * http://ai.baidu.com/docs#/ASR-Online-Java-SDK/top
 */
public enum  SpeachCode {
    inputParError(3300,"输入参数不正确"),
    qualityError(3301,"音频质量过差"),
    tooLong(3308,"音频过长"),
    fileSoBig(3310,"输入的音频文件过大");
    private int code;

    private String message;

    SpeachCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String message(int code){
        if(code == 0){
            return null;
        }
        for (SpeachCode speachCode : SpeachCode.values()) {
            if(speachCode.code == code){
                return speachCode.message;
            }
        }
        return "未知错误";
    }
}
