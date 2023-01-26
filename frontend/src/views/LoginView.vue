<template>
    <NavbarView/>
    <div>
        <div class="tab-content">
            <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                <form>
                    <div class="text-center mb-3">
                        <p>Sign in with:</p>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="email" id="loginName" class="form-control" v-model="email"/>
                        <label class="form-label" for="loginName">Email</label>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-4">
                        <input type="password" id="loginPassword" class="form-control" v-model="password"/>
                        <label class="form-label" for="loginPassword">Password</label>
                    </div>


                    <!-- Submit button -->
                    <button type="button" class="btn btn-primary btn-block mb-4" @click="login">Sign in</button>
                </form>
            </div>

        </div>
    </div>
</template>

<script>

import axios from 'axios' 
import NavbarView from '@/components/NavbarView.vue';

export default {
    components:{ NavbarView
    },
    data() {
        return {
            email: '',
            password: ''
        }
    },
        methods: {
        login() {
            console.log('login')
            // log email and password to console
            console.log(this.email)
            console.log(this.password)
            axios.post('http://localhost:8081/api/login', {
                email: this.email,
                password: this.password
            })
            .then(response => {
                console.log(response)
                this.$store.dispatch('setToken', response.data)
                this.$router.push({ name: 'home' })
            })
            .catch(error => {
                console.log(error)
                alert('Login failed')
            })
        }
    }   
}
</script>