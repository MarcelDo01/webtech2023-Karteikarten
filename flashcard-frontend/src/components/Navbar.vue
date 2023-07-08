<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="background">
    <div class="container-fluid">

      <a class="navbar-brand" href="" style="margin-right: 20px ;text-decoration: none">FlashcardApp</a>

      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">

          <div class="About">
            <router-link class="nav-link" to="/about" style="margin-right: 20px ;text-decoration: none">About</router-link>
          </div>
          <div class="Flashcard">
            <router-link class="nav-link" to="/flashcards" style="margin-right: 20px ;text-decoration: none">Flashcards</router-link>
          </div>
        </div>
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
  .background {
    background-color: #1a242c;
  }
  .Flashcard {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    background: linear-gradient(#ededed, #d1d1d1);
    box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.3);
    cursor: pointer;
  }
  .About {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    background: linear-gradient(#ededed, #d1d1d1);
    box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.3);
    cursor: pointer;
    margin-right: 20px;
  }
  .navbar-brand {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    background: linear-gradient(#ededed, #d1d1d1);
    box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.3);
    cursor: pointer;
    margin-right: 20px;
  }

</style>
<script>
export default {
  name: 'Navbar'
}
</script>

<style scoped>

</style>
