<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="jumbotron" style="background-color: white">
          <div class="row">
            <div class="col-12">
              <h3 class="d-flex flex-start">Book Classes</h3>
              <router-link
                to="/bookedclasses"
                tag="button"
                class="btn btn-outline-dark fa-pull-right"
              >
                My Booked Classes
              </router-link>
              <br/>
              <p style="text-align: left">Book classes page text here</p>
            </div>
          </div>

          <br/>
          <br/>

          <div class="row">
            <div class="col-12">
              <h3 style="text-align: center">Classes</h3>
              <br/>
              <p style="text-align: center">Book classes page text here</p>
              <div class="row">
                <div v-for="(classes, index) in gymClasses" :key="index">
                  <div class="card" style="margin-top: 2rem; margin-left: 1rem">
                    <div class="card-img">
                      <div style="text-align: center">
                        <img src="../assets/logo.png" />
                      </div>
                    </div>
                    <div class="card-body">
                      Class: <strong>{{ classes.className }}</strong><br/>
                      Time: <strong>{{ classes.dateTime | moment("DD-MM-YYYY HH:mm") }}</strong><br/>
                      Remaining Spaces: <strong>{{ classes.maxCapacity-classes.totalEnrolled }}</strong><br/>
                      <br/>
                      <button v-if="checkNumberEnrolled(classes)" v-on:click="bookClass(classes)" class="btn btn-sm btn-outline-dark">Book</button>
                      <button v-if="!checkNumberEnrolled(classes)" v-on:click="joinWaitlist(classes)" class="btn btn-sm btn-outline-dark">Join Waitlist</button>
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

    this.loadAllClasses();
    this.loadUserClasses();
  },
  methods: {
    bookClass: function(gymclass) {
      GymClassService.bookGymClass(gymclass, this.currentUser).then(
        response => {
          this.loadAllClasses();
          this.loadUserClasses();

          this.$swal("Success", response.data.message, "success");
        },
        error => {
          this.bookSuccess = false;
          this.bookMessage =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          this.$swal("Failed", error.response.data.message, "error");
        }
      );
    },
    joinWaitlist: function(gymclass) {
      GymClassService.joinWaitlist(gymclass, this.currentUser).then(
        response => {
          this.loadAllClasses();
          this.loadUserClasses();

          this.$swal("Success", response.data.message, "success");
        },
        error => {
          this.bookSuccess = false;
          this.bookMessage =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          this.$swal("Failed", error.response.data.message, "error");
        }
      );
    },
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
    loadAllClasses(){
      GymClassService.getGymClasses().then(
        response => {
          this.gymClasses = response.data;
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    }
  }
};
</script>
