<template>
  <div>
    <NavbarView />

    <h1>Upitnik</h1>
    <div>
      <div v-for="(question, index) in questions" :key="index">
        <label>{{ question.text }}</label
        ><br />
        <input type="radio" v-model="question.answer" value="yes" />Yes
        <input type="radio" v-model="question.answer" value="no" />No
      </div>
      <button @click="sacuvaj">Sacuvaj</button>
    </div>
  </div>
</template>

<script>
import NavbarView from "@/components/NavbarView.vue";
import axios from "axios";

export default {
  components: { NavbarView },
  data() {
    return {
      questions: [
        { text: "Da li ste do sada dobrovoljno davali krv", answer: "no" },
        {
          text: "Da li ste ikada bili odbijeni kao davalac krvi ili komponente krvi",
          answer: "no",
        },
        {
          text: "Da li se trenutno osecate zdravim, sposobnim i odmornim da date krv ili komponentu krvi",
          answer: "no",
        },
        {
          text: "Da li ste nesto jeli pre dolaska na davanje krvi ili komponente krvi",
          answer: "no",
        },
        {
          text: "Da li se bavite opasnim zanimanjem ili hobijem",
          answer: "no",
        },
        {
          text: "Da li ste redovno (svakodnevno) uzimali neke lekove",
          answer: "no",
        },
        {
          text: "Da li ste poslednja 2-3 dana uzimali bilo kakve lekove",
          answer: "no",
        },
      ],
    };
  },
  methods: {
    sacuvaj() {
      axios
        .post("http://localhost:8081/user/questions", this.questions, {
          headers: {
            Authorization: `Bearer ${this.$store.state.token}`,
          },
        })
        .then((response) => {
          console.log("Odgovor: ", response.data);
          this.$router.push("/");
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>
