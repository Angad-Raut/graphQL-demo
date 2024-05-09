package com.projectx.graphQL.demo.controller;

import com.projectx.graphQL.demo.dto.BookDto;
import com.projectx.graphQL.demo.dto.EntityIdDto;
import com.projectx.graphQL.demo.dto.ViewBooksDto;
import com.projectx.graphQL.demo.entity.Book;
import com.projectx.graphQL.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @MutationMapping
    public Book insertBook(@Argument BookDto bookDto) {
        return bookService.insertBook(bookDto);
    }

    @MutationMapping
    public Book updateBook(@Argument EntityIdDto entityIdDto,@Argument BookDto bookDto) {
        return bookService.updateBook(entityIdDto,bookDto);
    }

    @QueryMapping
    public Book findByBookId(@Argument EntityIdDto entityIdDto) {
        return bookService.findByBookId(entityIdDto);
    }

    @QueryMapping
    public List<ViewBooksDto> findAllBooks() {
        return bookService.findAllBooks();
    }
}
