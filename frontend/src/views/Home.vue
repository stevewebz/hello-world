<template>
  <div class="container">
    <div class="row" style="margin-top: 15rem">
      <div class="col-7">
        <div class="jumbotron" style="background-color: white">
          <h1 class="d-flex flex-start">Where Dreams Come True</h1>
          <h3 class="d-flex flex-start">Fitness AB</h3>
          <hr class="my-4" />
          <router-link
            to="/getstarted"
            tag="button"
            class="btn btn-outline-dark d-flex flex-start"
          >
            Get Started
          </router-link>
        </div>
      </div>
      <div class="col-5">
        <div>
          <img src="../assets/gympic.png" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>
