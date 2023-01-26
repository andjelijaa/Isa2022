<template>
    <NavbarView/>
    <div class="container">
        <h1>Profil korisnika</h1>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Email</th>
                            <th scope="col">Ime</th>
                            <th scope="col">Prezime</th>
                            <th scope="col">Grad</th>
                            <th scope="col">Adresa</th>
                            <th scope="col">Drzava</th>
                            <th scope="col">Telefon</th>
                            <th scope="col">JMBG</th>
                            <th scope="col">Pol</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" v-model="user.email" disabled/></td>
                            <td><input type="text" v-model="user.ime" /></td>
                            <td><input type="text" v-model="user.prezime" /></td>
                            <td><input type="text" v-model="user.grad" /></td>
                            <td><input type="text" v-model="user.adresa" /></td>
                            <td><input type="text" v-model="user.drzava" /></td>
                            <td><input type="text" v-model="user.phone" /></td>
                            <td><input type="text" v-model="user.jmbg" /></td>
                            <td><input type="text" v-model="user.genter" /></td>
                            <td><button class="btn btn-primary" @click="update">Update</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</template>

<script>
import axios from "axios";
import NavbarView from '@/components/NavbarView.vue';
export default {
    components: {NavbarView},
    name: 'ProfilKorisnikaView',
    data() {
        return {
            user: {},
        };
    },
    methods: {
        getKorisnik() {
            axios.get('http://localhost:8081/user/get-user', {
            headers: {
                'Authorization': `Bearer ${this.$store.state.token}`,
            }
        })
                .then((response) => {
                    console.log('Profil korisnika')
                    console.log('RESPOSE DATA: ', response.data)
                    this.user = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        update() {
            axios.put('http://localhost:8081/user/update-user', this.user, {
            headers: {
                'Authorization': `Bearer ${this.$store.state.token}`,
            }
        })
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
    },
    mounted() {
        this.getKorisnik();
    },
};

</script>