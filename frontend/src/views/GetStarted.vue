<template>
  <div class="container">
    <div class="row">
      <div class="col-3" style="margin-top: 8rem">
        <div class="card">
          <div class="card-header">
            Basic
          </div>
          <div class="card-body" style="text-align: left">
            <div style="text-align: center">
              <img src="../assets/logo.png" />
            </div>
            <hr class="my-4" />
            <ul>
              <li>Access to Gym at off-peak hours</li>
              <li>
                One free session with one of our Personal Trainers on your first
                visit
              </li>
              <li>
                Book into a class to get a real workout with one of our
                Instructors
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-6" style="margin-top: 6rem">
        <div class="card" style="height: 40rem">
          <div class="card-header">
            Standard
          </div>
          <div class="card-body" style="text-align: left">
            <div style="text-align: center">
              <img src="../assets/logo.png" />
            </div>
            <hr class="my-4" />
            <ul>
              <li>All the features of a Basic Membership</li>
              <li>Access to Gym at peak hours</li>
              <li>
                One free session per month with one of our Personal Trainers
              </li>
              <li>Off-peak access to the Spa and Swimming Pool</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-3" style="margin-top: 8rem">
        <div class="card">
          <div class="card-header">
            Premium
          </div>
          <div class="card-body" style="text-align: left">
            <div style="text-align: center">
              <img src="../assets/logo.png" />
            </div>
            <hr class="my-4" />
            <ul>
              <li>All the features of a Standard Membership</li>
              <li>24/7 Access to Gym</li>
              <li>
                Up to four sessions a month with a Personal Trainer of your
                choice
              </li>
              <li>Off-peak access to the Spa and Swimming Pool</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <br />
    <div class="row">
      <div class="col-12" style="text-align: center">
        <router-link
          to="/register"
          tag="button"
          style="display:inline-block;"
          class="btn btn-lg btn-outline-dark"
        >
          Sign Up
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>
