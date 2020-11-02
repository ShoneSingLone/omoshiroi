import axios from "axios";
import router from "@/router";
import qs from "qs";
import isPlainObject from "lodash/isPlainObject";
import { ls } from "./LocalStorage";
import { fetchGithubBaseURL } from "@/utils/Configs";

export const fetchGithub = axios.create({
    baseURL: fetchGithubBaseURL,
    timeout: 1000 * 180,
    withCredentials: true
});

/**
 * 请求拦截
 */

fetchGithub.interceptors.request.use(config => {
    const token = ls.get("OAUTH-TOKEN");
    if (token) {
        config.headers["Authorization"] = `token ${token}`;
    }
    config.headers["Accept"] = "application/vnd.github.v3.full+json";
    config.headers["Access-Control-Allow-Origin"] = "*";

    // 默认参数
    const defaults = {};
    if (isPlainObject(config.params)) {
        config.params = {
            ...defaults,
            ...config.params
        };
    }
    if (isPlainObject(config.data)) {
        config.data = {
            ...defaults,
            ...config.data
        };
        if (/^application\/x-www-form-urlencoded/.test(config.headers["content-type"])) {
            config.data = qs.stringify(config.data);
        }
    }
    return config;
}, Promise.reject.bind(Promise));

/**
 * 响应拦截
 */

fetchGithub.interceptors.response.use((response: any) => {
    debugger;
    if (response.data.code === 401 || response.data.code === 10001) {
        router.replace({
            name: "login"
        });
        return Promise.reject(response.data.msg);
    }
    return response;
}, Promise.reject.bind(Promise));