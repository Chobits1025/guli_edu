package com.avmask.guli_bos.utils;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class FileUpLoadUtils {
    @Value("${bos.ACCESS_KEY_ID}")
    private String ACCESS_KEY_ID ;                   // 用户的Access Key ID
    @Value("${bos.SECRET_ACCESS_KEY}")
    private String SECRET_ACCESS_KEY ;           // 用户的Secret Access Key
    @Value("${bos.bucketName}")
    private String bucketName;
    @Value("${bos.path}")
    private String path;

    public BosClient bosClientFactory(){
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        BosClient client = new BosClient(config);

        return client;
    }

    public String ImgUpLoad(InputStream in, String fileName, String host) throws FileNotFoundException {
        BosClient client = bosClientFactory();
        String key = UUID.randomUUID().toString().replace("-", "") + "_" + fileName;
        client.putObject(bucketName, host+"/"+key, in);
        return path +host+"/"+ key;
    }

    public void rmImg(String cover){
        String key = cover.replace(path, "");

        BosClient bosClient = bosClientFactory();

        bosClient.deleteObject(bucketName,key);

    }

}
