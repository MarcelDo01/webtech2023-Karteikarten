<template>
  <div v-if="selectedCard" class="card card-outline-dark">
    <div class="card-body">
      <h5 class="card-title">{{ selectedCard.question }}</h5>
      <p class="card-text">{{ selectedCard.answer }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SelectedFlashcard',
  props: {
    selectedCard: {
      type: Object,
      default: () => ({}) // Standardwert als leeres Objekt
    }
  }
}
</script>

<style scoped>
.card {
  margin-left: 20px;
  border: 2px solid black;
}

.card-outline-dark .card-body {
  padding: 15px;
}
</style>
