package com.avmask.guli_bos.test;

import com.avmask.guli_bos.utils.FileUpLoadUtils;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class guli_test {
    @Autowired
    private FileUpLoadUtils fileUpLoadUtils ;
    @Test
    public void fileUpload() throws FileNotFoundException {
        String ACCESS_KEY_ID = "dda695cbf9264f15921f481aebc33306";                   // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "8941d2cd3bb74d2483983af03e10baed";           // 用户的Secret Access Key

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        BosClient client = new BosClient(config);
        File file = new File("E:\\JavaTools\\shkd00836jp-8.jpg");
        FileInputStream fil = new FileInputStream(file);
        String key = UUID.randomUUID().toString().replace("-","")+"_"+file.getName();

        client.putObject("chobits", key, fil);

    }
    @Test
    public void doesBucketExist(){
        String ACCESS_KEY_ID = "dda695cbf9264f15921f481aebc33306";                   // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "8941d2cd3bb74d2483983af03e10baed";           // 用户的Secret Access Key

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        BosClient client = new BosClient(config);
        boolean chobits = client.doesBucketExist("chobits");

        System.out.println(chobits);
    }
    @Test
    public void delImg(){
        fileUpLoadUtils.rmImg("https://chobits.bj.bcebos.com/null/02049574194f4571b9e775bf0cb683cd_21a4462309f7905265ab435303f3d7ca7bcbd523.jpg");
    }
}
