package com.dsa.binarybeats.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dsa.binarybeats.Entity.Books;
import com.dsa.binarybeats.Exceptions.BookException;
import com.dsa.binarybeats.Interface.IBookService;
import com.dsa.binarybeats.Repository.BookRepo;
import com.dsa.binarybeats.Request.BookRequest;
import com.dsa.binarybeats.Response.BookResponse;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public Books addBook(BookRequest bookRequest) throws BookException {
        Books book = new Books();
        book.setAuthor(bookRequest.getAuthor());
        book.setDiscountedPercent(bookRequest.getDiscountedPercent());
        book.setDiscountedPrice(bookRequest.getDiscountedPrice());
        book.setImage_URL(bookRequest.getImage_URL());
        book.setPrice(bookRequest.getPrice());
        book.setQuantity(bookRequest.getQuantity());
        book.setTitle(bookRequest.getTitle());

        return bookRepo.save(book);

    }

    @Override
    public void deleteBook(long bookId) throws BookException {
        Optional<Books> book = bookRepo.findById(bookId);
        if (book.isEmpty()) {
            throw new BookException("Book not found");
        }
        bookRepo.delete(book.get());
    }

    @Override
    public Books updateBook(long bookId, BookRequest bookRequest) throws BookException {
        Optional<Books> isbook = bookRepo.findById(bookId);
        if (isbook.isEmpty()) {
            throw new BookException("Book not found");
        }
        Books book = isbook.get();
        book.setAuthor(bookRequest.getAuthor());
        book.setDiscountedPercent(bookRequest.getDiscountedPercent());
        book.setDiscountedPrice(bookRequest.getDiscountedPrice());
        book.setImage_URL(bookRequest.getImage_URL());
        book.setPrice(bookRequest.getPrice());
        book.setQuantity(bookRequest.getQuantity());
        book.setTitle(bookRequest.getTitle());

        return bookRepo.save(book);

    }

    @Override
    public List<Books> addMultipleBook(List<BookRequest> BookRequest) {
        List<Books> books = new ArrayList<>();
        for (BookRequest bookRequest : BookRequest) {

            Books book = new Books();
            book.setAuthor(bookRequest.getAuthor());
            book.setDiscountedPercent(bookRequest.getDiscountedPercent());
            book.setDiscountedPrice(bookRequest.getDiscountedPrice());
            book.setImage_URL(bookRequest.getImage_URL());
            book.setPrice(bookRequest.getPrice());
            book.setQuantity(bookRequest.getQuantity());
            book.setTitle(bookRequest.getTitle());
            books.add(book);
        }
        return bookRepo.saveAll(books);
    }

    @Override
    public Page<List<BookResponse>> get_all_Book(Integer page_no, Integer paage_size) {
         Pageable pageable = PageRequest.of(page_no, paage_size);
    List<Books> books = bookRepo.findAll();
    int startIdx = (int) pageable.getOffset();
    int endIdx = Math.min(startIdx + pageable.getPageSize(), books.size());
    List<Books> pageContent = books.subList(startIdx, endIdx);
    Page<Books> filteredBooks = new PageImpl<>(pageContent, pageable, books.size());
    
    List<List<BookResponse>> bookResponsesList = new ArrayList<>();
    for (Books book : filteredBooks.getContent()) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setDiscountedPercent(book.getDiscountedPercent());
        bookResponse.setDiscountedPrice(book.getDiscountedPrice());
        bookResponse.setImage_URL(book.getImage_URL());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setQuantity(book.getQuantity());
        bookResponse.setTitle(book.getTitle());
        
        List<BookResponse> singleResponseList = new ArrayList<>();
        singleResponseList.add(bookResponse);
        bookResponsesList.add(singleResponseList);
    }
    
    return new PageImpl<>(bookResponsesList, pageable, books.size());
    }

}
