<template>
<NavbarView/>
</template>

<script>
import axios from 'axios';
import NavbarView from '@/components/NavbarView.vue';

export default {
  components: {NavbarView},
    props: {
    id: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      termini: {}
    }
  },
  created() {
    axios.get(`http://localhost:8081/termin/${this.id}`,{
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            }
        })
      .then(response => {
        this.termini = response.data;
      })
      .catch(error => {
        console.log(error);
      });
  }
}
</script>