package htwberlin.Karteikarten.Service;

import htwberlin.Karteikarten.Persistence.FlashcardEntity;
import htwberlin.Karteikarten.Persistence.FlashcardRepository;
import htwberlin.Karteikarten.api.Flashcard;
import htwberlin.Karteikarten.api.FlashcardManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final FlashcardTransformer flashcardTransformer;

    public FlashcardService(FlashcardRepository flashcardRepository, FlashcardTransformer flashcardTransformer){
        this.flashcardRepository = flashcardRepository;
        this.flashcardTransformer = flashcardTransformer;
    }

    public List<Flashcard> findAll(){
        List<FlashcardEntity> flashcards = flashcardRepository.findAll();
        return flashcards.stream()
                .map(flashcardTransformer::transformEntity)
                .collect(Collectors.toList());
    }
    public Flashcard findById(Long id) {
        var flashcardEntity = flashcardRepository.findById(id);
        return flashcardEntity.map(flashcardTransformer::transformEntity).orElse(null);
    }
    public Flashcard create(FlashcardManipulationRequest request){
        var flashcardEntity = new FlashcardEntity(request.getQuestion(),request.getAnswer(),request.getCategory());
        flashcardEntity = flashcardRepository.save(flashcardEntity);
        return flashcardTransformer.transformEntity(flashcardEntity);
    }

    public Flashcard update(Long id, FlashcardManipulationRequest request) {
        var flashcardEntityOptional = flashcardRepository.findById(id);
        if (flashcardEntityOptional.isEmpty()) {
            return null;
        }

        var flashcardEntity = flashcardEntityOptional.get();
        flashcardEntity.setAnswer(request.getAnswer());
        flashcardEntity.setQuestion(request.getQuestion());
        flashcardEntity.setCategory(request.getCategory());

        flashcardEntity = flashcardRepository.save(flashcardEntity);

        return flashcardTransformer.transformEntity(flashcardEntity);
    }
    public boolean deleteById(Long id) {
        if (!flashcardRepository.existsById(id)) {
            return false;
        }

        flashcardRepository.deleteById(id);
        return true;
    }




}
