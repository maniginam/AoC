(ns advent-of-code.2024.day3
  (:require [clojure.string :as str]))

(defn discover-multiples [memory] (re-seq #"mul\(\d{1,3},\d{1,3}\)" memory))

(defn find-each-digit-pair [pair]
  (->> pair (re-seq #"\d{1,3}") (map read-string)))

(defn find-digit-pairs [memory]
  (->> memory discover-multiples (map find-each-digit-pair)))

(def *! (partial apply *))

(defn restore-memory [memory]
  (->> memory find-digit-pairs (map *!) (apply +)))

(defn remove-donts [memory]
  (let [memory-list (str/split memory #"don\'t")
        first-do    (first memory-list)]
    (->> memory-list
         (map #(str/split % #"do"))
         (mapcat rest)
         (cons first-do)
         str/join)))

(defn restore-memory-with-dos [memory]
  (->> memory remove-donts restore-memory))
