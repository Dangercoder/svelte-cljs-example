import adapter from '@sveltejs/adapter-auto';
import { vitePreprocess } from '@sveltejs/kit/vite'
import sveltePreprocess from 'svelte-preprocess';
import { compileString } from 'squint-cljs'

/** @type {import('@sveltejs/kit').Config} */
const config = {
	// Consult https://kit.svelte.dev/docs/integrations#preprocessors
	// for more information about preprocessors

	preprocess: [vitePreprocess(),
		sveltePreprocess({
			aliases: [
			  ['cljs', 'clojurescript'],
			],
			/** Add a custom language preprocessor */
			cljs({ content, filename, attributes }) {

			  var code = compileString(content);
			  
			  return {code};
			},
		  })],

	kit: {
		adapter: adapter()
	}
};

export default config;
