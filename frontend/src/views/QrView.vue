<template>
    <NavbarView/>
    <div>
        <img v-for="qrCode in qrCodes" :src="qrCode" alt="QR code" :key="qrCode.id">
    </div>
</template>

<script>
import axios from 'axios';
import NavbarView from '@/components/NavbarView.vue';

export default {
    components:{NavbarView},
    data() {
        return {
            qrCodes: []
        }
    },
    created() {
        axios.get('http://localhost:8081/termin/qr-codes', {
            headers: {
                'Authorization': `Bearer ${this.$store.state.token}`,
            }
        })
            .then(response => {
                console.log('QR')
                console.log('data: ', response.data)
                this.qrCodes = response.data;
            })
            .catch(error => {
                console.log(error);
            });
    }
}
</script>