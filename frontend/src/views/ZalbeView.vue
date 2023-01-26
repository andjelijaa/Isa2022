<template>
    <div>
        <NavbarView/>
    <select class="form-select" v-model="prijava">
            <option v-for="(centar, index) in centri" :value="centar.id" :key="index">{{centar.adresa}}</option>
            <option v-for="(zaposlen, index) in zaposleni" :value="zaposlen.ime" :key="index">{{zaposlen.ime}}</option>
            </select>
    <div class="form-floating">
        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 150px"
            v-model="zalba"></textarea>
        <label for="floatingTextarea2">Zalba</label>
        <br />
        <button class="btn btn-primary" @click="submitZalba" >Posalji zalbu</button>
    </div>
    <div>
        <div class="row">
        <div class="col-12">
          <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Text</th>
                <th scope="col">Korisnik</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="zalba in zalbe" :key="zalba.id">
                <td>{{ zalba.id }}</td>
                <td>{{ zalba.tekst }}</td>
                <td>{{ zalba.userEmail}}</td>              
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    </div>
</template>


<script>
import axios from 'axios';
import NavbarView from '@/components/NavbarView.vue';

export default {
    components: {NavbarView},
    name: 'ZalbeView',
    data() {
        return {
            zalba: '',
            centri: [],
            prijava: '',
            zaposleni: [],
            zalbe: []
        };
    },
    methods: {
        getCentri(){
            axios.get('http://localhost:8081/centar/get-centri-za-zalbe', {
                headers: {
                    'Authorization': `Bearer ${this.$store.state.token}`,
                } 
            })
            .then(res => {
                console.log('ZalbeView')
                console.log('res data: ', res.data)
                this.centri = res.data
                console.log('Centri: ', this.centri)
            })
        },
        submitZalba() {
            axios.post('http://localhost:8081/zalba/add', {
                text: this.prijava + ' - ' +  this.zalba,

            }, {
                headers: {
                    'Authorization': `Bearer ${this.$store.state.token}`,
                }
                
            })
                .then(response => {
                    console.log('Zalbe')
                    console.log('data: ', response)
                })
                .catch(error => {
                    console.log(error);
                });
        }
    },
    mounted() {
        axios.get('http://localhost:8081/centar/get-centri-za-zalbe', {
                headers: {
                    'Authorization': `Bearer ${this.$store.state.token}`,
                } 
            })
            .then(res => {
                console.log('ZalbeView')
                console.log('res data: ', res.data)
                this.centri = res.data
                console.log('Centri: ', this.centri)
            })

            axios.get('http://localhost:8081/centar/get-zaposleni-za-zalbe', {
                headers: {
                    'Authorization': `Bearer ${this.$store.state.token}`,
                } 
            })
            .then(res => {
                console.log('ZalbeView')
                console.log('res zaposleni data: ', res.data)
                this.zaposleni = res.data
                console.log('Zaposleni: ', this.zaposleni)
            })

            // sve zalbe
            axios.get('http://localhost:8081/zalba/get-all', {
                headers: {
                    'Authorization': `Bearer ${this.$store.state.token}`,
                } 
            })
            .then(res => {
                console.log('ZalbeView')
                console.log('res zalbe data: ', res.data)
                this.zalbe = res.data
                console.log('zalbe: ', this.zalbe)
            })
        
    }
}
</script>