package com.vonco.elasticsearch.service.impl;

import com.vonco.elasticsearch.domain.User;
import com.vonco.elasticsearch.service.ElasticsearchService;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ke feng
 * @title: ElasticsearchServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2021/11/26 11:32
 */
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建索引
     * @return
     */
    @Override
    public Boolean createIndex() {
        return elasticsearchRestTemplate.indexOps(User.class).create();
    }

    /**
     * 删除索引
     * @return
     */
    @Override
    public Boolean deleteIndex() {
        return elasticsearchRestTemplate.indexOps(User.class).delete();
    }

    /**
     * 判断索引是否存在
     * @return
     */
    @Override
    public Boolean isExistsIndex() {
        return elasticsearchRestTemplate.indexOps(User.class).exists();
    }

    /**
     * 添加文档
     * @return
     */
    @Override
    public String addDocument(){
        IndexQuery indexQuery = new IndexQuery();
        User user = new User();
        user.setId(1l);
        user.setSex(0);
        user.setName("张三");
        user.setSchool("成都理工学院");
        user.setBirthday(new Date());
        user.setRemark("备注信息");
        indexQuery.setId(user.getId().toString());
        indexQuery.setObject(user);
        return elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of(elasticsearchRestTemplate.getIndexCoordinatesFor(User.class).getIndexName()));
    }

    /**
     * 根据id查询文档
     * @return
     */
    @Override
    public User findDocumentById() {
        return elasticsearchRestTemplate.get("1",User.class);
    }

    /**
     * 根据id删除文档
     * @return
     */
    @Override
    public String deleteDocumentById() {
        return elasticsearchRestTemplate.delete("1",User.class);
    }

    /**
     * 修改文档
     * @return
     */
    @Override
    public String updateDocumentById(){
        IndexQuery indexQuery = new IndexQuery();
        User user = findDocumentById();
        user.setName("李四");
        indexQuery.setId(user.getId().toString());
        indexQuery.setObject(user);
        return elasticsearchRestTemplate.index(indexQuery,IndexCoordinates.of(elasticsearchRestTemplate.getIndexCoordinatesFor(User.class).getIndexName()));
    }

    /**
     * 搜索
     * @return
     */
    @Override
    public List<User> search(){
        //分页
        Pageable pageable = PageRequest.of(0, 10);
        //搜索方式
        //查询全部
        //MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        //精确查询
        //TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name.keyword", "张三");
        //根据id集合精确查询
        /*List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        TermsQueryBuilder queryBuilder = QueryBuilders.termsQuery("id", list);*/
        //模糊查询
        //MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("school", "成都理工").operator(Operator.OR);//一个词匹配查出
        //MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("school", "成都理工").operator(Operator.AND);//所有词匹配查出
        //MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("school", "成都理工").minimumShouldMatch("50%");//50%匹配查出
        //MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("成都", "school", "hobby").minimumShouldMatch("50%").field("school",10);//匹配多个字段
        //布尔查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name.keyword", "张三");
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("school", "成都理工").minimumShouldMatch("50%");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(matchQueryBuilder);
        queryBuilder.filter(termQueryBuilder);
        //排序
        FieldSortBuilder sortBuilder = new FieldSortBuilder("id").order(SortOrder.DESC);
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .preTags("<tag>")
                .postTags("</tag>")
                .field("school");
        //源字段过滤
        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(new String[]{"id","name","school","birthday"}, new String[]{"sex"});
        //构建搜索对象
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withSort(sortBuilder)
                .withHighlightBuilder(highlightBuilder)
                .withPageable(pageable)
                .withSourceFilter(fetchSourceFilter)
                .build();
        //执行搜索
        SearchHits<User> search = elasticsearchRestTemplate.search(query, User.class);
        //搜索结果
        List<SearchHit<User>> searchHits = search.getSearchHits();
        List<User> userList = new ArrayList<>();
        for (SearchHit<User> searchHit : searchHits) {
            //取出源文档内容
            User user = searchHit.getContent();
            //取出高亮字段
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            if (highlightFields != null && highlightFields.size() > 0) {
                List<String> school = highlightFields.get("school");
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : school) {
                    stringBuilder.append(s);
                }
                user.setSchool(stringBuilder.toString());
            }
            userList.add(user);
        }
        return userList;
    }
}
