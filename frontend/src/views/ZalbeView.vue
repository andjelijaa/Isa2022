<template>
    <div class="form-floating">
        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 150px"
            v-model="zalba"></textarea>
        <label for="floatingTextarea2">Zalba</label>
    </div>
</template>


<script>
import axios from 'axios';

export default {
    name: 'ZalbeView',
    data() {
        return {
            zalba: '',
        };
    },
    methods: {
        submitZalba() {
            axios.post('http://localhost:8081/zalba/add', {
                text: this.zalba,
            }, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`,
                }
            })
                .then(response => {
                    console.log(response);
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }
}
</script>