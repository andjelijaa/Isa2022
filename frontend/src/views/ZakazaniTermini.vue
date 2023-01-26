<template>
    <NavbarView/>
    <div class="container">
        <h1>Zakazani</h1>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Termin</th>
                            <th scope="col">Davalac</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="termin in zakazani" :key="termin.id">
                            <td>{{ termin.termin }}</td>
                            <td>{{ termin.pacijent.ime }}</td>
                            <td><button @click="otkazi(termin.id)">Otkazi</button></td>
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
    components:{NavbarView},
    name: 'ZakazaniTermini',
    props: ['centarId'],
    data() {
        return {
            zakazani: [],
        };
    },
    methods: {
        getZakazani() {
            axios.get(`http://localhost:8081/centar/${this.centarId}/get-zakazane-posete`
           , {
            headers: {
              Authorization: `Bearer ${this.$store.state.token}`,
            },
          }
        )    
            .then((response) => {
                    this.zakazani = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        },
    otkazi(id) {
      axios
        .delete(`http://localhost:8081/termin/${id}/otkazi`, {
            headers: {
                Authorization: `Bearer ${this.$store.state.token}`,
            },
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
        this.getZakazani();
    },
};
</script>