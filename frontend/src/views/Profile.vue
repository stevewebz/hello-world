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
                <br/>
                <ul>
                  <li v-for="(billing, index) in userBillings" :key="index">
                    Bank No: {{ billing.bankNo }}<br/>
                    Clearing No: {{ billing.clearingNo }}
                  </li>
                </ul>
            </b-tab>
          </b-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BillingService from "../services/billing.service";
import User from "../models/user";
export default {
  name: "Profile",
  data() {
    return {
      userBillings: [],
      submitted: false,
      successful: false,
      message: ""
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
  },
  methods: {
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
    }
  }
};
</script>
