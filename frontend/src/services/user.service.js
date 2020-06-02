import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8085/api/users/";

class UserService {
  editUser(newUser, currentUser){
    return axios.post(API_URL + "edit", { 
      userId: currentUser.userId,
      firstname: newUser.firstname,
      surname: newUser.surname,
      email: newUser.email,
      level: newUser.level,
    }, { headers: authHeader() }).then(response => {
      var ls = JSON.parse(localStorage["user"]);
      ls.firstname = response.data.firstname;
      ls.surname = response.data.surname;
      ls.email = response.data.email;
      ls.userLevel[0] = response.data.userLevel[0].levelName;
      localStorage.setItem("user", JSON.stringify(ls));

      console.log(ls);
      console.log(response.data);

      return response.data;
    });
  }
}

export default new UserService();
