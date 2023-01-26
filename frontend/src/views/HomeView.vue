<template>
  <div>
    <NavbarView></NavbarView>  
    <CentriView />
  
  </div>
</template>



<script>
  import CentriView from '@/components/CentriView.vue';
  import NavbarView from '@/components/NavbarView.vue';
  export default {
    components: {
      CentriView,
      NavbarView
    }
  }
  </script>

