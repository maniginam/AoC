(ns advent-of-code.2024.day2-spec
  (:require 
  [speclj.core :refer :all]
  [advent-of-code.2024.day2 :as sut]
  [advent-of-code.2024.inputs.day2 :refer :all]))

(def ereports
  "7 6 4 2 1\n1 2 7 8 9\n9 7 6 2 1\n1 3 2 4 5\n8 6 4 4 1\n1 3 6 7 9")

(describe "Day 2"

  (it "separates reports"
    (should=
     [[7 6 4 2 1]
      [1 2 7 8 9]
      [9 7 6 2 1]
      [1 3 2 4 5]
      [8 6 4 4 1]
      [1 3 6 7 9]] (sut/split-reports ereports)))


  (it "checks for safety"
    (should (sut/safe? [7 6 4 2 1]))
    (should-not (sut/safe? [1 2 7 8 9]))
    (should-not (sut/safe? [9 7 6 2 1]))
    (should-not (sut/safe? [1 3 2 4 5]))
    (should-not (sut/safe? [8 6 4 4 1]))
    (should (sut/safe? [1 3 6 7 9])))

  (it "counts safe reports"
    (should= 2 (sut/count-safe-reports ereports))
    (should= 390 (sut/count-safe-reports reports)))

  (it "checks for safety with dampening"
    (should (sut/safe-after-dampening? [7 6 4 2 1]))
    (should-not (sut/safe-after-dampening? [1 2 7 8 9]))
    (should-not (sut/safe-after-dampening? [9 7 6 2 1]))
    (should (sut/safe-after-dampening? [1 3 2 4 5]))
    (should (sut/safe-after-dampening? [8 6 4 4 1]))
    (should (sut/safe-after-dampening? [1 3 6 7 9])))

  (it "counts dampened safe reports"
    (should= 4 (sut/count-dampened-safe-reports ereports))
    (should= 439 (sut/count-dampened-safe-reports reports)))
  )