<template>
  <h1>Flashcards</h1>
  <div class="container">
    <div class="row" style="white-space: nowrap">
      <div class="col-md-8 scrollable-container" >
        <flashcard-list :flashcards="flashcards" @cardClick="showCard"></flashcard-list>
      </div>
      <div class="col-md-6 flashcard-sidebar">
        <edit-flashcard-sidebar :flashcard="selectedCard" @updateFlashcard="updateFlashcard"></edit-flashcard-sidebar>
      </div>
    </div>
  </div>
  <br>
</template>

<script>
import FlashcardList from "@/components/FlashcardList.vue";
import EditFlashcardSidebar from "@/components/EditFlashcardSidebar.vue"

export default {
  name: 'FlashcardView',

  data() {
    return {
      flashcards: [],
      selectedCard: null
    }
  },
  components: {
    FlashcardList,
    EditFlashcardSidebar
  },
  mounted() {
    const endpoint = "http://localhost:8080/api/v1/flashcards";
    fetch(endpoint)
      .then(response => response.json())
      .then(result => {
        this.flashcards = result;
      })
      .catch(error => console.log('error', error));
  },
  methods: {
    showCard(card) {
      this.selectedCard = card;
    },
    updateFlashcard(updatedFlashcard) {
      // Implementiere hier die Logik zum Aktualisieren der Flashcard
      // Verwende updatedFlashcard, um die aktualisierten Daten zu erhalten
    }
  }
}
</script>

<style scoped>
.container {
  text-align: auto;
}
.row {
  margin-bottom: 100px;
}
.container-fluid {
  margin-top: 20px;
}

.scrollable-container {
  height: auto;
  overflow-y: auto;
  max-height: 50vh; /* Hier kannst du die maximale Höhe festlegen */
  width: auto;
}

</style>
