import { createApp } from "vue";
import App from "./App.vue";
import "@/registerServiceWorker";
import router from "@/router";
import store from "@/store";
import "@/assets/styles/sing/sing_main.scss";
import { ls } from "@/utils/LocalStorage";

createApp(App).use(store).use(router).mount("#app");
/* https://docs.github.com/cn/free-pro-team@latest/github/authenticating-to-github/creating-a-personal-access-token */