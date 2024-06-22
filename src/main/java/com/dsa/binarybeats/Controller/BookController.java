package com.dsa.binarybeats.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.binarybeats.Entity.Books;
import com.dsa.binarybeats.Exceptions.BookException;
import com.dsa.binarybeats.Interface.IBookService;
import com.dsa.binarybeats.Request.BookRequest;
import com.dsa.binarybeats.Response.BookResponse;

@RestController
@RequestMapping("/api/book")
public class BookController {

     @Autowired
    IBookService bookService;
    
    @PostMapping("/add")
    public ResponseEntity<Books> addBook(@RequestBody BookRequest bookRequest) throws BookException{
        Books book = bookService.addBook(bookRequest);
        if(book!=null){
            return new ResponseEntity<Books>(book,HttpStatus.CREATED);
        }
        else{
            throw new BookException("Error adding a new book");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam Long id) throws BookException {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Books> updateBook(@RequestBody BookRequest bookRequest,@PathVariable Long id) throws BookException{
        Books book = bookService.updateBook(id, bookRequest);
        if(book!=null){
            return new ResponseEntity<Books>(book,HttpStatus.ACCEPTED);
        }
        else{
            throw new BookException("Error updating book");
        }
    }

    @PostMapping("/addMultiple")
    public ResponseEntity<List<Books>> addMultipleBook(@RequestBody List<BookRequest> codeRequests) {
        List<Books> codes = bookService.addMultipleBook(codeRequests);
        return new ResponseEntity<>(codes,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Page<List<BookResponse>>> findCodessHandler(@RequestParam Integer page_no, @RequestParam Integer paage_size) {
        Page<List<BookResponse>> books = bookService.get_all_Book(page_no, paage_size);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponse> findBookHandler(@PathVariable long id) throws BookException {
        BookResponse books = bookService.getBook(id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
}
