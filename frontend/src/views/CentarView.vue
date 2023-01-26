<template>
  <div>
    <NavbarView />
    <button class="btn" @click="sortCentri">Sort po datumu</button>
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Datum</th>
          <th scope="col">Status</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="termin in termini" :key="termin.id">
          <td>{{ termin.datum }}</td>
          <td>{{ termin.status }}</td>
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
  components: { NavbarView },
  data() {
    return {
      termini: [],
      sort: "",
    };
  },
  mounted() {
    let id = this.$route.params.id;
    const data = {
      datum: false,
    };
    console.log("Mounted CentarView");
    console.log("id:", id);
    axios
      .get(`http://localhost:8081/centar/${id}/get-slobodni-termini`, {
        params: data,
        headers: {
          Authorization: `Bearer ${this.$store.state.token}`,
        },
      })
      .then((response) => {
        console.log("CentarView");
        console.log("data: ", response.data);
        this.termini = response.data;
        console.log("termini: ", this.termini);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    zakazi(id) {
      console.log('CentarView')
      console.log('id: ', id)
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
          if(response.data == false){
            alert('Ne mozete da zakazete novi termin')
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    sortCentri() {
      let id = this.$route.params.id;
      const data = {
        datum: true,
      };
      console.log("Mounted CentarView");
      console.log("id:", id);
      axios
        .get(`http://localhost:8081/centar/${id}/get-slobodni-termini`, {
          params: data,
          headers: {
            Authorization: `Bearer ${this.$store.state.token}`,
          },
        })
        .then((response) => {
          console.log("CentarView");
          console.log("data: ", response.data);
          this.termini = response.data;
          console.log("termini: ", this.termini);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
