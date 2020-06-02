<template>
  <div class="container">
    <div class="jumbotron" style="background-color: white; text-align: left">
      <div class="row">
        <div class="col col-12">
          <h3>Manage Membership</h3>
          <br/>
          <p>Manage membership page text.</p>
        </div>
      </div>
      <br/>
      <div class="row">
        <div class="col col-12">
          <b-tabs content-class="mt-3">
            <b-tab title="Personal Information" active>
                Name: <strong>{{ currentUser.firstname }} {{ currentUser.surname }}</strong><br/>
                Email: <strong>{{ currentUser.email }}</strong><br/>
                Membership Level: <strong>{{ userLevel }}</strong>
                <br/>
                <br/>
                <br/>
                <button v-b-modal.modal-2 v-on:click="setEditUser" class="btn btn-sm btn-outline-dark" style="margin-right: 1rem">Edit</button><button v-on:click="cancelMembership" class="btn btn-sm btn-outline-danger">Cancel Membership</button>
            </b-tab>
            <b-tab title="Change Password">
              <form name="form" @submit.prevent="handleChangePass">
              <div class="form-group">
              <label for="password">New Password</label>
              <input
                v-model="changePassUser.password"
                v-validate="'required|min:6|max:40'"
                type="password"
                class="form-control"
                name="password"
                ref="password"
              />
              <div
                v-if="submitted && errors.has('password')"
                class="alert alert-danger"
              >
                {{ errors.first("password") }}
              </div>
            </div>
            <div class="form-group">
              <label for="c_password">Confirm Password</label>
              <input
                v-validate="'required|confirmed:password'"
                type="password"
                class="form-control"
                name="c_password"
                data-vv-as="password"
              />
              <div
                v-if="submitted && errors.has('c_password')"
                class="alert alert-danger"
              >
                {{ errors.first("c_password") }}
              </div>
            </div>
            <br />
            <div class="form-group">
              <button class="btn btn-secondary btn-block">Submit</button>
            </div>
            </form>

            <div
              v-if="message"
              class="alert"
              :class="successful ? 'alert-success' : 'alert-danger'"
            >
              {{ message }}
            </div>
            </b-tab>
            <b-tab title="Billing Information">
              Probably shouldn't be showing this info!
              <button v-b-modal.modal-1 v-on:click="setNewBilling()" class="btn btn-sm btn-outline-dark fa-pull-right">Add Billing</button>
              <br/>
              <div v-for="(billing, index) in userBillings" :key="index">
                <br/>
                <button v-on:click="deleteBilling(billing)" class="btn btn-sm btn-outline-danger">
                  <b-icon-x></b-icon-x>
                </button>
                <div style="margin-left: 1rem; display:inline-block">
                  Bank No: {{ billing.bankNo }}
                </div>
                <div style="margin-left: 1rem; display:inline-block">
                  Clearing No: {{ billing.clearingNo }}
                </div>
              </div>
            </b-tab>
          </b-tabs>
        </div>
      </div>
    </div>

    <b-modal id="modal-1" title="New Billing" hide-footer>
      <form name="form" @submit.prevent="handleNewBilling">
        <div class="form-group">
          <label for="bankNo">Bank No</label>
          <input
            v-model="newBilling.bankNo"
            v-validate="'required'"
            type="text"
            class="form-control"
            name="bankNo"
          />
          <div
            v-if="submitted && errors.has('bankNo')"
            class="alert alert-danger"
          >
            {{ errors.first("bankNo") }}
          </div>
        </div>
        <div class="form-group">
          <label for="clearingNo">Clearing No</label>
          <input
            v-model="newBilling.clearingNo"
            v-validate="'required'"
            type="text"
            class="form-control"
            name="clearingNo"
          />
          <div
            v-if="submitted && errors.has('clearingNo')"
            class="alert alert-danger"
          >
            {{ errors.first("clearingNo") }}
          </div>
        </div>
        <br />
        <div class="form-group">
          <button class="btn btn-secondary btn-block">Submit</button>
        </div>
      </form>
    </b-modal>

    <b-modal id="modal-2" title="Edit User" hide-footer>
      <form name="form" @submit.prevent="handleEditUser">
        <div class="form-group">
          <label for="firstname">First Name</label>
          <input
            v-model="editUser.firstname"
            v-validate="'required|min:3|max:40'"
            type="text"
            class="form-control"
            name="firstname"
          />
          <div
            v-if="submitted && errors.has('firstname')"
            class="alert alert-danger"
          >
            {{ errors.first("firstname") }}
          </div>
        </div>
        <div class="form-group">
          <label for="surname">Surname</label>
          <input
            v-model="editUser.surname"
            v-validate="'required|min:3|max:40'"
            type="text"
            class="form-control"
            name="surname"
          />
          <div
            v-if="submitted && errors.has('surname')"
            class="alert alert-danger"
          >
            {{ errors.first("surname") }}
          </div>
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input
            v-model="editUser.email"
            v-validate="'required|email|max:50'"
            type="email"
            class="form-control"
            name="email"
          />
          <div
            v-if="submitted && errors.has('email')"
            class="alert alert-danger"
          >
            {{ errors.first("email") }}
          </div>
        </div>
         <div class="form-group">
          <label for="level">Membership Tier</label>
          <b-form-select
            v-model="editUser.level"
            :options="options"
            v-validate="'required'"
            class="form-control"
            name="level"
          >
          </b-form-select>
          <div
            v-if="submitted && errors.has('level')"
            class="alert alert-danger"
          >
            You must select a Membership Tier
          </div>
        </div>
        <br/>
        <div class="form-group">
          <button class="btn btn-secondary btn-block">Submit</button>
        </div>
      </form>
    </b-modal>
  </div>
</template>

<script>
import BillingService from "../services/billing.service";
import Billing from "../models/billing";
import UserService from "../services/user.service";
import User from "../models/user";
export default {
  name: "Profile",
  data() {
    return {
      newBilling: new Billing("", ""),
      editUser: new User("", "", "", ""),
      userBillings: [],
      submitted: false,
      successful: false,
      message: "",
      options: [
        { value: null, text: "Please select a Tier" },
        { value: "MEMBER_BASIC", text: "Basic" },
        { value: "MEMBER_STANDARD", text: "Standard" },
        { value: "MEMBER_PREMIUM", text: "Premium" }
      ]
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    userLevel(){
      if(!this.currentUser){
        return null;
      }
      var level = "";
      switch(this.currentUser.userLevel[0]){
        case "MEMBER_BASIC":
          level = "Basic";
          break;
        case "MEMBER_STANDARD":
          level = "Standard";
          break;
        case "MEMBER_PREMIUM":
          level = "Premium";
          break;
        case "INSTRUCTOR":
          level = "Instructor";
          break;
      }
      return level;
    },
    changePassUser(){
      return new User("", "", this.currentUser.email, "", null, "", "");
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }

    this.loadUserBilling();
  },
  methods: {
    setNewBilling() {
      this.newBilling = new Billing("", "");
    },
    setEditUser() {
      this.editUser = new User("", "", "", "");
      this.editUser.firstname = this.currentUser.firstname;
      this.editUser.surname = this.currentUser.surname;
      this.editUser.email = this.currentUser.email;
      this.editUser.level = this.currentUser.userLevel[0];
    },
    cancelMembership() {
      this.$store.dispatch("auth/cancelMembership", this.currentUser).then(
        data => {
          data;
          this.$store.dispatch("auth/logout");
          this.$router.push("/login");
        },
        error => {
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    deleteBilling: function(billing) {
      BillingService.deleteBilling(billing).then(
        data => {
          this.$swal("Success", data.message, "success");
          this.loadUserBilling();
        },
        error => {
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    handleChangePass() {
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch("auth/changepass", this.changePassUser).then(
            data => {
              this.message = data.message;
              this.successful = true;
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
    handleNewBilling() {
      BillingService.createBilling(this.newBilling, this.currentUser).then(
        data => {
          this.$swal("Success", data.message, "success");
          this.loadUserBilling();
        },
        error => {
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          this.successful = false;
        }
      );
    },
    handleEditUser() {
      UserService.editUser(this.editUser, this.currentUser).then(
        response => {
          response;
          this.$swal("Success", "Details edited!", "success");
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    loadUserBilling(){
      BillingService.getUserBilling(this.currentUser.userId).then(
      response => {
        this.userBillings = response.data;
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
