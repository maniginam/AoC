(ns advent-of-code.2024.day2
  (:require [clojure.string :as str]))

(defn read-nums [nums] (map read-string nums))

(defn split-reports [reports]
  (->> (str/split-lines reports) (map #(str/split % #"\s")) (map read-nums)))

(defn all-decreasing? [deltas] (every? neg? deltas))
(defn all-increasing? [deltas] (every? pos? deltas))

(defn same-direction? [deltas] (or (all-decreasing? deltas) (all-increasing? deltas)))

(defn at-least-one? [d] (< 0 (* d d)))
(defn no-more-than-3? [d] (<= -3 d 3))

(defn safe-range? [deltas]
  (and (every? at-least-one? deltas)
       (every? no-more-than-3? deltas)))

(defn safe? [levels]
  (let [deltas (map - levels (rest levels))]
    (and (same-direction? deltas)
       (safe-range? deltas))))

(defn count-safe-reports [reports] (->> reports split-reports (filter safe?) count))

(defn safe-after-dampening? [levels]
  (let [num-of-levels (count levels)]
    (some safe? (map #(concat (subvec levels 0 %) (subvec levels (inc %))) (range num-of-levels)))))

(defn count-dampened-safe-reports [reports]
  (->> reports split-reports (map vec) (filter safe-after-dampening?) count))
