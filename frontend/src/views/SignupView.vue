<template>
    <NavbarView/>
    <div>

        <!-- <div class="tab-pane fade" id="pills-register" role="tabpanel" aria-labelledby="tab-register"> -->
        <form>
            <div class="text-center mb-3">
                <p>Sign up with:</p>
            </div>

            <!-- Name input -->
            <div class="form-outline mb-4">
                <input type="text" id="registerName" class="form-control" v-model="name" />
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

            <div class="form-outline mb-4">
                <input type="text" id="registerAdresa" v-model="adresa" class="form-control" />
                <label class="form-label" for="registerAdresa">Adresa</label>
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="registerGrad" v-model="grad" class="form-control" />
                <label class="form-label" for="registerGrad">Grad</label>
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="registerDrzava" v-model="drzava" class="form-control" />
                <label class="form-label" for="registerDrzava">Drzava</label>
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="registerJMBG" v-model="jmbg" class="form-control" />
                <label class="form-label" for="registerJMBG">JMBG</label>
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="registerPhone" v-model="phone" class="form-control" />
                <label class="form-label" for="registerPhone">Phone</label>
            </div>

            <div class="form-outline mb-4">
                <!-- <input type="text" id="registerPol" v-model="pol" class="form-control" /> -->
                <select class="form-select" v-model="pol">
            <option selected>Pol</option>
            <option value="M">Musko</option>
            <option value="Z">Zensko</option>
            </select>
                <label class="form-label" for="registerPol">Pol</label>
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="registerZanimanje" v-model="zanimanje" class="form-control" />
                <label class="form-label" for="registerZanimanje">Zanimanje</label>
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="registerUstanova" v-model="ustanova" class="form-control" />
                <label class="form-label" for="registerUstanova">Informacije o ustanovi</label>
            </div>

            <!-- Submit button -->
            <button type="button" class="btn btn-primary btn-block mb-3" @click="signup">Sign up</button>
        </form>
        <!-- </div> -->
    </div>
</template>


<script>
import axios from 'axios'
import NavbarView from '@/components/NavbarView.vue';
export default {
    components: {NavbarView},
    name: 'SignupView',
    data() {
        return {
            email: '',
            password: '',
            repassword: '',
            name: '',
            lastname: '',
            adresa: '',
            grad: '',
            drzava: '',
            jmbg: '',
            phone: '',
            pol: '',
            zanimanje: '',
            ustanova: '',
            polSend: 0,
        }
    },
    mounted() {
        console.log('Signup mounted.')
    },
    methods: {
        signup() {
            if (this.password !== this.repassword) {
                alert('Lose ste uneli password')
            }
            else if(this.jmbg.length != 13){
                alert('Lose ste uneli JMBG')
            } else {
                if (this.pol == 'M') {
                    this.polSend = 0
                }else if(this.pol == 'Z'){
                    this.polSend = 1
                }
                console.log(this.polSend)
                const data = {
                    email: this.email,
                    password: this.password,
                    ime: this.name,
                    prezime: this.lastname,
                    adresa: this.adresa,
                    grad: this.grad,
                    drzava: this.drzava,
                    jmbg: this.jmbg,
                    phone: this.phone,
                    pol: this.polSend,
                    zanimanje: this.zanimanje,
                    ustanova: this.ustanova,
                }
                console.log(data)
                axios.post('http://localhost:8081/api/register', data)
                    .then(response => {
                        console.log(response)
                        this.$router.push('/login')
                    })
                    .catch(error => {
                        console.log(error)
                    })
            }
        }
    }
}
</script>