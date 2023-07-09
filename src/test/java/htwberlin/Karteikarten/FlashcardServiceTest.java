package htwberlin.Karteikarten;

import htwberlin.Karteikarten.Persistence.FlashcardEntity;
import htwberlin.Karteikarten.Persistence.FlashcardRepository;
import htwberlin.Karteikarten.Service.FlashcardService;
import htwberlin.Karteikarten.api.Flashcard;
import htwberlin.Karteikarten.api.FlashcardManipulationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FlashcardServiceTest {

    @Autowired
    private FlashcardService flashcardService;
    @MockBean
    private FlashcardRepository flashcardRepository;


    @Test
    void findAll_ReturnsListOfFlashcards() {

        FlashcardEntity flashcardEntity1 = new FlashcardEntity("Question1", "Answer1", "Category1");
        FlashcardEntity flashcardEntity2 = new FlashcardEntity("Question2", "Answer2", "Category2");
        when(flashcardRepository.findAll()).thenReturn(List.of(flashcardEntity1, flashcardEntity2));
        List<Flashcard> flashcards = flashcardService.findAll();

        assertEquals(2, flashcards.size());

    }
    @Test
    void create_ReturnsCreatedFlashcard() {
        // Mocken des Repository-Verhaltens
        when(flashcardRepository.save(any(FlashcardEntity.class))).thenAnswer(invocation -> {
            FlashcardEntity savedEntity = invocation.getArgument(0);
            savedEntity.setId(1L); // Setzen einer Test-ID
            return savedEntity;
        });

        // Test-FlashcardManipulationRequest-Objekt erstellen
        FlashcardManipulationRequest request = new FlashcardManipulationRequest("Question", "Answer", "Category");

        // Methode create aufrufen
        Flashcard createdFlashcard = flashcardService.create(request);

        // Überprüfung, ob die zurückgegebene Flashcard den erwarteten Werten entspricht
        assertNotNull(createdFlashcard.getId());
        assertEquals(request.getQuestion(), createdFlashcard.getQuestion());
        assertEquals(request.getAnswer(), createdFlashcard.getAnswer());
        assertEquals(request.getCategory(), createdFlashcard.getCategory());

        // Überprüfung, ob die save-Methode des Repositories genau einmal aufgerufen wurde
        verify(flashcardRepository, times(1)).save(any(FlashcardEntity.class));
    }

    @Test
    void update_ReturnsUpdatedFlashcard() {
        // Testdaten
        Long id = 1L;
        FlashcardManipulationRequest request = new FlashcardManipulationRequest("Updated Question", "Updated Answer", "Updated Category");
        FlashcardEntity existingFlashcardEntity = new FlashcardEntity("Question", "Answer", "Category");

        // Mocken des Repository-Verhaltens, um das vorhandene FlashcardEntity zurückzugeben
        when(flashcardRepository.findById(id)).thenReturn(Optional.of(existingFlashcardEntity));
        when(flashcardRepository.save(any(FlashcardEntity.class))).thenReturn(existingFlashcardEntity);

        // Aufruf der Methode zum Aktualisieren der Flashcard
        Flashcard updatedFlashcard = flashcardService.update(id, request);

        // Überprüfung, ob die zurückgegebene Flashcard nicht null ist
        assertNotNull(updatedFlashcard);

        // Überprüfung, ob die Eigenschaften der aktualisierten Flashcard den erwarteten Werten entsprechen

        assertEquals(request.getQuestion(), updatedFlashcard.getQuestion());
        assertEquals(request.getAnswer(), updatedFlashcard.getAnswer());
        assertEquals(request.getCategory(), updatedFlashcard.getCategory());
    }

    @Test
    void deleteById_ReturnsTrueIfDeleted() {
        // Testdaten
        Long id = 1L;
        FlashcardEntity existingFlashcardEntity = new FlashcardEntity("Question", "Answer", "Category");

        // Mocken des Repository-Verhaltens, um das vorhandene FlashcardEntity zurückzugeben
        when(flashcardRepository.existsById(id)).thenReturn(true);

        // Aufruf der Methode zum Löschen der Flashcard
        boolean deleted = flashcardService.deleteById(id);

        // Überprüfung, ob die Rückgabewert true ist, wenn die Löschung erfolgreich war
        assertTrue(deleted);

        // Überprüfung, ob die deleteById-Methode des Repositories mit der richtigen ID aufgerufen wurde
        verify(flashcardRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteById_ReturnsFalseIfNotDeleted() {
        // Testdaten
        Long id = 1L;

        // Mocken des Repository-Verhaltens, um zu simulieren, dass die Flashcard nicht existiert
        when(flashcardRepository.existsById(id)).thenReturn(false);

        // Aufruf der Methode zum Löschen der Flashcard
        boolean deleted = flashcardService.deleteById(id);

        // Überprüfung, ob die Rückgabewert false ist, wenn die Flashcard nicht existiert und somit nicht gelöscht werden kann
        assertFalse(deleted);

        // Überprüfung, ob die deleteById-Methode des Repositories nicht aufgerufen wurde, da die Flashcard nicht existiert
        verify(flashcardRepository, never()).deleteById(id);
    }





}