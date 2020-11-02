<template>
  <div class="home" v-title="'My Notebook'">
    <ul>
      <li>
        <a
          target="_blank"
          rel="noopener noreferrer"
          href="https://docs.github.com/cn/free-pro-team@latest/github/authenticating-to-github/creating-a-personal-access-token"
          >创建个人访问令牌</a
        >
      </li>
      <li>
        <a
          target="_blank"
          rel="noopener noreferrer"
          href="https://github.com/settings/tokens"
          >创建个人访问令牌settings/tokens</a
        >
      </li>
    </ul>
    <p>
      <pre>
        token:
        {{ token }}
      </pre>
    </p>
    <template  v-if="isShowInput">
      <input v-model="token" multiple />
      <button @click="setToken">setToken</button>
    </template>
    <template v-else>
      <button @click="removeToken">removeToken</button>
      <button @click="getUser">getUser</button>
    </template>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { ls } from "@/utils/LocalStorage";
import { fetchGithub } from "@/utils/Http";

export default defineComponent({
  name: "Home",
  methods: {
    init() {
      this.token = ls.get("OAUTH-TOKEN");
      this.isShowInput = this.token ? false : true;
    },
    setToken() {
      ls.set("OAUTH-TOKEN", this.token);
      this.isShowInput = this.token ? false : true;
    },
    removeToken() {
      ls.clear("OAUTH-TOKEN");
      this.token = "";
      this.isShowInput = true;
    },
    async getUser() {
      const res = await fetchGithub.get("/users/octocat");
      debugger;
    },
  },
  mounted() {
    this.init();
  },
  data() {
    return {
      isShowInput: true,
      token: "",
    };
  },
});
</script>
