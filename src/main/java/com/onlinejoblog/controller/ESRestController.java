package com.onlinejoblog.controller;

import com.onlinejoblog.model.Book;
import com.onlinejoblog.repository.BookRepository;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pankaj on 08,2018
 */
@RestController
public class ESRestController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/start_es")
    public String pushData() {
        Book book = new Book();
        book.setAuthor("Pankaj");
        book.setId("100");
        book.setTitle("Java Spring boot");
        Map<String, Object> map = new HashMap<>();
        map.put("hawkBook", book);
        try {
            bookRepository.saveDocument(book, "12");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
