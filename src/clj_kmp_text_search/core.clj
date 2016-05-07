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

(defn kmp-search ([s target]
        (kmp-search s target (backtrack-table s) 0 0))
        ([s target table m i]
            (let [s-size (.length s)
                    current-offset (+ m i)]
                (cond
                    (> current-offset s-size)
                        -1
                    (= (.charAt target i) (.charAt s current-offset))
                        (if (= (inc i) (.length target))
                            m
                            (recur s target table m (inc i)))
                    :else
                        (let [backtrack (get table i)]
                            (if (= i 0)
                                (recur s target table (+ m i 1) 0)
                                (recur s target table (- (+ m i) backtrack) 0)))))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
