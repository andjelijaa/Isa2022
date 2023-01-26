<template>
  <div>
    <NavbarView />
    <div class="container">
      <h1>Istorija</h1>
      <!-- <div class="row">
        <label>Datum: </label>
        <input type="date" v-model="date" />
        <button @click="sort">Sort</button>
      </div> -->
      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">Datum</th>
                <th scope="col">Pacijent</th>
                <th scope="col">Centar</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="termin in istorija" :key="termin.id">
                <td>{{ termin.datum }}</td>
                <td>{{ termin.pacijent.ime }}</td>
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
      date: '',
    };
  },
  methods: {
    getIstorija() {
      axios
        .get(`http://localhost:8081/termin/istorija-termina`, {
          headers: {
            Authorization: `Bearer ${this.$store.state.token}`,
          },
        })
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
