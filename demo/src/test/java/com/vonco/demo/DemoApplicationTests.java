package com.vonco.demo;

import com.vonco.demo.domain.TreeVo;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
        List<TreeVo> list = new ArrayList<>();
        TreeVo treeVo1 = new TreeVo();
        treeVo1.setCode("01");
        treeVo1.setName("一级");
        treeVo1.setParentCode("0");
        list.add(treeVo1);
        TreeVo treeVo2 = new TreeVo();
        treeVo2.setCode("0101");
        treeVo2.setName("二级");
        treeVo2.setParentCode("01");
        list.add(treeVo2);
        TreeVo treeVo3 = new TreeVo();
        treeVo3.setCode("010101");
        treeVo3.setName("三级");
        treeVo3.setParentCode("0101");
        list.add(treeVo3);
        TreeVo treeVo4 = new TreeVo();
        treeVo4.setCode("010102");
        treeVo4.setName("三级");
        treeVo4.setParentCode("0101");
        list.add(treeVo4);

        List<TreeVo> collect = list.stream()
                // 2. 找出所有顶级（规定 0 为顶级）
                .filter(o -> "0101".equals(o.getParentCode()))
                // 3.给当前父级的 childList 设置子
                .peek(o -> o.setChildren(getChildList(o, list)))
                // 4.收集
                .collect(Collectors.toList());
        System.out.println(collect);
    }
    //根据当前父类找出子类， 并通过递归找出子类的子类
    private List<TreeVo> getChildList(TreeVo treeVo, List<TreeVo> list) {
        return list.stream()
                //筛选出父节点id == parentId 的所有对象 => list
                .filter(o -> treeVo.getCode().equals(o.getParentCode()))
                .peek(o -> o.setChildren(getChildList(o, list)))
                .collect(Collectors.toList());
    }

    @Test
    void Test(){
        String s = "010101_010101_0101";
        if (s.split("_").length > 2) {
            s = s.substring(s.indexOf("_") + 1);
        }
        System.out.println(s);
    }

    @Test
    void Test01(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        System.out.println(ip);
    }

    @Test
    void Test02(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("123456");
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("nameBloom");
        bloomFilter.tryInit(100000000L,0.01);
        bloomFilter.add("vonco");
        System.out.println(bloomFilter.contains("ce"));
        System.out.println(bloomFilter.contains("vonco"));
    }
}
