<template>
    <div>
        <NavbarView/>
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">Datum</th>
            <th scope="col">Status</th>
            <th scope="col">Pacijent</th>
            <th scope="col">Doktor</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="termin in termini" :key="termin.id">
            <td>{{ termin.datum }}</td>
            <td>{{ termin.status }}</td>
            <td>{{ termin.pacijent.ime }}</td>
            <td>{{ termin.doktor.ime }}</td>
            <button class="btn btn-primary" @click="zakazi(termin.id)">
              Zakazi
            </button>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import NavbarView from "@/components/NavbarView.vue";
  export default {
    components:{NavbarView},
    data() {
      return {
        termini: [],
      };
    },
    mounted() {
        let id=this.$route.params.id
      console.log('Mounted CentarView')
      console.log("id:", id);
      axios
        .get(`http://localhost:8081/centar/${id}/get-slobodni-termini`, {
          headers: {
            Authorization: `Bearer ${this.$store.state.token}`,
          },
        })
        .then((response) => {
          console.log('CentarView')
          this.termini = response.data;
          console.log('termini: ', this.termini)
        })
        .catch((error) => {
          console.log(error);
        });
    },
    methods: {
      zakazi(id) {
        axios
          .post(
            `http://localhost:8081/termin/${id}/zakazi`,
            {},
            {
              headers: {
                Authorization: `Bearer ${this.$store.state.token}`,
              },
            }
          )
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
      },
    },
  };
  </script>