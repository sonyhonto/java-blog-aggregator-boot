package cz.jiripinkas.jba.simple;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SimpleControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void shoudReturnOkOnEmptyEndpoint() throws Exception {
        this.mvc.perform(get("/simple/empty"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkWhenAllIsRequested() throws Exception {
        this.mvc.perform(get("/simple/all"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkWhenEntityIsCreated() throws Exception {
        this.mvc.perform(post("/simple/create")
                .contentType("application/json")
                .content("""
                        {
                            "name": "john",
                            "title": "home"
                        }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    void shouldReturnCreatedWhenDataIsSaved() throws Exception {

        this.mvc.perform(post("/simple/post")
                .contentType("application/json")
                .content("""
                        {
                            "name": "john",
                            "title": "home"
                        }
                                """))
                .andExpect(status().isCreated());

    }

    @Test
    @DirtiesContext
    void shouldPerformGetWhenDataIsSaved() throws Exception {

        String location = this.mvc.perform(post("/simple/post")
                .contentType("application/json")
                .content("""
                        {
                            "name": "john",
                            "title": "home"
                        }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn().getResponse().getHeader("Location");

        this.mvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("john"))
                .andExpect(jsonPath("$.title").value("home"));

    }

    @Test
    @DirtiesContext
    void shouldPerformDeleteWhenDataIsSaved() throws Exception {

        String location = this.mvc.perform(post("/simple/post")
                .contentType("application/json")
                .content("""
                        {
                            "name": "john",
                            "title": "home"
                        }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn().getResponse().getHeader("Location");

        String content = this.mvc.perform(get(location))
                .andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.read(content, "$.id");
        this.mvc.perform(delete("/simple/delete/{id}", id)
                .contentType("application/json"))
                .andExpect(status().isNoContent());

    }

}
