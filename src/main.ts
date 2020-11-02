import { createApp } from "vue";
import "@/registerServiceWorker";
/* 路由 */
import router from "@/router";
/* 状态 */
import store from "@/store";
/* style */
import "@/assets/styles/sing/sing_main.scss";
/* 主视图 */
import App from "./App.vue";
/* 指令 */
import { vTitle } from "@/directive/title";


createApp(App)
  .directive("title", vTitle)
  .use(store)
  .use(router)
  .mount("#app");