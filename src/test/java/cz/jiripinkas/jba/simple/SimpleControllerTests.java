package cz.jiripinkas.jba.simple;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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

}
