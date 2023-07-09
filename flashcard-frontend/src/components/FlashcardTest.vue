<template>
  <div class="card card-outline-dark h-100">
    <div class="card-body" @click="toggleAnswer">
      <h5 class="card-title" v-if="!showSolution">Frage: {{ flashcard.question }}</h5>
      <p v-show="showAnswer || showSolution" class="card-text">Antwort: {{ flashcard.answer }}</p>
    </div>
    <div class="buttons">
      <button class="btn btn-primary" @click="toggleSolution" style="margin: 5px">Lösungen zeigen/verbergen</button>
      <button class="btn btn-success" @click="markAsKnown" style="margin: 5px">Ich kann</button>
    </div>
  </div>
  <br>
</template>

<script>
export default {
  name: 'FlashcardTest',
  props: {
    flashcard: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      showAnswer: false,
      showSolution: false
    };
  },
  methods: {
    toggleAnswer() {
      if (!this.showSolution) {
        this.showAnswer = !this.showAnswer;
      }
    },
    toggleSolution() {
      this.showSolution = !this.showSolution;
      this.showAnswer = false;
    },
    markAsKnown() {
      this.$emit('markAsKnown', this.flashcard);
    }
  }
}
</script>

<style scoped>
.card {
  border: 2px solid black;
  padding: 20px 35px;
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
</style>
