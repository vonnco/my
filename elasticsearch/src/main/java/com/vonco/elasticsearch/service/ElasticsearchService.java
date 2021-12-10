package com.vonco.elasticsearch.service;

import com.vonco.elasticsearch.domain.User;

import java.util.List;

/**
 * @author ke feng
 * @title: ElasticsearchService
 * @projectName my
 * @description: TODO
 * @date 2021/11/26 11:30
 */
public interface ElasticsearchService {
    Boolean createIndex();

    Boolean deleteIndex();

    Boolean isExistsIndex();

    String addDocument();

    User findDocumentById();

    String deleteDocumentById();

    String updateDocumentById();

    List<User> search();
}
