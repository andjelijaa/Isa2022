<template>
  <nav>
    <router-link to="/">Centri</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/profilKorisnika" v-if="login === true">Profil Korisnika</router-link> |
    <router-link to="/istorija" v-if="login === true">Istorija Poseta</router-link> |
    <router-link to="/zakazane" v-if="login === true">Zakazane Posete</router-link> |
    <router-link to="/qr" v-if="login === true">QR</router-link> |
    <router-link to="/login">Login</router-link> |
    <router-link to="/signup">SignUp</router-link>
  </nav>
  <router-view />
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

<script>
export default {
  name: 'App',
  data() {
    return {
      login: false,
    };
  },
  mounted() {
    console.log('App mounted');
    const token = localStorage.getItem('token');
    if (token) {
      this.login = true;
    }

  },
};
</script>
