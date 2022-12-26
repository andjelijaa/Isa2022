<template>
    <div></div>
</template>

<script>
import axios from 'axios';

export default {
    props: {
        id: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            termini: [],
        }
    },
    created() {
        axios.get(`http://localhost:8081/centar/${this.id}/get-slobodni-termini`, {
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