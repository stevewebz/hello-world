import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Profile from "../views/Profile.vue";
import BoardInstructor from "../views/BoardInstructor.vue";
import GymClass from "../views/GymClass.vue";
import BookedClasses from "../views/BookedClasses.vue";
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
    path: "/instructor",
    name: "Instructor",
    component: BoardInstructor
  },
  {
    path: "/gymclass",
    name: "GymClass",
    component: GymClass
  },
  {
    path: "/bookedclasses",
    name: "BookedClasses",
    component: BookedClasses
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
