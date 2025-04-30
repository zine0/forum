import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router';
import '@/assets/css/global.css';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import Prism from 'prismjs';
import 'highlight.js/styles/idea.css';

const pinia = createPinia()
VueMarkdownEditor.use(vuepressTheme, {
  Prism,
});

pinia.use(piniaPluginPersistedstate)
createApp(App).use(pinia).use(router).use(VueMarkdownEditor).mount('#app')
