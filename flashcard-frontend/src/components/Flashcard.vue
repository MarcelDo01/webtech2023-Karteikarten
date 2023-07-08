<template>
  <div className="card card-outline-dark h-100">
    <div className="card-body">
      <h5 className="card-title">Frage: {{ flashcard.question }}</h5>

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
  }
}

</script>

<style scoped>
.card {
  border: 2px solid black;
  padding: 20px;
}

.card-title {
  margin-bottom: 10px;
}

.card-title, .card-text {
  font-size: 16px;
}
</style>
