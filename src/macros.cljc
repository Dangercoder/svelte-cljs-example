(ns macros)

(defmacro defreactive [name init]
  `(js* "$: ~{} = ~{}" ~name ~init))