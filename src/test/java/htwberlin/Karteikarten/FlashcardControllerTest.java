package htwberlin.Karteikarten;

import com.fasterxml.jackson.databind.ObjectMapper;
import htwberlin.Karteikarten.Service.FlashcardService;
import htwberlin.Karteikarten.api.Flashcard;
import htwberlin.Karteikarten.api.FlashcardManipulationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FlashcardControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlashcardService flashcardService;

    @InjectMocks
    private FlashcardController flashcardController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flashcardController).build();
    }

    @Test
    public void testFetchFlashcards() throws Exception {
        Flashcard flashcard1 = new Flashcard(1L, "Question 1", "Answer 1", "Category1");
        Flashcard flashcard2 = new Flashcard(2L, "Question 2", "Answer 2", "Category2");
        List<Flashcard> flashcards = Arrays.asList(flashcard1, flashcard2);

        when(flashcardService.findAll()).thenReturn(flashcards);

        mockMvc.perform(get("/api/v1/flashcards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].question").value("Question 1"))
                .andExpect(jsonPath("$[0].answer").value("Answer 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].question").value("Question 2"))
                .andExpect(jsonPath("$[1].answer").value("Answer 2"));
    }

    @Test
    public void testFetchFlashcardById() throws Exception {
        Flashcard flashcard = new Flashcard(1L, "Question", "Answer", "Category1");

        when(flashcardService.findById(anyLong())).thenReturn(flashcard);

        mockMvc.perform(get("/api/v1/flashcards/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.question").value("Question"))
                .andExpect(jsonPath("$.answer").value("Answer"));
    }

    /**

    @Test
    public void testCreateFlashcard() throws Exception {
        FlashcardManipulationRequest request = new FlashcardManipulationRequest("Question", "Answer", "Category");

        mockMvc.perform(post("/api/v1/flashcards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated());
    }

     */

    @Test
    public void testUpdateFlashcard() throws Exception {
        FlashcardManipulationRequest request = new FlashcardManipulationRequest("Updated Question", "Updated Answer", "Updated Category");
        Flashcard updatedFlashcard = new Flashcard(1L, "Updated Question", "Updated Answer","Updated Category");

        when(flashcardService.update(anyLong(), any(FlashcardManipulationRequest.class))).thenReturn(updatedFlashcard);

        mockMvc.perform(put("/api/v1/flashcards/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.question").value("Updated Question"))
                .andExpect(jsonPath("$.answer").value("Updated Answer"));
    }

    @Test
    public void testDeleteFlashcard() throws Exception {
        when(flashcardService.deleteById(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/flashcards/1"))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
