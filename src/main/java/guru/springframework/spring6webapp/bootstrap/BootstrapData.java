package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author John = new Author();
        John.setFirstName("John");
        John.setLastName("Smith");

        Book SFG = new Book();
        SFG.setTitle("Spring Framework Guru");
        SFG.setIsbn("123");

        Author JohnSaved = authorRepository.save(John);
        Book SFGSaved = bookRepository.save(SFG);

        authorRepository.save(JohnSaved);

        JohnSaved.getBooks().add(SFGSaved);

        System.out.println("In bootstrapping data");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());

    }
}
