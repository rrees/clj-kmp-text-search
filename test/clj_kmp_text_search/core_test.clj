(ns clj-kmp-text-search.core-test
  (:require [clojure.test :refer :all]
            [clj-kmp-text-search.core :refer :all]))

(deftest test-backtrack-score
    (testing "Non-matching prefixes"
        (is (= 0 (backtrack-score "ab" 1))))
    (testing "Matching prefixes"
        (is (= 2 (backtrack-score "pabpac" 3))))
    (testing "String overflow"
        (is (= 1 (backtrack-score "papp" 3)))))
