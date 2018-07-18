package org.poem;


import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

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
        FileOutputStream fileOutputStream = null;
        for (MultipartFile file : files) {
            try {
                byte[] bytes = file.getBytes();
                System.err.println(bytes.length);

                fileOutputStream = new FileOutputStream(new File(file.getOriginalFilename()));
                fileOutputStream.write(bytes,0 , bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                IOUtils.closeQuietly(fileOutputStream);
            }
        }
    }
}
