package epicode.u5d7hw.services;

import epicode.u5d7hw.entities.Author;
import epicode.u5d7hw.entities.Blogpost;
import epicode.u5d7hw.entities.BlogPostRequestPayload;
import epicode.u5d7hw.exceptions.NotFoundException;
import epicode.u5d7hw.repositories.AuthorRepository;
import epicode.u5d7hw.repositories.BlogpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogsService {
    @Autowired
    private BlogpostRepository blogpostRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Blogpost save(BlogPostRequestPayload payload) {
        Author author = authorRepository.findById(payload.getAuthorId()).orElseThrow(() -> new NotFoundException("Author not found"));

        Blogpost blogpost = new Blogpost();
        blogpost.setTitle(payload.getTitle());
        blogpost.setCategory(payload.getCategory());
        blogpost.setContent(payload.getContent());
        blogpost.setReadingTime(payload.getReadingTime());
        blogpost.setCover("https://picsum.photos/200/300");
        blogpost.setAuthor(author);

        return blogpostRepository.save(blogpost);
    }

    public Page<Blogpost> getBlogs(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return blogpostRepository.findAll(pageable);
    }

    public Blogpost findById(int id) {
        return blogpostRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Blogpost findByIdAndUpdate(int id, Blogpost updatedBlog) {
        Blogpost found = findById(id);
        found.setTitle(updatedBlog.getTitle());
        found.setCategory(updatedBlog.getCategory());
        found.setContent(updatedBlog.getContent());
        found.setReadingTime(updatedBlog.getReadingTime());
        found.setCover(updatedBlog.getCover());
        return blogpostRepository.save(found);
    }

    public void findByIdAndDelete(int id) {
        Blogpost found = findById(id);
        blogpostRepository.delete(found);
    }
}

