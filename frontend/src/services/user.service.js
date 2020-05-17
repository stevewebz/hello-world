import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8085/api/home/";

class UserService {
  getPublicContent() {
    return axios.get(API_URL + "all");
  }

  getUserBoard() {
    return axios.get(API_URL + "user", { headers: authHeader() });
  }

  getInstructorBoard() {
    return axios.get(API_URL + "instructor", { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + "admin", { headers: authHeader() });
  }
}

export default new UserService();
