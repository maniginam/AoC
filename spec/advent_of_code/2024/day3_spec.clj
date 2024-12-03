(ns advent-of-code.2024.day3-spec
  (:require 
  [speclj.core :refer :all]
  [advent-of-code.2024.day3 :as sut]
  [advent-of-code.2024.inputs.day3 :refer :all]))

(def ememory "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+don'tdodon'tmul(32,64]don't(mul(11,8)don'tundododo()?mul(8,5))")

(describe "Day 3"

  (it "finds all the muls"
    (should= ["mul(2,4)" "mul(5,5)" "mul(11,8)" "mul(8,5)"] (sut/discover-multiples ememory)))

  (it "finds-digit-pairs"
    (should= [[2 4] [5 5] [11 8] [8 5]] (sut/find-digit-pairs ememory)))

  (it "adds up the digits"
    (should= 161 (sut/restore-memory ememory))
    (should= 160672468 (sut/restore-memory memory)))

  (it "splits for do's & dont's"
    (should= [[2 4] [8 5]] (-> (sut/remove-donts ememory) sut/find-digit-pairs))
    (should= [[283 734] [961 154] [840 357] [47 948] [331 33]] (take 5 (drop 20 (-> (sut/remove-donts memory) sut/find-digit-pairs))))
    )

  (it "restores memory with dos"
    (should= 48 (sut/restore-memory-with-dos ememory))
    (should= 84893551 (sut/restore-memory-with-dos memory)))

  )