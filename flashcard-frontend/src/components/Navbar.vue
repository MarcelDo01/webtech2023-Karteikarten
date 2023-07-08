<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">

      <a class="navbar-brand" href="" style="margin-right: 20px">FlashcardApp</a>

      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">

          <router-link class="nav-link" to="/" style="margin-right: 20px">Home</router-link>
          <router-link class="nav-link" to="/about" style="margin-right: 20px">About</router-link>
          <router-link class="nav-link" to="/flashcards" style="margin-right: 20px">Flashcards</router-link>

        </div>
      </div>
    </div>
  </nav>
</template>

<style>
  .navbar navbar-expand-lg navbar-light bg-light {
    background: grey;

  }
  .navbar-nav{
    display: flex;
    justify-content: flex-start;
    text-align: left;
  }
  .container-fluid {
    display: flex;
    justify-content: flex-start;
    text-align: left;
  }
</style>
<script>
export default {
  name: 'Navbar'
}
</script>

<style scoped>

</style>
