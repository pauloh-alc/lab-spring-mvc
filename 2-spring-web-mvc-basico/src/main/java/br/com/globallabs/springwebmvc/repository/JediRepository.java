package br.com.globallabs.springwebmvc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.globallabs.springwebmvc.model.Jedi;

@Repository
public class JediRepository {
	
	private List<Jedi> jedi;
	
	public JediRepository() {
		this.jedi = new ArrayList<>();
	}
	
	public List<Jedi> getAllJedi() {
		return jedi;
	}
	
	public void add(final Jedi jedi) {
		this.jedi.add(jedi);
	}
}
