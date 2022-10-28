package com.paulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulo.model.Book;

//	This interface extends JpaRepository, it contains all the CRUD operations. 

// Spring Data magic :)
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
