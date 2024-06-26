package com.dsa.binarybeats.Interface;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dsa.binarybeats.Entity.Books;
import com.dsa.binarybeats.Exceptions.BookException;
import com.dsa.binarybeats.Request.BookRequest;
import com.dsa.binarybeats.Response.BookResponse;


public interface IBookService {

    


    Books addBook(BookRequest bookRequest) throws BookException;

    void deleteBook(Long bookId) throws BookException;

    Books updateBook(Long bookId, BookRequest bookRequest) throws BookException;

    List<Books> addMultipleBook(List<BookRequest> bookRequests);

    Page<List<BookResponse>> get_all_Book(Integer page_no, Integer paage_size);

    BookResponse getBook(Long bookId) throws BookException;

    Books getBook2(Long bookId);
    
}
