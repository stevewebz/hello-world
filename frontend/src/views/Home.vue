<template>
  <div class="home">
    <h1>Home Page</h1>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      title: ""
    };
  },
  mounted() {
    fetch("http://localhost:8085/api/user/levels")
      .then(response => {
        return response.text();
      })
      .then(data => {
        this.title = data;
      });
  }
};
</script>
