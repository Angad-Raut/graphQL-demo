package com.projectx.graphQL.demo.repository;

import com.projectx.graphQL.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "select * from book_details where id=:bookId",nativeQuery = true)
    Book getById(@Param("bookId")Long bookId);
    Boolean existsByName(String bookName);
}
