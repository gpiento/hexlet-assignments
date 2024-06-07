package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        MvcResult result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        ResultActions result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
        String body = result.andReturn().getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public void testCreate() throws Exception {
        String title = faker.lorem().sentence(4);
        String description = faker.lorem().paragraph();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated());

        assertThat(taskRepository.findByTitle(title).get().getTitle()).isEqualTo(title);
        assertThat(taskRepository.findByTitle(title).get().getDescription()).isEqualTo(description);
    }

    @Test
    public void testUpdate() throws Exception {
        String title = faker.lorem().sentence(4);
        String description = faker.lorem().paragraph();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        long id = taskRepository.save(task).getId();

        String titleUpdate = faker.lorem().sentence(4);
        String descriptionUpdate = faker.lorem().paragraph();
        Task taskUpdate = new Task();
        taskUpdate.setTitle(titleUpdate);
        taskUpdate.setDescription(descriptionUpdate);

        mockMvc.perform(put("/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(taskUpdate)))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(id).get().getTitle()).isEqualTo(titleUpdate);
        assertThat(taskRepository.findById(id).get().getDescription()).isEqualTo(descriptionUpdate);
    }

    @Test
    public void testDelete() throws Exception {
        String title = faker.lorem().sentence(4);
        String description = faker.lorem().paragraph();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        long id = taskRepository.save(task).getId();

        MvcResult result = mockMvc.perform(delete("/tasks/" + id))
                .andExpect(status().isOk()).andReturn();

        assertThat(taskRepository.findById(id)).isEmpty();
    }
    // END
}