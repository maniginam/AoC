(ns advent-of-code.2022.day-1)

(def add (partial apply +))
(def add-all (partial map add))
(def top-three (partial take 3))

(defn max-calories [calories] (apply max calories))

(defn find-biggest-load [elf-loads] (-> elf-loads add-all max-calories))

(defn sum-top-three-loads [elf-loads]
  (->> elf-loads
       add-all
       sort
       reverse
       top-three
       add))