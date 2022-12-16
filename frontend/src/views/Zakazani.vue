<template>
    <div class="container">
        <h1>Zakazani</h1>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Termin</th>
                            <th scope="col">Davalac</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="termin in zakazani" :key="termin.id">
                            <td>{{ termin.termin }}</td>
                            <td>{{ termin.davalac.ime }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>


<script>
import axios from 'axios';

export default {
    name: 'Zakazani',
    props: ['centarId'],
    data() {
        return {
            zakazani: [],
        };
    },
    methods: {
        getZakazani() {
            axios.get(`http://localhost:8081/centar/${this.centarId}/get-zakazane-posete`)
                .then((response) => {
                    this.zakazani = response.data;
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