package com.projectx.graphQL.demo.service;

import com.projectx.graphQL.demo.dto.BookDto;
import com.projectx.graphQL.demo.dto.EntityIdDto;
import com.projectx.graphQL.demo.dto.ViewBooksDto;
import com.projectx.graphQL.demo.entity.Book;
import com.projectx.graphQL.demo.exceptions.BookAlreadyExistsException;
import com.projectx.graphQL.demo.exceptions.BookNotFoundException;
import com.projectx.graphQL.demo.repository.BookRepository;
import com.projectx.graphQL.demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book insertBook(BookDto bookDto) throws BookAlreadyExistsException {
        isBookExist(bookDto.getName());
        return bookRepository.save(Book.builder()
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .price(bookDto.getPrice())
                .publication(bookDto.getPublication())
                .bookStatus(true)
                .insertedTime(new Date())
                .updatedTime(new Date())
                .build());
    }

    @Override
    public Book findByBookId(EntityIdDto dto) throws BookNotFoundException {
        return bookRepository.getById(dto.getEntityId());
    }

    @Override
    public Book updateBook(EntityIdDto dto, BookDto bookDto) throws BookAlreadyExistsException, BookNotFoundException {
        Book book = bookRepository.getById(dto.getEntityId());
        if (book==null) {
            throw new BookAlreadyExistsException(Constants.BOOK_NOT_FOUND);
        } else {
            if (!bookDto.getName().equals(book.getName())) {
                book.setName(bookDto.getName());
            }
            if (!bookDto.getAuthor().equals(book.getAuthor())) {
                book.setAuthor(bookDto.getAuthor());
            }
            if (!bookDto.getPublication().equals(book.getPublication())) {
                book.setPublication(bookDto.getPublication());
            }
            if (!bookDto.getPrice().equals(book.getPrice())) {
                book.setPrice(bookDto.getPrice());
            }
            book.setUpdatedTime(new Date());
            return bookRepository.save(book);
        }
    }

    @Override
    public List<ViewBooksDto> findAllBooks() {
        List<Book> fetchList = bookRepository.findAll();
        AtomicInteger index = new AtomicInteger(0);
        return !fetchList.isEmpty()?fetchList.stream()
                .map(data -> ViewBooksDto.builder()
                        .srNo(index.incrementAndGet())
                        .bookId(data.getId())
                        .name(data.getName())
                        .author(data.getAuthor())
                        .publication(data.getPublication())
                        .price(data.getPrice())
                        .bookStatus(Constants.toStatus(data.getBookStatus()))
                        .build())
                .collect(Collectors.toList()) : new ArrayList<>();
    }
    private void isBookExist(String bookName) {
        if (bookRepository.existsByName(bookName)) {
            throw new BookAlreadyExistsException(Constants.BOOK_EXISTS);
        }
    }
}
