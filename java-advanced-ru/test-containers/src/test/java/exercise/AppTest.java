package exercise;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("hexlet")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");
    // BEGIN
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonRepository personRepository;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }

    @Test
    void testIndex() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/people"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        String body = response.getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    void testShow() throws Exception {
        Person testPerson = new Person();
        testPerson.setId(1);
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        personRepository.save(testPerson);
        MockHttpServletResponse response = mockMvc
                .perform(get("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("id", "firstName", "lastName");
        assertThat(response.getContentAsString()).contains("John");
        assertThat(response.getContentAsString()).contains("Doe");
    }

    @Test
    void testUpdate() throws Exception {
        Person testPerson = new Person();
        testPerson.setId(1);
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        personRepository.save(testPerson);
        MockHttpServletResponse response = mockMvc
                .perform(patch("/people/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);

        MockHttpServletResponse responseGet = mockMvc
                .perform(get("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(responseGet.getStatus()).isEqualTo(200);
        assertThat(responseGet.getContentAsString()).contains("Jackson", "Bind");
    }

    @Test
    void testDelete() throws Exception {
        Person testPerson = new Person();
        testPerson.setId(1);
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        personRepository.save(testPerson);
        MockHttpServletResponse response = mockMvc
                .perform(delete("/people/1"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(personRepository.findById(1L)).isEqualTo(null);
    }
    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
                .perform(
                        post("/people")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
                )
                .andReturn()
                .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
