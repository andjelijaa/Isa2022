<template>
  <div>
    <select
      class="form-select"
      aria-label="Default select example"
      v-model="sort"
    >
      <option value="Grad">Grad</option>
      <option value="Ocena">Ocena</option>
    </select>
    <button class="btn" @click="sortCentri">Sort</button>
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
                <th scope="col">ID</th>
                <th scope="col">Grad</th>
                <th scope="col">Adresa</th>
                <th scope="col">Drzava</th>
                <th scope="col">Telefon</th>
                <th scope="col">Ocena</th>
                <th scope="col" v-if="login === true"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="centro in centri" :key="centro.id">
                <td>{{ centro.id }}</td>
                <td>{{ centro.grad }}</td>
                <td>{{ centro.adresa }}</td>
                <td>{{ centro.drzava }}</td>
                <td>{{ centro.phone }}</td>
                <td>{{ centro.ocena }}</td>
                <td v-if="login === true">
                  <router-link
                    :to="{ name: 'centar', params: { id: centro.id } }"
                    >GO</router-link
                  >
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "CentriView",
  data() {
    return {
      centri: [],
      sort: '',
      token: "",
      login: "",
    };
  },
  methods: {
    sortCentri() {
      this.token = this.$store.state.token;
      this.login = this.token != null ? true : false;
      let data = {};
    if(this.sort == 'Grad'){
        data = {
        grad: true,
        ocena: false,
    }}
    if(this.sort == 'Ocena'){
        data = {
        grad: false,
        ocena: true,
    }}
      console.log("data:");
      console.log(this.sort);
      console.log("token:");
      console.log(this.token);
      axios
        .get("http://localhost:8081/centar/get-all", { params: data })
        .then((response) => {
          console.log("response:");
          console.log(response);
          this.centri = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.token = this.$store.state.token;
    this.login = this.token != null ? true : false;
    const data = {
        grad: true,
        ocena: false,
    };
    console.log("data:");
    console.log(data);
    console.log("token:");
    console.log(this.token);
    axios
      .get("http://localhost:8081/centar/get-all", { params: data })
      .then((response) => {
        console.log("response:");
        console.log(response);
        this.centri = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
