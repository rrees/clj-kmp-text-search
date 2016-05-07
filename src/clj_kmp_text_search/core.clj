(ns clj-kmp-text-search.core)

(defn backtrack-table ([^String s]
    (let [str-length (.length s)]
        (cond
            (= 0 str-length) []
            (= 1 str-length) [0]
            (= 2 str-length) [0 0]
            :else (backtrack-table s [0 0] 0 0))))
    ([^String s ^String ^longs t ^long last-match-idx ^long score]
        (let [table-size (count t)
            str-length (.length s)
            next-score (inc score)]
        (if (= table-size str-length)
            t
            (if (= (.charAt s last-match-idx) (.charAt s (dec table-size)))
                (recur s (conj t next-score) (inc last-match-idx) next-score)
                (if (> score 0)
                    (recur s (conj t 0) last-match-idx 0)
                    (recur s (conj t 0) 0 0)))))))

(defn kmp-search ([^String s ^String target]
        (kmp-search [s target] (backtrack-table s) 0 0))
        ([strings ^longs table ^Long m ^long i]
            (let [
                [^String s ^String target] strings
                s-size (.length s)
                ^Long current-offset (+ m i)]
                (cond
                    (> (inc current-offset) s-size)
                        -1
                    (= (.charAt target i) (.charAt s current-offset))
                        (if (= (inc i) (.length target))
                            m
                            (recur [s target] table m (inc i)))
                    :else
                        (let [backtrack (get table i)]
                            (if (= i 0)
                                (recur [s target] table (+ m i 1) 0)
                                (recur [s target] table (- (+ m i) backtrack) 0)))))))

(defn read-input []
    (let [in (slurp *in*)
      lines (clojure.string/split in #"\s")
      pairs (partition 2 (rest lines))
      search-results (map (fn [[^String s ^String t]] (kmp-search s t)) pairs)
      text-output (map (fn [^long result] (if (= result -1) "NO" "YES")) search-results)]
      (doall (map println text-output))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
