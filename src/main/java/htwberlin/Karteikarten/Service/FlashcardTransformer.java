package htwberlin.Karteikarten.Service;

import htwberlin.Karteikarten.Persistence.FlashcardEntity;
import htwberlin.Karteikarten.api.Flashcard;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class FlashcardTransformer {
    public Flashcard transformEntity(FlashcardEntity flashcardEntity) {
        Long id = flashcardEntity.getId() != null ? flashcardEntity.getId() : generateRandomId();
        return new Flashcard(
                id,
                flashcardEntity.getQuestion(),
                flashcardEntity.getAnswer(),
                flashcardEntity.getCategory());
    }

    private Long generateRandomId() {
        return ThreadLocalRandom.current().nextLong(1, 10_000_000_001L);
    }

}
