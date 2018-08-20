package com.onlinejoblog.repository;

import com.onlinejoblog.model.Book;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by pankaj on 08,2018
 */
@Repository
public class BookRepository  {
    @Value("${es.index}")
    private String index;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public String saveDocument(Map<String, Object> sourceMap, XContentType xContentType, String id)
            throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, "doc", id).source(sourceMap);
        IndexResponse index = restHighLevelClient.index(indexRequest);
        return index.getId();
    }

    public String saveDocument(String source, XContentType xContentType, String id)
            throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, "doc", id);
        indexRequest.source(source, xContentType);
        IndexResponse index = restHighLevelClient.index(indexRequest);
        return index.getId();
    }
    public String saveDocument(Book book, String id)
            throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, "_doc", id);
        indexRequest.source(book, XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest);
        return index.getId();
    }

    public void updateDocument(Map<String, Object> sourceMap, String id) throws IOException {
        UpdateRequest request = new UpdateRequest(index, "doc", id);
        request.doc(sourceMap);
        restHighLevelClient.update(request);
    }
}
