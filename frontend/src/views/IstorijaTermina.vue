<template>
  <div>
    <NavbarView />
    <button class="btn" @click="sortCentri">Sort po datumu</button>

    <div class="container">
      <h1>Istorija</h1>
      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">Datum</th>
                <th scope="col">Centar</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="termin in istorija" :key="termin.id">
                <td>{{ termin.datum }}</td>
                <td>{{ termin.centar.id }}</td>
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
import NavbarView from "@/components/NavbarView.vue";

export default {
  name: "IstorijaTermina",
  components: { NavbarView },
  data() {
    return {
      istorija: [],
    };
  },
  methods: {
    getIstorija() {
        const data = {
        datum: false,
      };
      axios
        .get(`http://localhost:8081/termin/istorija-termina`, {
            params: data,
            headers: {
            Authorization: `Bearer ${this.$store.state.token}`,
          },
        })
        .then((response) => {
            console.log('Istorija termina')
            console.log('data: ', response.data)
          this.istorija = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    sortCentri() {
      const data = {
        datum: true,
      };
      console.log("IstorijaView");
      axios
        .get(`http://localhost:8081/termin/istorija-termina`, {
          params: data,
          headers: {
            Authorization: `Bearer ${this.$store.state.token}`,
          },
        })
        .then((response) => {
          console.log("IstorijaView");
          console.log("data: ", response.data);
          this.istorija = response.data;
          console.log("termini: ", this.termini);
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
