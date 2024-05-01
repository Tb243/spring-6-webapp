package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author John = new Author();
        John.setFirstName("John");
        John.setLastName("Smith");
        Author Jane = new Author();
        Jane.setFirstName("Jane");
        Jane.setLastName("Doe");

        Book SFG = new Book();
        SFG.setTitle("Spring Framework Guru");
        SFG.setIsbn("123");
        Book JAVBOOK = new Book();
        JAVBOOK.setTitle("Java Book");
        JAVBOOK.setIsbn("456");

        Publisher ABC = new Publisher();
        ABC.setPublisherName("ABC Books");
        ABC.setAddress("123 Street");
        ABC.setCity("ACITY");
        ABC.setPostcode("1234");
        ABC.setState("VIC");

        Author JohnSaved = authorRepository.save(John);
        Book SFGSaved = bookRepository.save(SFG);
        Publisher ABCSaved = publisherRepository.save(ABC);
        Author JaneSaved = authorRepository.save(Jane);
        Book JAVBOOKSaved = bookRepository.save(JAVBOOK);

        //Books class has the Join table, so only the books needed instead of both-way
        //JohnSaved.getBooks().add(SFGSaved);
        //JaneSaved.getBooks().add(JAVBOOKSaved);
        SFGSaved.getAuthors().add(JohnSaved);
        JAVBOOKSaved.getAuthors().add(JaneSaved);

        SFGSaved.setPublisher(ABCSaved);
        JAVBOOKSaved.setPublisher(ABCSaved);

        authorRepository.save(JohnSaved);
        authorRepository.save(JaneSaved);
        bookRepository.save(SFGSaved);
        bookRepository.save(JAVBOOKSaved);

        System.out.println("In bootstrapping data");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());

    }
}
