# svelte-cljs
Using [squint](https://github.com/squint-cljs/squint) and Svelte.

## How does it work?
This repo defines a [svelte preprocessor](https://kit.svelte.dev/docs/integrations#preprocessors) for cljs which allows you to use clojurescript within `<script lang="cljs">` blocks.

[source](./svelte.config.js)

## Setup
`npm install`  
`npm run dev`

## Unsolved/Known issues
Does not emit `export let` javascript which is needed for [svelte props](https://svelte.dev/tutorial/declaring-props)

Does not emit `$:` javascript which is needed for [svelte reactivity](https://svelte.dev/tutorial/reactive-declarations)

