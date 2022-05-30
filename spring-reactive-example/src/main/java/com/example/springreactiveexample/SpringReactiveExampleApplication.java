package com.example.springreactiveexample;

import com.example.springreactiveexample.model.Post;
import com.example.springreactiveexample.repository.PostRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringReactiveExampleApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringReactiveExampleApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveExampleApplication.class, args);
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }

    @Bean
    public CommandLineRunner demo(PostRepository repository) {
        List<Post> posts = new ArrayList<>();
        int number_of_entries = 1000;
        for (int i = 1; i <= number_of_entries; i++) {
            posts.add(new Post("Post" + i, "This is post" + i + "."));
        }
        return (args) -> {
            // save a few posts
            repository.saveAll(posts)
                    .blockLast(Duration.ofSeconds(10));
        };
    }
}
