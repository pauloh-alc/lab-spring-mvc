package com.paulo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paulo.error.BookNotFoundException;
import com.paulo.error.BookUnSupportedFieldPatchException;
import com.paulo.model.Book;
import com.paulo.repository.BookRepository;


@RestController
public class BookController {
	
	/**
	 *  Spring REST
	 *  
	 *  A REST controller to create the following REST API endpoints:
	 *  
	 *  HTTP method    |    URI     |   Description
	 *  
	 *    GET 			  /books   		List all books.
	 *    POST			  /books     	Save a book.
	 *    GET			  /books/{id}   Find a book where id = {:id}.
	 *    PUT			  /books/{id}   Update a book where id = {:id}, or save it.
	 *    PATCH			  /books/{id}   Update a single field where id = {:id}. 
	 *    DELETE		  /books/{id}   Delete a book where id = {:id}.
	 *    
	 */
	
	/* 1.Criando um enpoint para teste:
	 *
	@GetMapping("/books")
	public Book returnBook() {
		Book book = new Book();
		book.setId(1L);
		book.setName("Dom Quixote");
		book.setAuthor("Miguel de Cervantes");
		book.setPrice(new BigDecimal(100));
		
		return book;
	}
	 */
	
	@Autowired
	private BookRepository bookRepository;
	
	// Find
	@GetMapping("/books")
	public List<Book> findAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books;
	}
	
	// Save
	// return 201 instead of 200
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/books")
	public Book newBook(@RequestBody Book newBook) {
		Book bookEntity = bookRepository.save(newBook);
		return bookEntity;
	}
	
	// Find
	@GetMapping("/books/{id}")
	public Book findOne(@PathVariable("id") Long id) {
		Book bookEntity = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
		return bookEntity;
	}
	
	// Save or update
	@PutMapping("/books/{id}")
	public Book saveOrUpdate(@RequestBody Book newBook, @PathVariable("id") Long id) {
		Book bookEntity = bookRepository.findById(id)
		.map(x -> {
			x.setName(newBook.getName());
			x.setAuthor(newBook.getAuthor());
			x.setPrice(newBook.getPrice());
			return bookRepository.save(x);
		})
		.orElseGet(() -> {
			newBook.setId(id);
			return bookRepository.save(newBook);
		});
		return bookEntity;
	}
	
	@PatchMapping("/books/{id}")
	public Book patch(@RequestBody Map<String, String> update,  @PathVariable("id") Long id) {
		Book bookEntity = bookRepository.findById(id)
				.map(book -> {
					String author = update.get("author");
					
					if (author != "" && author != null) {
						book.setAuthor(author);
						return bookRepository.save(book);
					} else {
						throw new BookUnSupportedFieldPatchException(update.keySet());
					}
				})
				.orElseGet(() -> {
					throw new BookNotFoundException(id);
				});
		
		return bookEntity;
	}
	
	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
	}
}
