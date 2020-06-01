import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8085/api/classes/";

class GymclassService {
  getGymClasses() {
    return axios.get(API_URL + "all", { headers: authHeader() });
  }

  getUserGymClasses(user){
    return axios.post(API_URL + "userClasses", {
      email: user.email,
    }, 
    { 
      headers: authHeader()
    });
  }

  getWaitlistClasses(user){
    return axios.post(API_URL + "waitlistClasses", {
      email: user.email,
    }, 
    { 
      headers: authHeader()
    });
  }

  bookGymClass(gymclass, user){
    return axios.post(API_URL + "book", {
        email: user.email,
        classid: gymclass.classId,
    }, 
    { 
      headers: authHeader()
    });
  }

  joinWaitlist(gymclass, user){
    return axios.post(API_URL + "waitlist", {
        email: user.email,
        classid: gymclass.classId,
    }, 
    { 
      headers: authHeader()
    });
  }

  cancelClass(gymclass, user){
    return axios.post(API_URL + "cancelClass", {
        email: user.email,
        classid: gymclass.classId,
    }, 
    { 
      headers: authHeader()
    });
  }

  cancelWaitlist(gymclass, user){
    return axios.post(API_URL + "cancelWaitlist", {
        email: user.email,
        classid: gymclass.classId,
    }, 
    { 
      headers: authHeader()
    });
  }
}

export default new GymclassService();
