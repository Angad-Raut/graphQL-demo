package com.projectx.graphQL.demo.utils;

import com.projectx.graphQL.demo.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static String BOOK_EXISTS="Book already exists in the system!!";
    public static String BOOK_NOT_FOUND="Book is not present in system!!";

    public static String toStatus(Boolean status) {
        return status?"Enabled":"Disabled";
    }
}
