import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Profile from "../views/Profile.vue";
import BoardAdmin from "../views/BoardAdmin.vue";
import BoardInstructor from "../views/BoardInstructor.vue";
import BoardUser from "../views/BoardUser.vue";
import GetStarted from "../views/GetStarted.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile
  },
  {
    path: "/admin",
    name: "Admin",
    component: BoardAdmin
  },
  {
    path: "/instructor",
    name: "Instructor",
    component: BoardInstructor
  },
  {
    path: "/user",
    name: "User",
    component: BoardUser
  },
  {
    path: "/getstarted",
    name: "GetStarted",
    component: GetStarted
  }
];

const router = new VueRouter({
  routes
});

export default router;
