<script lang="cljs">
    (def answer)

	(js* "$: doubled = ~{}" (* 2 answer))

</script>

<p>The answer is {answer}</p>
<p>And the doubled answer is: {doubled}</p>
