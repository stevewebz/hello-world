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
          <div class="row">
            <div class="col-12">
              <div class="row">
                <div v-for="(classes, index) in userClasses" :key="index">
                  <div class="card" style="margin-top: 2rem; margin-left: 1rem">
                    <div class="card-img">
                      <div style="text-align: center">
                        <img src="../assets/logo.png" />
                      </div>
                    </div>
                    <div class="card-body">
                      Class: <strong>{{ classes.className }}</strong><br/>
                      Time: <strong>{{ classes.dateTime | moment("DD-MM-YYYY HH:mm:ss") }}</strong><br/>
                      Remaining Spaces: <strong>{{ classes.maxCapacity-classes.totalEnrolled }}</strong><br/>
                      <br/>
                      <button v-on:click="cancelClass(classes)" class="btn btn-sm btn-outline-danger">Cancel</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <br/>
          <br/>

          <div class="row">
            <div class="col-12">
              <h3 class="d-flex flex-start">My Waitlist Classes</h3>
              <br/>
              <p style="text-align: left">Waitlist classes page text here</p>
            </div>
          </div>
          <div class="row">
            <div class="col-12">
              <div class="row">
                <div v-for="(classes, index) in waitlistClasses" :key="index">
                  <div class="card" style="margin-top: 2rem; margin-left: 1rem">
                    <div class="card-img">
                      <div style="text-align: center">
                        <img src="../assets/logo.png" />
                      </div>
                    </div>
                    <div class="card-body">
                      Class: <strong>{{ classes.className }}</strong><br/>
                      Time: <strong>{{ classes.dateTime | moment("DD-MM-YYYY HH:mm:ss") }}</strong><br/>
                      Remaining Spaces: <strong>{{ classes.maxCapacity-classes.totalEnrolled }}</strong><br/>
                      <br/>
                      <button v-on:click="cancelWaitlist(classes)" class="btn btn-sm btn-outline-danger">Cancel</button>
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
      waitlistClasses: [],
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
    this.loadWaitlistClasses();
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
    loadWaitlistClasses(){
      GymClassService.getWaitlistClasses(this.currentUser).then(
        response => {
          this.waitlistClasses = response.data;
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    cancelClass: function(gymclass){
      GymClassService.cancelClass(gymclass, this.currentUser).then(
        response => {
          this.waitlistClasses = response.data;
          this.loadUserClasses();
          this.loadWaitlistClasses();
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    cancelWaitlist: function(gymclass){
      GymClassService.cancelWaitlist(gymclass, this.currentUser).then(
        response => {
          this.waitlistClasses = response.data;
          this.loadUserClasses();
          this.loadWaitlistClasses();
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
