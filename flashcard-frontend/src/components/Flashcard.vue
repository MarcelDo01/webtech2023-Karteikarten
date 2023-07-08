<template>
  <div class="card card-outline-dark h-100" @click="toggleAnswer">
    <div class="card-body">
      <h5 class="card-title">Frage: {{ flashcard.question }}</h5>
      <p v-if="showAnswer" class="card-text">Antwort: {{ flashcard.answer }}</p>
      <div class="buttons">
        <button class="btn btn-danger" @click="deleteFlashcard">Delete</button>
        <button class="btn btn-primary" @click="openSidebar">Update</button>
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
      // ...
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
