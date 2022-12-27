<template>
    <div>
        <img v-for="qrCode in qrCodes" :src="qrCode" alt="QR code" :key="qrCode.id">
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
        axios.get('http://localhost:8081/termin/qr-codes', {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            }
        })
            .then(response => {
                this.qrCodes = response.data;
            })
            .catch(error => {
                console.log(error);
            });
    }
}
</script>