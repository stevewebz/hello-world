<template>
  <div class="levels">
    <h1>Levels</h1>
    <ul>
      <li v-for="(value, index) in levels" v-bind:key="index">
        {{ value.levelName }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Levels",
  data() {
    return {
      levels: []
    };
  },
  mounted() {
    fetch("http://localhost:8085/api/user/levels")
      .then(response => {
        return response.json();
      })
      .then(data => {
        this.levels = data;
      });
  }
};
</script>
