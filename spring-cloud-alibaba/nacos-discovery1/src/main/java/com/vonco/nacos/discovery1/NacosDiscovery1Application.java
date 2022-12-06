package com.vonco.nacos.discovery1;

import com.vonco.nacos.discovery1.mq.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author ke feng
 * @title: NacosDiscoveryApplication
 * @projectName my
 * @description: TODO
 * @date 2022/9/28 11:29
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({MySource.class})
public class NacosDiscovery1Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscovery1Application.class,args);
    }
}
