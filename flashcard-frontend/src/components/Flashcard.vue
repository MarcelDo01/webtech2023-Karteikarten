<template>
  <div class="card card-outline-dark h-100" @click="toggleAnswer">
    <div class="card-body">
      <h5 class="card-title">Frage: {{ flashcard.question }}</h5>
      <p v-if="showAnswer" class="card-text">Antwort: {{ flashcard.answer }}</p>
      <div class="buttons">
        <button class="btn btn-danger" @click="deleteFlashcard">Delete</button>
        <button class="btn btn-primary" @click="updateFlashcard">Update</button>
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
      showAnswer: false
    };
  },
  methods: {
    toggleAnswer() {
      this.showAnswer = !this.showAnswer;
    },
    deleteFlashcard() {
      // Implementiere hier die Logik zum Löschen der Karteikarte
      const flashcardId = this.flashcard.id;
      // Rufen Sie den gewünschten API-Endpunkt auf, um die Karteikarte zu löschen
      const endpoint = `http://localhost:8080/api/v1/flashcards/${this.flashcard.id}`;
      const requestOptions = {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        }
      };

      fetch(endpoint, requestOptions)
        .then(response => {
          if (response.ok) {
            // Wenn die Karteikarte erfolgreich gelöscht wurde, können Sie hier weitere Aktionen ausführen
            console.log('Karteikarte erfolgreich gelöscht');
          } else {
            // Wenn ein Fehler aufgetreten ist, können Sie hier den entsprechenden Fehler behandeln
            console.error('Fehler beim Löschen der Karteikarte');
          }
        })
        .catch(error => {
          // Wenn ein Fehler beim Fetchen oder bei der Verarbeitung des Requests aufgetreten ist, können Sie ihn hier behandeln
          console.error('Fehler beim Löschen der Karteikarte', error);
        });
    },
    updateFlashcard() {
      // Implementiere hier die Logik zur Aktualisierung der Karteikarte
      const flashcardId = this.flashcard.id;
      // Rufen Sie den gewünschten API-Endpunkt auf, um die Karteikarte zu aktualisieren
      const endpoint = `http://localhost:8080/api/v1/flashcards/${this.flashcard.id}`;
      const requestOptions = {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          // Passen Sie hier die aktualisierten Daten der Karteikarte an
        })
      };

      fetch(endpoint, requestOptions)
        .then(response => {
          if (response.ok) {
            // Wenn die Karteikarte erfolgreich aktualisiert wurde, können Sie hier weitere Aktionen ausführen
            console.log('Karteikarte erfolgreich aktualisiert');
          } else {
            // Wenn ein Fehler aufgetreten ist, können Sie hier den entsprechenden Fehler behandeln
            console.error('Fehler beim Aktualisieren der Karteikarte');
          }
        })
        .catch(error => {
          // Wenn ein Fehler beim Fetchen oder bei der Verarbeitung des Requests aufgetreten ist, können Sie ihn hier behandeln
          console.error('Fehler beim Aktualisieren der Karteikarte', error);
        });
    }
  }
}
</script>

<style scoped>
.card {
  border: 2px solid black;
  padding: 15px;
  cursor: pointer;
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
</style>
