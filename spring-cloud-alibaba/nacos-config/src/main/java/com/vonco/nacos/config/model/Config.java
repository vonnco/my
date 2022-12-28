package com.vonco.nacos.config.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ke feng
 * @title: Config
 * @projectName my
 * @description: TODO
 * @date 2022/9/27 17:04
 */
@Data
@Component
@ConfigurationProperties(prefix = "config")
public class Config {
    private String name;
    private String type;
}
