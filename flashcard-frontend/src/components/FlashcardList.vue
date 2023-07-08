<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="flashcard in flashcards" :key="flashcard.id">
      <flashcard :flashcard="flashcard"></flashcard>
    </div>
  </div>
</template>

<script>
import Flashcard from "@/components/Flashcard.vue";

export default {
  name: 'FlashcardList',
  components: {
    Flashcard,
  },
  props: {
    flashcards: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>

</style>
