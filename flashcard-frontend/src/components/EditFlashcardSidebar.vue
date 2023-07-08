<template>
  <button class="btn btn-success sticky-button" data-bs-toggle="offcanvas" data-bs-target="#flashcards-create-offcanvas" aria-controls="#flashcards-create-offcanvas">
    <i class="bi bi-flashcard-plus-fill"></i>
  </button>
  <div class="offcanvas offcanvas-end" tabindex="-1" id="flashcards-create-offcanvas" aria-labelledby="offcanvas-label">
    <div class="offcanvas-header">
      <h5 id="offcanvas-label">New Flashcard</h5>
      <button type="button" id="close-offcanvas" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
      <form class="text-start needs-validation" id="flashcard-create-form" novalidate>
        <div class="mb-3">
          <label for="question" class="form-label">Question</label>
          <input type="text" class="form-control" id="question" v-model="question" required>
          <div class="invalid-feedback">
            Please provide the question.
          </div>
        </div>
        <div class="mb-3">
          <label for="answer" class="form-label">Answer</label>
          <input type="text" class="form-control" id="answer" v-model="answer" required>
          <div class="invalid-feedback">
            Please provide the answer.
          </div>
        </div>
        <div class="mb-3">
          <label for="category" class="form-label">Category</label>
          <input id="category" class="form-select" v-model="category" required>

          </input>
          <div class="invalid-feedback">
            Please select a valid gender.
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
          <button class="btn btn-primary me-3" type="submit" @click.prevent="createPerson">Create</button>
          <button class="btn btn-danger" type="reset">Reset</button>
        </div>
      </form>
    </div>
  </div>
</template>
