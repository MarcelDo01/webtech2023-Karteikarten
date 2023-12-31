package htwberlin.Karteikarten;

import htwberlin.Karteikarten.Service.FlashcardService;
import htwberlin.Karteikarten.api.Flashcard;
import htwberlin.Karteikarten.api.FlashcardManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class FlashcardController {


    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping(path = "/api/v1/flashcards")
    public ResponseEntity<List<Flashcard>> fetchFlashcards(){
        return ResponseEntity.ok(flashcardService.findAll());
    }
    @GetMapping(path = "/api/v1/flashcards/{id}")
    public ResponseEntity<Flashcard> fetchFlashcardById(@PathVariable Long id) {
        var flashcard = flashcardService.findById(id);
        return flashcard != null ? ResponseEntity.ok(flashcard) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/flashcards")
    public ResponseEntity<Void> createFlashcard(@RequestBody FlashcardManipulationRequest request) throws URISyntaxException {
        var flashcard = flashcardService.create(request);
        URI uri = new URI("/api/v1/flashcards/" + flashcard.getId());
        return ResponseEntity.created(uri).build();

    }
    @PutMapping(path = "/api/v1/flashcards/{id}")
    public ResponseEntity<Flashcard> updateFlashcard(@PathVariable Long id, @RequestBody FlashcardManipulationRequest request) {
        var flashcard = flashcardService.update(id, request);
        return flashcard != null? ResponseEntity.ok(flashcard) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/flashcards/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long id) {
        boolean successful = flashcardService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
