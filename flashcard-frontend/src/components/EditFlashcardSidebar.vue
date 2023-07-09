<template>
  <div class="offcanvas offcanvas-end" tabindex="-1" id="flashcard-create-offcanvas" aria-labelledby="offcanvas-label">
    <div class="offcanvas-header">
      <button type="text" id="close-offcanvas" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
      <form class="text-start needs-validation" id="flashcard-create-form" novalidate>
        <div class="mb-3">
          <label for="question" class="form-label">Question</label>
          <span class="input-group-text" style="margin-right: 10px;">:</span>
          <input type="text" class="form-control larger-input" id="question" v-model="question" maxlength="100" style="word-wrap: anywhere " required>
          <div class="invalid-feedback">
          </div>
        </div>
        <div class="mb-3">
          <label for="answer" class="form-label">Answer</label>
          <span class="input-group-text" style="margin-right: 21px;">:</span>
          <input type="text" class="form-control larger-input" id="answer" v-model="answer" maxlength="100" required>
          <div class="invalid-feedback">
          </div>
        </div>
        <div v-if="this.serverValidationMessages">
          <ul>
            <li v-for="(message, index) in serverValidationMessages" :key="index" style="color: red">
              {{ message }}
            </li>
          </ul>
        </div>
        <div class="mt-5">
          <button class="btn btn-primary me-3" type="submit" @click.prevent="createFlashcard">Create</button>
          <button class="btn btn-danger" type="reset">Reset</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FlashcardCreateForm',
  data () {
    return {
      question: '',
      answer: '',
      serverValidationMessages: []
    }
  },
  emits: ['created'],
  methods: {
    async createFlashcard () {
      if (this.validate()) {
        const endpoint = "http://localhost:8080/api/v1/flashcards"

        const headers = new Headers()
        headers.append('Content-Type', 'application/json')

        const flashcard = JSON.stringify({
          question: this.question,
          answer: this.answer
        })

        const requestOptions = {
          method: 'POST',
          headers: headers,
          body: flashcard,
          redirect: 'follow'
        }

        const response = await fetch(endpoint, requestOptions)
        await this.handleResponse(response)
      }location.reload();
    },
    async handleResponse (response) {
      if (response.ok) {
        this.$emit('created', response.headers.get('location'))
        document.getElementById('close-offcanvas').click()
      } else if (response.status === 400) {
        response = await response.json()
        response.errors.forEach(error => {
          this.serverValidationMessages.push(error.defaultMessage)
        })
      } else {
        this.serverValidationMessages.push('Unknown error occurred')
      }
    },
    validate () {
      const form = document.getElementById('flashcard-create-form')
      form.classList.add('was-validated')
      return form.checkValidity()
    }
  }
}
</script>

<style scoped>
.sticky-button {
  position: fixed;
  bottom: 30px;
  right: 20px;
  padding: 10px 20px;
  border-radius: 50px;
}
.offcanvas-header{
  display: none;
}
.larger-input {
  height: 50px;
  font-size: 16px;
  width: 70%;
}
.mt-5 .btn-primary{
  margin-right: 10px;
}
</style>
