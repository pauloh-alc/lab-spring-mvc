package com.paulo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.paulo.model.Book;
import com.paulo.repository.BookRepository;

@SpringBootApplication
public class StartBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartBookApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initDataBase(BookRepository bookRepository) {
		
		return args -> {
			
			Book b1 = new Book("Dom Quixote", "Miguel de Cervantes", new BigDecimal(212.99));
			Book b2 = new Book("O Principe", "Maquiavel", new BigDecimal(45.85));
			Book b3 = new Book("Iliada", "Homero", new BigDecimal(78.85));
			Book b4 = new Book("Odisseia", "Homero", new BigDecimal(95.85));
			
			List<Book> books = Arrays.asList(b1,b2,b3,b4);
			bookRepository.saveAll(books);
	
		};
	}
}
