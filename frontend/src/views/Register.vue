<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-6 offset-3" style="text-align: left; margin-top: 4rem">
        <form name="form" @submit.prevent="handleRegister">
          <div v-if="!successful">
            <div class="form-group">
              <label for="firstname">First Name</label>
              <input
                v-model="user.firstname"
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
                v-model="user.surname"
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
                v-model="user.email"
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
              <label for="password">Password</label>
              <input
                v-model="user.password"
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
            <div class="form-group">
              <label for="level">Membership Tier</label>
              <b-form-select
                v-model="user.level"
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
            <div v-if="user.level" class="form-group">
              <label for="bank">Bank Number</label>
              <input
                v-model="user.bankno"
                v-validate="'required'"
                type="text"
                class="form-control"
                name="bank"
              />
              <div
                v-if="submitted && errors.has('bank')"
                class="alert alert-danger"
              >
                You must enter a Bank Number
              </div>
            </div>
            <div v-if="user.level" class="form-group">
              <label for="clearing">Clearing Number</label>
              <input
                v-model="user.clearingno"
                v-validate="'required'"
                type="text"
                class="form-control"
                name="clearing"
              />
              <div
                v-if="submitted && errors.has('clearing')"
                class="alert alert-danger"
              >
                You must enter a Clearing Number
              </div>
            </div>
            <br />
            <div class="form-group">
              <button class="btn btn-secondary btn-block">Submit</button>
            </div>
          </div>
        </form>

        <div
          v-if="message"
          class="alert"
          :class="successful ? 'alert-success' : 'alert-danger'"
        >
          {{ message }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import User from "../models/user";
export default {
  name: "Register",
  data() {
    return {
      user: new User("", "", "", "", null, "", ""),
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
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleRegister() {
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch("auth/register", this.user).then(
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

<style scoped>
label {
  display: block;
  margin-top: 10px;
}
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
