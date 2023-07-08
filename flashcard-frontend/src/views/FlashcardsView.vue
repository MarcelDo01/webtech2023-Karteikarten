<template>
  <div>
    <h1>Flashcards</h1>
    <div class="container-fluid scrollable-container">
      <flashcard-list :flashcards="flashcards"></flashcard-list>
    </div>
  </div>
</template>

<script>
import FlashcardList from "@/components/FlashcardList.vue";

export default {
  name: 'Flashcards',

  data() {
    return {
      flashcards: []
    }
  },
  components: {
    FlashcardList
  },
  mounted() {
    const endpoint = "http://localhost:8080/api/v1/flashcards";
    fetch(endpoint)
      .then(response => response.json())
      .then(result => {
        this.flashcards = result;
      })
      .catch(error => console.log('error', error));
  }
}
</script>

<style scoped>
.scrollable-container {
  height: 1000px; /* Höhe der Scrollbar anpassen */
  overflow-y: scroll; /* Scrollbar anzeigen */
}
</style>
