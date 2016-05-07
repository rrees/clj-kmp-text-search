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
