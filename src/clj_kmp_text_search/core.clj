(ns clj-kmp-text-search.core)

(defn backtrack-table ([s]
    (let [str-length (.length s)]
        (cond
            (= 0 str-length) []
            (= 1 str-length) [0]
            (= 2 str-length) [0 0]
            :else (backtrack-table s [0 0] 0 0))))
    ([s t last-match-idx score]
        (let [table-size (count t)
            str-length (.length s)]
        (if (= table-size str-length)
            t
            (if (= (.charAt s last-match-idx) (.charAt s (dec table-size)))
                (recur s (conj t (inc score)) (inc last-match-idx) (inc score))
                (if (> score 0)
                    (recur s (conj t 0) last-match-idx 0)
                    (recur s (conj t 0) 0 0)))))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
