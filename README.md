# svelte-cljs
Using [squint](https://github.com/squint-cljs/squint) and Svelte.

## How does it work?
This repo defines a [svelte preprocessor](https://kit.svelte.dev/docs/integrations#preprocessors) for cljs which allows you to use clojurescript within `<script lang="cljs">` blocks - [source](./svelte.config.js)

## Setup
`npm install`  
`bb dev`

## Current 'aha-s'
- Macros are not compiled when calling squint compileString. There's a fix in place in this branch of the squint compiler:
  https://github.com/squint-cljs/compiler-common/tree/svelte-macros