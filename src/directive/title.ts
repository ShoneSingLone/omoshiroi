/* https://v3.vuejs.org/guide/migration/custom-directives.html#_2-x-syntax */
type bind = {
  value: string;
}
export const vTitle = {
  mounted: (el: HTMLElement, bind: bind) => document.title = bind.value || ""
};

