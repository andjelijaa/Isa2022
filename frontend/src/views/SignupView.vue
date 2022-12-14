<template>
    <div>

        <!-- <div class="tab-pane fade" id="pills-register" role="tabpanel" aria-labelledby="tab-register"> -->
            <form>
                <div class="text-center mb-3">
                    <p>Sign up with:</p>
                    <button type="button" class="btn btn-link btn-floating mx-1">
                        <i class="fab fa-facebook-f"></i>
                    </button>

                    <button type="button" class="btn btn-link btn-floating mx-1">
                        <i class="fab fa-google"></i>
                    </button>

                    <button type="button" class="btn btn-link btn-floating mx-1">
                        <i class="fab fa-twitter"></i>
                    </button>

                    <button type="button" class="btn btn-link btn-floating mx-1">
                        <i class="fab fa-github"></i>
                    </button>
                </div>

                <p class="text-center">or:</p>

                <!-- Name input -->
                <div class="form-outline mb-4">
                    <input type="text" id="registerName" class="form-control" />
                    <label class="form-label" for="registerName">Name</label>
                </div>

                <!-- Lastname input -->
                <div class="form-outline mb-4">
                    <input type="text" id="registerUsername" v-model="lastname" class="form-control" />
                    <label class="form-label" for="registerUsername">Last name</label>
                </div>

                <!-- Email input -->
                <div class="form-outline mb-4">
                    <input type="email" id="registerEmail" v-model="email" class="form-control" />
                    <label class="form-label" for="registerEmail">Email</label>
                </div>

                <!-- Password input -->
                <div class="form-outline mb-4">
                    <input type="password" id="registerPassword" v-model="password" class="form-control" />
                    <label class="form-label" for="registerPassword">Password</label>
                </div>

                <!-- Repeat Password input -->
                <div class="form-outline mb-4">
                    <input type="password" id="registerRepeatPassword" v-model="repassword" class="form-control" />
                    <label class="form-label" for="registerRepeatPassword">Repeat password</label>
                </div>

                <!-- Checkbox -->
                <div class="form-check d-flex justify-content-center mb-4">
                    <input class="form-check-input me-2" type="checkbox" value="" id="registerCheck" checked
                        aria-describedby="registerCheckHelpText" />
                    <label class="form-check-label" for="registerCheck">
                        I have read and agree to the terms
                    </label>
                </div>

                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-3">Sign in</button>
            </form>
        <!-- </div> -->
    </div>
</template>


<script>
import axios from 'axios'
    export default {
        name: 'SignupView',
        data() {
            return {
                email: '',
                password: '',
                repassword: '',
                name: '',
                lastname: ''
                        }
        },
        mounted() {
            if (localStorage.getItem('token')) {
                this.login = true
            }
        },
        methods: {
            signup() {
                axios.post('http://localhost:8001/api/register', {
                    email: this.email,
                    password: this.password,
                    repassword: this.repassword,
                    name: this.name,
                    lastname:~ this.lastname
                })
                    .then(response => {
                        console.log(response)
                    })
                    .catch(error => {
                        console.log(error)
                    })
            }
        }
    }
</script>