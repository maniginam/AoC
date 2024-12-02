(ns advent-of-code.2024.day1-spec
  (:require 
  [speclj.core :refer :all]
  [advent-of-code.2024.day1 :as sut]
  [advent-of-code.2024.inputs.day1 :refer :all]
  ))

(def rows (count lists))

(def list-1 (map #(nth lists %) (filter even? (range rows))))
(def list-2 (map #(nth lists %) (filter odd? (range (inc rows)))))
(def elist-1 [3 4 2 1 3 3])
(def elist-2 [4 3 5 3 9 3])

(describe "Day 1"

  (it "splits lists"
    (should= (first lists) (first list-1))
    (should= (last (butlast lists)) (last list-1))
    (should= (second lists) (first list-2))
    (should= (last lists) (last list-2)))

  (it "adds up distances"
    (should= 11 (sut/find-total-distance elist-1 elist-2))
    (should= 1580061 (sut/find-total-distance list-1 list-2)))


  (it "finds the product"
    (should= 31 (sut/find-similarity-score elist-1 elist-2))
    (should= 23046913 (sut/find-similarity-score list-1 list-2)))
  )

