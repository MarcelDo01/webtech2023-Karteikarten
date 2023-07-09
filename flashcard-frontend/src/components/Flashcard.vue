<template>
  <div class="card card-outline-dark h-100" @click="toggleAnswer">
    <div class="card-body">
      <h5 class="card-title">Frage: {{ flashcard.question }}</h5>
      <p v-if="showAnswer" class="card-text">Antwort: {{ flashcard.answer }}</p>
      <div class="buttons">
        <button class="btn btn-danger" @click="deleteFlashcard" style="margin: 5px">Delete</button>
        <button class="btn btn-primary" @click="openModal">Update</button>
      </div>
    </div>
  </div>
  <br>

  <!-- Modal -->
  <div class="modal" tabindex="-1" role="dialog" v-if="showModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Update Flashcard</h5>
          <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="questionInput" class="form-label">Question</label>
              <span class="input-group-text" style="margin-right: 20px;">:</span>
              <input type="text" class="form-control input-field" id="questionInput" v-model="updatedQuestion">

            </div>
            <div class="mb-3">
              <label for="answerInput" class="form-label">Answer</label>
              <span class="input-group-text" style="margin-right: 31px;">:</span>
              <input type="text" class="form-control input-field" id="answerInput" v-model="updatedAnswer">
            </div>
          </form>
        </div>
        <div class="modal-footer justify-content-end">
          <button type="button" class="btn btn-secondary" @click="closeModal">Close</button>
          <button type="button" class="btn btn-primary" @click="updateFlashcard">Save Changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Flashcard',
  props: {
    flashcard: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      showAnswer: false,
      showModal: false,
      updatedQuestion: '',
      updatedAnswer: ''
    };
  },
  methods: {
    toggleAnswer() {
      this.showAnswer = !this.showAnswer;
    },
    deleteFlashcard() {
      const flashcardId = this.flashcard.id;
      const endpoint = `http://localhost:8080/api/v1/flashcards/${flashcardId}`;
      const requestOptions = {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        }
      };

      fetch(endpoint, requestOptions)
        .then(response => {
          if (response.ok) {
            console.log('Karteikarte erfolgreich gelöscht');
            this.$emit('cardDeleted', flashcardId);
          } else {
            console.error('Fehler beim Löschen der Karteikarte');
          }
        })
        .catch(error => {
          console.error('Fehler beim Löschen der Karteikarte', error);
        }); location.reload();
    },

    openModal() {
      this.showModal = true;
      this.updatedQuestion = this.flashcard.question;
      this.updatedAnswer = this.flashcard.answer;
    },
    closeModal() {
      this.showModal = false;
    },
    updateFlashcard() {
      const flashcardId = this.flashcard.id;
      const endpoint = `http://localhost:8080/api/v1/flashcards/${flashcardId}`;
      const updatedFlashcard = {
        question: this.updatedQuestion,
        answer: this.updatedAnswer
      };
      const requestOptions = {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedFlashcard)
      };

      fetch(endpoint, requestOptions)
        .then(response => {
          if (response.ok) {
            console.log('Karteikarte erfolgreich aktualisiert');
            // Führe hier weitere Aktionen nach dem Aktualisieren aus
          } else {
            console.error('Fehler beim Aktualisieren der Karteikarte');
          }
        })
        .catch(error => {
          console.error('Fehler beim Aktualisieren der Karteikarte', error);
        });

      this.closeModal();
      location.reload();
    }
  }
}
</script>

<style scoped>
.card {
  border: 2px solid black;
  padding: 20px 35px ;
  cursor: pointer;
  background-color: lightgray;
  width: 1300px;
}

.card-title {
  margin-bottom: 20px;
}

.card-text {
  font-size: 15px;
}

.buttons {
  margin-top: 10px;
}
.modal-header{
  display: none;
}


.modal-footer {
  margin-top: 15px;
  justify-content: right;
  margin-left: 89px;
  margin-bottom: 20px;
}

.input-field {
  width: 80%;
  height: 40px
}
.modal-footer .btn {
  margin-right: 10px;
}

</style>
