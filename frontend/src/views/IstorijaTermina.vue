<template>
    <div class="container">
        <h1>Istorija</h1>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Datum</th>
                            <th scope="col">Davalac</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="termin in istorija" :key="termin.id">
                            <td>{{ termin.datum }}</td>
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
    name: 'IstorijaTermina',
    props: ['centarId'],
    data() {
        return {
            istorija: [],
        };
    },
    methods: {
        getIstorija() {
            axios.get(`http://localhost:8081/centar/${this.centarId}/get-all-za-korisnika`)
                .then((response) => {
                    this.istorija = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        },
    },
    mounted() {
        this.getIstorija();
    },
};
</script>