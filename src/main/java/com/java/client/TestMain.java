package com.java.client;

import com.java.model.Employee;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public class TestMain {
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        //activeClient();
       // postEmloyee();
        createFile();
    }

    private static void activeClient() {
        String url = "http://localhost:8080/active";
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(url,
                        HttpMethod.GET,
                        null,
                        String.class);
        System.out.println(responseEntity.getBody());

    }

    private static void createFile()
    {

        File downloadsDir = new File("C:\\Users\\SourabhaPatra\\Downloads\\");

        for(File f : downloadsDir.listFiles()) {
            File profilePic = f;
            if(profilePic.isDirectory())
            {
                continue;
            }

            Resource resource = new FileSystemResource(profilePic);

            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("file", resource);
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parts);
            String url = "http://localhost:8080/multipartFile";
            invokeApi(url, HttpMethod.POST, httpEntity, Long.class);
        }

    }
    private static void postEmloyee() {
        String url="http://localhost:8080/file";
        Employee employee=new Employee();
        employee.setId(1);
        employee.setName("harish");
        employee.setResume(new File("C:\\Users\\SourabhaPatra\\Downloads\\image001.png"));
        HttpEntity httpEntity=new HttpEntity(employee);
        invokeApi(url,HttpMethod.POST,httpEntity,Long.class);
    }

    private static <T> void invokeApi(String url, HttpMethod httpMethod, HttpEntity httpEntity, Class<T> cls) {
        ResponseEntity<T> responseEntity = restTemplate.
                exchange(url,
                        httpMethod,
                        httpEntity,
                        cls);
        System.out.println(responseEntity.getBody());

    }

}
