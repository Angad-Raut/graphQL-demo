package com.projectx.graphQL.demo.service;

import com.projectx.graphQL.demo.dto.BookDto;
import com.projectx.graphQL.demo.dto.EntityIdDto;
import com.projectx.graphQL.demo.dto.ViewBooksDto;
import com.projectx.graphQL.demo.entity.Book;
import com.projectx.graphQL.demo.exceptions.BookAlreadyExistsException;
import com.projectx.graphQL.demo.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {
    Book insertBook(BookDto bookDto)throws BookAlreadyExistsException;
    Book findByBookId(EntityIdDto dto)throws BookNotFoundException;
    Book updateBook(EntityIdDto dto,BookDto bookDto)throws BookAlreadyExistsException,BookNotFoundException;
    List<ViewBooksDto> findAllBooks();
}
