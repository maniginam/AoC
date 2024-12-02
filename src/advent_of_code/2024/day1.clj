(ns advent-of-code.2024.day1)

(defn abs [v] (if (pos? v) v (* -1 v)))

(defn find-total-distance [list-1 list-2]
  (->> (map sort [list-1 list-2])
       (apply map -)
       (map abs)
       (apply +)))

(defn multiply-count [num right]
  (* num (count (filter #(= num %) right))))

(defn find-similarity-score [left right]
  (apply + (map #(multiply-count % right) left)))
