package cz.jiripinkas.jba.service.initdb;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Category;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.repository.BlogRepository;
import cz.jiripinkas.jba.repository.CategoryRepository;
import cz.jiripinkas.jba.repository.UserRepository;

@Transactional
@Service
public class FakeInitDbService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void initEnvironment() {

        User userAdmin = userRepository.findByName("admin");

        if (userAdmin != null) {
            Category fakeCategory = new Category();
            fakeCategory.setName("Fakes cat");
            fakeCategory.setShortName("fakes");
            fakeCategory = categoryRepository.save(fakeCategory);

            Blog blogEmpty = new Blog();
            blogEmpty.setName("My Fake Blog");
            blogEmpty.setUrl("https://empty-my");
            blogEmpty.setHomepageUrl("https://empty-my");
            blogEmpty.setShortName("empty-my");
            blogEmpty.setUser(userAdmin);
            blogEmpty.setCategory(fakeCategory);
            blogRepository.save(blogEmpty);
        }
    }
}
