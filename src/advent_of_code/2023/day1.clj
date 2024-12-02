(ns advent-of-code.2023.day1
  (:require [clojure.edn :as edn]
            [clojure.string :as str]))

(def word->num {"one" 1 "two" 2 "three" 3 "four" 4 "five" 5 "six" 6 "seven" 7 "eight" 8 "nine" 9})
(def word-nums (set (keys word->num)))

(defn ->words [chars]
  (let [words (map #(str/join (take % chars)) (reverse (range 3 8)))]
    (first (filter #(contains? word-nums %) words))))

(defn get-digits [line]
  (prn "line: " line)
  (let [identities (keep identity line)
        chars      (map #(-> % str edn/read-string) identities)
        digits []]
    (loop [test-chars (take 5 chars)
           remaining  (rest chars)
           digits     digits]
      (when (empty? test-chars) (prn "digits: " digits))
      (if (empty? test-chars)
        digits
        (let [word (delay (->words test-chars))]
          (cond
            (number? (first test-chars)) (recur (take 5 remaining) (rest remaining) (conj digits (first test-chars)))
            @word (recur (take 5 (drop (dec (count @word)) remaining)) (drop (dec (count @word)) remaining) (conj digits (get word->num @word)))
            :else (recur (take 5 remaining) (rest remaining) digits)))))))

(defn find-double-digit [line]
  (let [[d1 :as digits] (get-digits line)]
    (prn "(read-string (str d1 (last digits))): " (read-string (str d1 (last digits))))
    (read-string (str d1 (last digits)))))

(defn sum-calibration-nums [doc]
  (->> doc
    str/split-lines
    (map find-double-digit)
    (apply +)))