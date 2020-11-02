import each from "lodash/each";

const prefix = "omoshiroi";

type LS_KEY = "OAUTH-TOKEN";
type LS_VALUE = any | boolean | string;

export function test() {
    each(localStorage, (value, keyIndex) => {
        console.log("value, keyIndex", value, keyIndex, localStorage.key(keyIndex));
    });
}

export const ls = {
    get(property: LS_KEY): LS_VALUE {
        const objString = localStorage[`${prefix}_${property}`] || `
        {
            "value": false
        }`;
        return JSON.parse(objString).value;
    },
    set(property: LS_KEY, value: any) {
        localStorage[`${prefix}_${property}`] = JSON.stringify({
            value
        });
        return true;
    },
    clear(property: LS_KEY | "") {
        const reg = property ? new RegExp(`^${prefix}_${property}`, "g") : new RegExp(`^${prefix}`, "g");
        each(localStorage, (value, keyIndex) => {
            const key: string = localStorage.key(keyIndex) || "";
            if (reg.test(key)) {
                delete localStorage[key];
            }
        });
    }
};