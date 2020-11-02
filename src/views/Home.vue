<template>
  <div class="btn">btn</div>
  <router-link to="/">Home</router-link> |
  <router-link to="/about">About</router-link>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "Home",
});
</script>
