package org.poem;


import org.apache.commons.io.IOUtils;
import org.poem.api.AipSpeechApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@SpringBootApplication
@Controller
@RequestMapping("")
public class SoundRecord{
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SoundRecord.class, args);
    }



    @RequestMapping("")
    public String index(){
        return  "/index.html";
    }

    @RequestMapping("/record")
    @ResponseBody
    public void record(HttpServletRequest request){
        StandardMultipartHttpServletRequest multiRequest = (StandardMultipartHttpServletRequest) request;
        Collection<MultipartFile> files = multiRequest.getFileMap().values();
        for (MultipartFile file : files) {
            try {
                byte[] bytes = file.getBytes();
                String contentType = file.getContentType();
                String format = contentType.substring(contentType.indexOf("/")+1);
                System.err.println(bytes.length+ "   " + fromateDate()+ "." + format);
                AipSpeechApi.translator(bytes,format);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String fromateDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(new Date());
    }
}
