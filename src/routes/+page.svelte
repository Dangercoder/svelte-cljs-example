<script lang="cljs">
	(ns test.core)

	(def cats [])

	(js* "$: doubled = ~{}" (* 2 (count cats)))

	(defn addCat []
	    (let [new-cat-id (+ 1 (count cats))
			  new-cat {:id 1 :name "Danger Cat"}]
		(set! cats (conj! cats new-cat))))
</script>

<h1>The Famous Cats of Narnia</h1>

<button on:click={addCat}>
	Add cat
</button>

<p>Total number of cats {doubled}</p>

<ul>
    {#each cats as cat, i}
	<li><a target="_blank" href="https://www.youtube.com/watch?v={cat.id}" rel="noreferrer">
		{i + 1}: {cat.name}
	</a></li>
{/each}
</ul>

