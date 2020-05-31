<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="jumbotron" style="background-color: white">
          <div class="row">
            <div class="col-12">
              <h3 class="d-flex flex-start">My Booked Classes</h3>
              <br/>
              <p style="text-align: left">Booked classes page text here</p>
            </div>
          </div>

          <br/>
          <br/>

          <div class="row">
            <div class="col-12">
              <div class="row">
              <div v-for="(classes, index) in userClasses" :key="index">
                <div class="col-12">
                  <div class="card">
                    <div class="card-img">
                      <div style="text-align: center">
                        <img src="../assets/logo.png" />
                      </div>
                    </div>
                    <div class="card-body">
                      Class: <strong>{{ classes.className }}</strong><br/>
                      Time: <strong>{{ classes.dateTime | moment("DD-MM-YYYY HH:mm:ss") }}</strong><br/>
                      Remaining Spaces: <strong>{{ classes.maxCapacity-classes.totalEnrolled }}</strong><br/>
                    </div>
                  </div>
                </div>
              </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GymClassService from "../services/gymclass.service";
export default {
  name: "Gymclass",
  data() {
    return {
      content: "",
      gymClasses: [],
      userClasses: [],
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    checkNumberEnrolled(){
      return gymclass => (gymclass.maxCapacity-gymclass.totalEnrolled > 0) ? true : false;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }

    this.loadUserClasses();
  },
  methods: {
    loadUserClasses(){
      GymClassService.getUserGymClasses(this.currentUser).then(
        response => {
          this.userClasses = response.data;
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
  }
};
</script>
