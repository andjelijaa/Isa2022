<template>
  <NavbarView/>
    <div>
      <h1>Upitnik</h1>
      <form>
        <div v-for="(question, index) in questions" :key="index">
          <label>{{ question.text }}</label><br>
          <input type="radio" v-model="question.answer" value="yes">Yes
          <input type="radio" v-model="question.answer" value="no">No
        </div>
        <button @click="sacuvaj">Sacuvaj</button>
      </form>

    </div>
  </template>
  
  <script>
    import NavbarView from '@/components/NavbarView.vue';
import axios from 'axios';

  export default {
    components:{NavbarView},
    data() {
      return {
        questions: [
          { text: 'Da li ste do sada dobrovoljno davali krv', answer: 'no' },
          { text: 'Da li ste ikada bili odbijeni kao davalac krvi ili komponente krvi', answer: 'no' },
          { text: 'Da li se trenutno osecate zdravim, sposobnim i odmornim da date krv ili komponentu krvi', answer: 'no' },
          { text: 'Da li ste nesto jeli pre dolaska na davanje krvi ili komponente krvi', answer: 'no' },
          { text: 'Da li se bavite opasnim zanimanjem ili hobijem', answer: 'no' },
          { text: 'Da li ste redovno (svakodnevno) uzimali neke lekove', answer: 'no' },
          { text: 'Da li ste poslednja 2-3 dana uzimali bilo kakve lekove', answer: 'no' }
        ],
        
      }
    },
    methods: {
      sacuvaj(){
        axios.post('http://localhost:8081/api/user/questions', this.questions, {
          headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            }
        })
        .then(response => {
          console.log('Odgovor: ', response.data)
        })
        .catch(err => console.log(err))
      }
    }
  }
  </script>
  