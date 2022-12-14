<template>
    <div>
        <select class="form-select" aria-label="Default select example" v-model="sort">
            <option selected>Sort</option>
            <option value="Naziv">Naziv</option>
            <option value="Grad">Grad</option>
            <option value="Ocena">Ocena</option>
        </select>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1>Centri</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Grad</th>
                                <th scope="col">Adresa</th>
                                <th scope="col">Drzava</th>
                                <th scope="col">Telefon</th>
                                <th scope="col">Ocena</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="centro in centri" :key="centro.id">
                                <td>{{ centro.grad }}</td>
                                <td>{{ centro.adresa }}</td>
                                <td>{{ centro.drzava }}</td>
                                <td>{{ centro.phone }}</td>
                                <td>{{ centro.ocena }}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'Centri',
    data() {
        return {
            centri: [],
            sort: ''
        }
    },
    mounted() {
        axios.get('http://localhost:8081/centar/get-all')
            .then(response => {
                this.centri = response.data
            })
            .catch(error => {
                console.log(error)
            })
    }
}
</script>