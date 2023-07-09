<template>
  <div class="flashcard-carousel">
    <h1>Flashcard Quiz</h1>
    <div class="carousel-container">
      <button class="carousel-btn prev" @click="prevFlashcard">&#8249;</button>
      <div class="carousel">
        <div class="carousel-item" v-for="(flashcard, index) in visibleFlashcards" :key="index">
          <Flashcard :flashcard="flashcard" />
        </div>
      </div>
      <button class="carousel-btn next" @click="nextFlashcard">&#8250;</button>
    </div>
  </div>
</template>

<script>
import Flashcard from "@/components/Flashcard.vue";

export default {
  name: 'FlashcardCarousel',
  components: {
    Flashcard
  },
  data() {
    return {
      flashcards: [], // Array mit den Karteikarten-Daten
      currentIndex: 0, // Index des aktuellen Karteikarten-Elements im Carousel
      visibleFlashcards: [] // Array mit den aktuell sichtbaren Karteikarten
    };
  },
  mounted() {
    this.loadFlashcards();
  },
  methods: {
    loadFlashcards() {
      const endpoint = "http://localhost:8080/api/v1/flashcards";
      fetch(endpoint)
        .then(response => response.json())
        .then(result => {
          this.flashcards = result;
          this.updateVisibleFlashcards();
        })
        .catch(error => console.log('Fehler beim Laden der Karteikarten', error));
    },

    prevFlashcard() {
      if (this.currentIndex > 0) {
        this.currentIndex--;
      } else {
        this.currentIndex = this.flashcards.length - 1;
      }
      this.updateVisibleFlashcards();
    },

    nextFlashcard() {
      if (this.currentIndex < this.flashcards.length - 1) {
        this.currentIndex++;
      } else {
        this.currentIndex = 0;
      }
      this.updateVisibleFlashcards();
    },

    updateVisibleFlashcards() {
      const numVisibleCards = 3; // Anzahl der gleichzeitig sichtbaren Karten im Carousel
      const startIdx = this.currentIndex;
      const endIdx = (startIdx + numVisibleCards - 1) % this.flashcards.length;
      if (endIdx >= startIdx) {
        this.visibleFlashcards = this.flashcards.slice(startIdx, endIdx + 1);
      } else {
        this.visibleFlashcards = [
          ...this.flashcards.slice(startIdx),
          ...this.flashcards.slice(0, endIdx + 1)
        ];
      }
    }
  }
}
</script>

<style scoped>
.flashcard-carousel {
  text-align: center;
}

.carousel-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.carousel {
  display: flex;
  overflow: hidden;
  width: 80%;
  max-width: 1500px;
}

.carousel-item {
  flex: 0 0 100%;
  margin-right: 10px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel-btn {
  border: none;
  background-color: transparent;
  color: #333;
  font-size: 24px;
  cursor: pointer;
}

.carousel-btn.prev {
  margin-right: 10px;
}

.carousel-btn.next {
  margin-left: 10px;
}
</style>
