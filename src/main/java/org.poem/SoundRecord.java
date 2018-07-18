package org.poem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

@SpringBootApplication
@Controller
@RequestMapping("")
@EnableWebMvc
public class SoundRecord implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/webapp/");
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SoundRecord.class, args);
    }



    @RequestMapping("")
    public String index(){

        return  "index";
    }

    @RequestMapping("/record")
    public void record(HttpServletRequest request){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            System.err.println("错了");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Collection<MultipartFile> files = multiRequest.getFileMap().values();
        for (MultipartFile file : files) {
            try {
                byte[] bytes = file.getBytes();
                System.err.println(bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
