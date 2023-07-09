
<template>
  <div>
    <!-- Button zum Öffnen des Modals -->
    <button type="button" class="btn btn-primary" @click="openModal">Update</button>

    <!-- Modal -->
    <div class="modal" tabindex="-1" role="dialog" v-if="showModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Update Flashcard</h5>
            <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <!-- Flashcard-Inhalte und Formularfelder hier... -->
            <div class="mb-3">
              <label for="question" class="form-label">Question</label>
              <input type="text" class="form-control" id="question" v-model="updatedFlashcard.question">
            </div>
            <div class="mb-3">
              <label for="answer" class="form-label">Answer</label>
              <input type="text" class="form-control" id="answer" v-model="updatedFlashcard.answer">
            </div>
            <div class="mb-3">
              <label for="category" class="form-label">Category</label>
              <input type="text" class="form-control" id="category" v-model="updatedFlashcard.category">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="closeModal">Close</button>
            <button type="button" class="btn btn-primary" @click="updateFlashcard">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EditFlashcardModal',
  data() {
    return {
      showModal: false,
      updatedFlashcard: {
        question: '',
        answer: '',
        category: ''
      }
    }
  },
  methods: {
    openModal() {
      // Öffne das Modal
      this.showModal = true;

      // Setze die aktualisierten Flashcard-Daten basierend auf der ausgewählten Karteikarte
      this.updatedFlashcard = {
        question: this.flashcard.question,
        answer: this.flashcard.answer,
        category: this.flashcard.category
      };
    },
    closeModal() {
      // Schließe das Modal
      this.showModal = false;
    },
    updateFlashcard() {
      // Aktualisiere die Flashcard-Daten basierend auf den aktualisierten Werten
      // ...

      // Schließe das Modal
      this.showModal = false;
    }
  },
  props: {
    flashcard: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
/* CSS-Styling des Modals hier... */
</style>
