import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8085/api/billing/";

class BillingService {
  getUserBilling(userid) {
    return axios.get(API_URL + userid, { headers: authHeader() });
  }

  deleteBilling(billing) {
    return axios.post(API_URL + "delete", { billingId: billing.billingId }, { headers: authHeader() });
  }
}

export default new BillingService();
