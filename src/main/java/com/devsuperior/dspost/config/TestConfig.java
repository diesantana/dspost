package com.devsuperior.dspost.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devsuperior.dspost.models.embedded.Author;
import com.devsuperior.dspost.models.embedded.Comment;
import com.devsuperior.dspost.models.entities.Post;
import com.devsuperior.dspost.models.entities.User;
import com.devsuperior.dspost.repositories.PostRepository;
import com.devsuperior.dspost.repositories.UserRepository;

import jakarta.annotation.PostConstruct;
/*
 *  A classe TestConfig é uma classe de configuração do Spring utilizada para inicializar dados de teste em um ambiente controlado. 
 *  Esta configuração é específica para o perfil "test", 
 *  permitindo que o ambiente de testes seja configurado de maneira isolada dos demais ambientes.
 */
@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@PostConstruct // Indica que este método será executado ao iniciar a aplicação
	public void init() {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		// Cria três usuários fictícios.
		User maria = new User(null, "Maria Brow", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	
		Post post1 = new Post(null, Instant.parse("2021-02-13T11:15:01Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new Author(maria));
		Post post2 = new Post(null, Instant.parse("2021-02-14T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(maria));
		
		Comment c1 = new Comment("Boa viagem Maria!", Instant.parse("2021-02-13T14:30:01Z"), new Author(alex));
		Comment c2 = new Comment("Aproveite", Instant.parse("2021-02-13T15:38:05Z"), new Author(bob));
		Comment c3 = new Comment("Tenha um ótimo dia!", Instant.parse("2021-02-14T12:34:26Z"), new Author(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
