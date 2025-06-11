package epicode.u5d7hw.controllers;

import epicode.u5d7hw.entities.Blogpost;
import epicode.u5d7hw.entities.BlogPostRequestPayload;
import epicode.u5d7hw.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    BlogsService blogsService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Blogpost saveBlog(@RequestBody BlogPostRequestPayload payload) {
        return blogsService.save(payload);
    }

    @GetMapping("")
    public Page<Blogpost> getBlogs(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return blogsService.getBlogs(page, size, sortBy);
    }

    @GetMapping("/{blogId}")
    public Blogpost findById(@PathVariable int blogId) {
        return blogsService.findById(blogId);
    }

    @PutMapping("/{blogId}")
    public Blogpost findAndUpdate(@PathVariable int blogId, @RequestBody Blogpost body) {
        return blogsService.findByIdAndUpdate(blogId, body);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int blogId) {
        blogsService.findByIdAndDelete(blogId);
    }
}