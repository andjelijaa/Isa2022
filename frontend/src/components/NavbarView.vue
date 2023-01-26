<template>
     <nav>
    <router-link to="/">Centri |</router-link>
    <router-link to="/profilKorisnika" v-if="login === true">Profil Korisnika |</router-link>
    <router-link to="/istorija" v-if="login === true">Istorija Poseta |</router-link>
    <router-link to="/zakazani" v-if="login === true">Zakazane Posete |</router-link> 
    <router-link to="/qr" v-if="login === true">QR |</router-link>
    <router-link to="/upitnik" v-if="login === true">Upitnik |</router-link>
    <router-link to="/zalbe" v-if="login === true">Zalbe |</router-link>
    <router-link to="/login" v-if="login === false">Login |</router-link>
    <router-link to="/signup" v-if="login === false">SignUp</router-link>
    <router-link to="/" v-if="login === true" @click="logout">Logout</router-link>
  </nav>
</template>
<script>
export default {
    name: "NavbarView", 
    data (){
        return{ login: false}
    }
    ,
    mounted(){
        this.login= this.$store.state.token!= null ? true : false
    },
    methods: {
        logout(){
            this.$store.dispatch('setToken', null)
        }
    }
}
</script>