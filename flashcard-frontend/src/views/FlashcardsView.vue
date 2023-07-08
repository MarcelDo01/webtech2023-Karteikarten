<template>
  <h1>Flashcards</h1>
  <div class="container">
    <div class="row">
      <div class="col scrollable-container">
        <flashcard-list :flashcards="flashcards" @cardClick="showCard"></flashcard-list>
      </div>
    </div>
  </div>
</template>

<script>
import FlashcardList from "@/components/FlashcardList.vue";

export default {
  name: 'FlashcardView',

  data() {
    return {
      flashcards: [],

    }
  },
  components: {
    FlashcardList,
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
    }
  }
}
</script>

<style scoped>
.container{
  text-align: left;
}
.row {
  margin-bottom: 100px;
}
.container-fluid {
  margin-top: 20px;
}

.scrollable-container {
  height: 700px;
  overflow-y: auto;
}
</style>
