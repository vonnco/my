package com.vonco.nacos.discovery2;

import com.vonco.nacos.discovery2.mq.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author ke feng
 * @title: NacosDiscovery2Application
 * @projectName my
 * @description: TODO
 * @date 2022/9/28 11:43
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableBinding({MySink.class })
public class NacosDiscovery2Application {
    public static void main(String[] args) {
        SpringApplication.run(NacosDiscovery2Application.class,args);
    }
}
