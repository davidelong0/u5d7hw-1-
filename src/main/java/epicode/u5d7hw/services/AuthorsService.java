package epicode.u5d7hw.services;

import epicode.u5d7hw.entities.Author;
import epicode.u5d7hw.exceptions.NotFoundException;
import epicode.u5d7hw.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        author.setAvatar("https://ui-avatars.com/api/?name=" + author.getName() + "+" + author.getSurname());
        return authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author findById(int id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Author found = findById(id);
        authorRepository.delete(found);
    }

    public Author findByIdAndUpdate(int id, Author author) {
        Author found = findById(id);
        found.setName(author.getName());
        found.setSurname(author.getSurname());
        found.setEmail(author.getEmail());
        found.setDateOfBirth(author.getDateOfBirth());
        found.setAvatar("https://ui-avatars.com/api/?name=" + author.getName() + "+" + author.getSurname());
        return authorRepository.save(found);
    }
}
