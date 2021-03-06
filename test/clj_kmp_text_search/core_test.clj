(ns clj-kmp-text-search.core-test
  (:require [clojure.test :refer :all]
            [clj-kmp-text-search.core :refer :all]))

(deftest test-backtrack-table
    (testing "Increasing matching strings"
        (is (= [0 0 0] (backtrack-table "aba")))
        (is (= [0 0 0 1] (backtrack-table "abab")))
        (is (= [0 0 0 1 2] (backtrack-table "ababa")))
        (is (= [0 0 0 0 0 1 2] (backtrack-table "abcdabd"))
        (is (= [0 0  0 1 2 3] (backtrack-table "ababac")))))
    (testing "Example strings from Wikipedia"
        (is (= [0 0 0 0 0 1 2] (backtrack-table "abcdabd")))
        (is (= [0 0 0 0 0 0 0 0 1 2 0 0 0 0 0 0 1 2 3 0 0 0 0 0] (backtrack-table "participate in parachute")))))

(deftest test-search
    (testing "Matches"
        (is (= 0 (kmp-search "joe" "joe"))
        (is (= 5 (kmp-search "fast joe" "joe")))))
    (testing "Non-matches"
        (is (= -1 (kmp-search "eric" "joe"))))
    (testing "Back tracking"
        (is (= 10 (kmp-search "jo joanna joe jolene" "joe")))))

(def large-string (str (take 10000 (repeatedly (fn [] "a")))))

(deftest test-performance
    (testing "Large strings"
        (is (= 0 (kmp-search large-string large-string))
        (is (= -1 (kmp-search (str large-string "b") (str large-string "c")))))))
