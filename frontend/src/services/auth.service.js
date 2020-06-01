import axios from "axios";

const API_URL = "http://localhost:8085/api/auth/";

class AuthService {
  login(user) {
    return axios
      .post(API_URL + "signin", {
        email: user.email,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(user) {
    return axios.post(API_URL + "signup", {
      firstname: user.firstname,
      surname: user.surname,
      email: user.email,
      password: user.password,
      level: user.level,
      bankno: user.bankno,
      clearingno: user.clearingno
    });
  }

  changepass(user) {
    return axios.post(API_URL + "changepass", {
      email: user.email,
      password: user.password,
    });
  }

  cancelMembership(user) {
    return axios.post(API_URL + "cancel", {
      email: user.email,
    });
  }
}

export default new AuthService();
