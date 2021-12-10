package com.vonco.elasticsearch;

import com.vonco.elasticsearch.service.ElasticsearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ke feng
 * @title: ElasticsearchTest
 * @projectName my
 * @description: TODO
 * @date 2021/11/26 13:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {
    @Autowired
    private ElasticsearchService elasticsearchService;

    @Test
    public void test(){
        System.out.println(elasticsearchService.search());
    }
}
