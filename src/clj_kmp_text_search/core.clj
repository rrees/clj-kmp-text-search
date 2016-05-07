(ns clj-kmp-text-search.core)

(defn backtrack-score ([s i]
    (backtrack-score (seq s) (drop i (seq s)) 0))
    ([prefix_chars suffix_chars score]
        (let [_ (str prefix_chars " " suffix_chars "" score)]
        (if (empty? suffix_chars)
            score
            (if (= (first prefix_chars) (first suffix_chars))
                (recur (rest prefix_chars) (rest suffix_chars) (inc score))
                score)))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
