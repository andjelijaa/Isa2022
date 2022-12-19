<template>
  <div>
    <img v-for="qrCode in qrCodes" :src="qrCode" alt="QR code">
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      qrCodes: []
    }
  },
  created() {
    axios.get('http://localhost:8081/termin/qr-codes')
      .then(response => {
        this.qrCodes = response.data;
      })
      .catch(error => {
        cpmsole.log(error);
      });
  }
}
</script>