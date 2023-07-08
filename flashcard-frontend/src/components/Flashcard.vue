<template>
  <div class="card card-outline-dark h-100" @click="toggleAnswer">
    <div class="card-body">
      <h5 class="card-title">Frage: {{ flashcard.question }}</h5>
      <p v-if="showAnswer" class="card-text">Antwort: {{ flashcard.answer }}</p>
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
    }
  }
}
</script>

<style scoped>
.card {
  border: 2px solid black;
  padding: 20px;
  cursor: pointer;
}

.card-title {
  margin-bottom: 10px;
}

.card-text {
  font-size: 16px;
}
</style>
