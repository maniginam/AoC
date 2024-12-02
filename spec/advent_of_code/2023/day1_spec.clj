(ns advent-of-code.2023.day1-spec
  (:require
   [clojure.string :as str]
   [speclj.core :refer :all]
   [advent-of-code.2023.day1 :as sut]
   [advent-of-code.2023.inputs.day1 :as inputs]))

(def test-doc
  "1abc2
   pqr3stu8vwx
   a1b2c3d4e5f
   treb7uchet")

(def test-doc2
  "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen")

(def split-lines (str/split-lines test-doc))
(def split-lines2 (str/split-lines test-doc2))

(describe "Day 1"

  (context "star1"
    (it "gets each line's number"
    (let [[line1 line2 line3 line4] split-lines]
      (should= 12 (sut/find-double-digit line1))
      (should= 38 (sut/find-double-digit line2))
      (should= 15 (sut/find-double-digit line3))
      (should= 77 (sut/find-double-digit line4))))

  (it "sums the calibration numbers"
    (let [[line1 line2 line3] split-lines]
      (should= 12 (sut/sum-calibration-nums line1))
      (should= 50 (sut/sum-calibration-nums (str line1 "\n" line2)))
      (should= 65 (sut/sum-calibration-nums (str line1 "\n" line2 "\n" line3)))
      (should= 142 (sut/sum-calibration-nums test-doc))
      #_(should= 53921 (sut/sum-calibration-nums inputs/calibration-data)))))

  (context "star2"

    (it "gets each line's number"
      (let [[line1 line2 line3 line4 line5 line6 line7] split-lines2]
        (should= 29 (sut/find-double-digit line1))
        (should= 83 (sut/find-double-digit line2))
        (should= 13 (sut/find-double-digit line3))
        (should= 24 (sut/find-double-digit line4))
        (should= 42 (sut/find-double-digit line5))
        (should= 14 (sut/find-double-digit line6))
        (should= 76 (sut/find-double-digit line7))))

    (it "sums the calibration numbers"
      (let [[line1 line2 line3 line4 line5 line6] split-lines2]
        (should= 29 (sut/sum-calibration-nums line1))
        (should= (+ 29 83) (sut/sum-calibration-nums (str line1 "\n" line2)))
        (should= (+ 29 83 13) (sut/sum-calibration-nums (str line1 "\n" line2 "\n" line3)))
        (should= (+ 29 83 13 24) (sut/sum-calibration-nums (str line1 "\n" line2 "\n" line3 "\n" line4)))
        (should= (+ 29 83 13 24 42) (sut/sum-calibration-nums (str line1 "\n" line2 "\n" line3 "\n" line4 "\n" line5)))
        (should= (+ 29 83 13 24 42 14) (sut/sum-calibration-nums (str line1 "\n" line2 "\n" line3 "\n" line4 "\n" line5 "\n" line6)))
        (should= 281 (sut/sum-calibration-nums test-doc2))
        (should= (+ 19 26) (sut/sum-calibration-nums "dssmtmrkonedbbhdhjbf9hq\n2njsevenszzsfltconesixhsflpbpd"))
        (should= 53921 (sut/sum-calibration-nums inputs/calibration-data)))))
  )