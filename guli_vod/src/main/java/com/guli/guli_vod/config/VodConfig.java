package com.guli.guli_vod.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@ConfigurationProperties(prefix = "vod")
@Data
public class VodConfig {
    private String regionId;
    private String accessKeyId;
    private String accessKeySecret;

    @Bean
    public DefaultAcsClient initVodClient() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);

        return client;
    }
}
