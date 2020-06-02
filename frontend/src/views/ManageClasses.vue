<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="jumbotron" style="background-color: white">
          <div class="row">
            <div class="col-12">
              <h3 class="d-flex flex-start">Manage Classes</h3>
               <button v-b-modal.modal-2 v-on:click="setNewClass" class="btn btn-sm btn-outline-dark fa-pull-right">Create New Class</button>
              <br/>
              <p style="text-align: left">Manage classes page text here</p>
            </div>
          </div>
          <div class="row">
            <div class="col-12">
              <div class="row">
                <div v-for="(classes, index) in instructorClasses" :key="index">
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
                      <button v-b-modal.modal-1 v-on:click="setEditClass(classes)" class="btn btn-sm btn-outline-dark">Edit</button>
                      <button v-if="(checkNobodyEnrolled(classes) == true)" v-on:click="deleteClass(classes)" style="margin-left: 1rem" class="btn btn-sm btn-outline-danger">Delete</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <b-modal id="modal-1" title="Edit Class" hide-footer>
      <form name="form" @submit.prevent="handleEditClass">
        <div class="form-group">
          <label for="password">Name</label>
          <input
            v-model="editClass.className"
            v-validate="'required'"
            type="text"
            class="form-control"
            name="name"
          />
          <div
            v-if="submitted && errors.has('name')"
            class="alert alert-danger"
          >
            {{ errors.first("name") }}
          </div>
        </div>
        <div class="form-group">
          <label for="datetime">Date</label>
          <datetime v-model="editClass.dateTime"
            v-validate="'required'"
            name="datetime"
            type="datetime"
          >
          </datetime>
          <div
            v-if="submitted && errors.has('datetime')"
            class="alert alert-danger"
          >
            {{ errors.first("datetime") }}
          </div>
        </div>
        <br />
        <div class="form-group">
          <label for="maxCapacity">Max Capacity</label>
          <input
            v-model="editClass.maxCapacity"
            v-validate="'required'"
            type="number"
            class="form-control"
            name="maxCapacity"
          />
          <div
            v-if="submitted && errors.has('maxCapacity')"
            class="alert alert-danger"
          >
            {{ errors.first("maxCapacity") }}
          </div>
        </div>
        <br />
        <div class="form-group">
          <label for="location">Location</label>
          <b-form-select
            v-model="editClass.locationId"
            :options="options"
            v-validate="'required'"
            class="form-control"
            name="location"
          >
          </b-form-select>
          <div
            v-if="submitted && errors.has('location')"
            class="alert alert-danger"
          >
            {{ errors.first("location") }}
          </div>
        </div>
        <br />
        <div class="form-group">
          <button class="btn btn-secondary btn-block">Submit</button>
        </div>
      </form>
    </b-modal>

    <b-modal id="modal-2" title="New Class" hide-footer>
      <form name="form" @submit.prevent="handleNewClass">
        <div class="form-group">
          <label for="password">Name</label>
          <input
            v-model="newClass.className"
            v-validate="'required'"
            type="text"
            class="form-control"
            name="name"
          />
          <div
            v-if="submitted && errors.has('name')"
            class="alert alert-danger"
          >
            {{ errors.first("name") }}
          </div>
        </div>
        <div class="form-group">
          <label for="datetime">Date</label>
          <datetime v-model="newClass.dateTime"
            v-validate="'required'"
            name="datetime"
            type="datetime"
          >
          </datetime>
          <div
            v-if="submitted && errors.has('datetime')"
            class="alert alert-danger"
          >
            {{ errors.first("datetime") }}
          </div>
        </div>
        <br />
        <div class="form-group">
          <label for="maxCapacity">Max Capacity</label>
          <input
            v-model="newClass.maxCapacity"
            v-validate="'required'"
            type="number"
            class="form-control"
            name="maxCapacity"
          />
          <div
            v-if="submitted && errors.has('maxCapacity')"
            class="alert alert-danger"
          >
            {{ errors.first("maxCapacity") }}
          </div>
        </div>
        <br />
        <div class="form-group">
          <label for="location">Location</label>
          <b-form-select
            v-model="newClass.locationId"
            :options="options"
            v-validate="'required'"
            class="form-control"
            name="location"
          >
          </b-form-select>
          <div
            v-if="submitted && errors.has('location')"
            class="alert alert-danger"
          >
            {{ errors.first("location") }}
          </div>
        </div>
        <br />
        <div class="form-group">
          <button class="btn btn-secondary btn-block">Submit</button>
        </div>
      </form>
    </b-modal>
  </div>
</template>

<script>
import GymClassService from "../services/gymclass.service";
import LocationService from "../services/location.service";
import GymClass from "../models/gymclass";
export default {
  name: "ManageClasses",
  data() {
    return {
      submitted: false,
      successful: false,
      message: "",
      content: "",
      instructorClasses: [],
      options: [],
      editClass: new GymClass(0, "", "", 0, 0, "", 0),
      newClass: new GymClass(0, "", "", 0, 0, "", 0),
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    checkNumberEnrolled(){
      return gymclass => (gymclass.maxCapacity-gymclass.totalEnrolled > 0) ? true : false;
    },
    checkNobodyEnrolled(){
      return gymclass => (gymclass.totalEnrolled == 0) ? true : false;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }
    if (this.currentUser.userLevel[0] != "INSTRUCTOR"){
      this.$router.push("/");
    }

    this.loadAllLocations();
    this.loadInstructorClasses();
  },
  methods: {
    setEditClass: function(gymClass){
      this.editClass = new GymClass(0, "", "", 0, 0, "", 0);
      this.editClass.classId = gymClass.classId;
      this.editClass.className = gymClass.className;
      this.editClass.dateTime = gymClass.dateTime;
      this.editClass.maxCapacity = gymClass.maxCapacity;
      this.editClass.location = gymClass.location.locationId;
    },
    setNewClass: function(){
      this.newClass = new GymClass(0, "", "", 0, 0, "", 0);
    },
    loadAllLocations(){
      LocationService.getLocations().then(
        response => {
          this.options = [];
          this.options.push({ value: null, text: "Please select a Location" })
          response.data.forEach(element => {
            this.options.push({ value: element.locationId, text: element.locationName });
          });
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    loadInstructorClasses(){
      GymClassService.getInstructorGymClasses(this.currentUser).then(
        response => {
          this.instructorClasses = response.data;
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    deleteClass: function(gymclass){
      GymClassService.deleteClass(gymclass).then(
        response => {
          response;
          this.loadInstructorClasses();
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          this.$swal("Failed", error.response.data.message, "error");
        }
      );
    },
    handleEditClass() {
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then(isValid => {
        if (isValid) {
          GymClassService.updateGymClass(this.editClass, this.currentUser).then(
            data => {
              this.message = data.message;
              this.$swal("Success", data.message, "success");
              this.successful = true;
              this.loadAllLocations();
              this.loadInstructorClasses();
            },
            error => {
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    },
    handleNewClass() {
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then(isValid => {
        if (isValid) {
          GymClassService.createGymClass(this.newClass, this.currentUser).then(
            data => {
              this.message = data.message;
              this.$swal("Success", data.message, "success");
              this.successful = true;
              this.loadAllLocations();
              this.loadInstructorClasses();
            },
            error => {
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    }
  }
};
</script>
