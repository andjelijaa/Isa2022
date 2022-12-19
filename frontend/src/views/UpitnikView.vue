<template>
    <div>
      <h1>Upitnik</h1>
      <form>
        <div v-for="(question, index) in questions" :key="index">
          <label>{{ question.text }}</label><br>
          <input type="radio" v-model="question.answer" value="yes">Yes
          <input type="radio" v-model="question.answer" value="no">No
        </div>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        questions: [
          { text: 'Da li ste do sada dobrovoljno davali krv', answer: '' },
          { text: 'Da li ste ikada bili odbijeni kao davalac krvi ili komponente krvi', answer: '' },
          { text: 'Da li se trenutno osecate zdravim, sposobnim i odmornim da date krv ili komponentu krvi', answer: '' },
          { text: 'Da li ste nesto jeli pre dolaska na davanje krvi ili komponente krvi', answer: '' },
          { text: 'Da li se bavite opasnim zanimanjem ili hobijem', answer: '' },
          { text: 'Da li ste redovno (svakodnevno) uzimali neke lekove', answer: '' },
          { text: 'Da li ste poslednja 2-3 dana uzimali bilo kakve lekove', answer: '' }
        ]
      }
    }
  }
  </script>
  