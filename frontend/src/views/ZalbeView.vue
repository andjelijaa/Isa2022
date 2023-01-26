<template>
    <NavbarView/>
    <select class="form-select" v-model="prijava">
            <option v-for="(centar, index) in centri" value="centar.id" :key="index">{{centar.id}}</option>
            <option v-for="(zaposlen, index) in zaposleni" value="zaposlen.id" :key="index">{{zaposlen.id}}</option>
            </select>
    <div class="form-floating">
        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 150px"
            v-model="zalba"></textarea>
        <label for="floatingTextarea2">Zalba</label>
        <br />
        <button class="btn btn-primary" @click="submitZalba" >Posalji zalbu</button>
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
            zaposleni: []
        };
    },
    methods: {
        getCentri(){
            axios.get('http://localhost:8081/centri/get-centri-za-zalbe', {
                headers: {
                    'Authorization': `Bearer ${this.$store.state.token}`,
                } 
            })
            .then(res => {
                console.log('ZalbeView')
                this.centri = res.data
                console.log('Centri: ', this.centri)
            })
        },
        submitZalba() {
            axios.post('http://localhost:8081/zalba/add', {
                text: this.zalba,

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
        this.getCentri()
        this.zaposleni = this.centri
                            .flatMap(centar => centar.zaposleni)
    }
}
</script>