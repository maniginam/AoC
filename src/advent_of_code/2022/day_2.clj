(ns advent-of-code.2022.day-2)

(def rpc-value {"A" 1 "B" 2 "C" 3 "X" 1 "Y" 2 "Z" 3})
(def outcome-score {"X" 0 "Y" 3 "Z" 6})
(def wins #{1 -2})

(defn calc-score [my-play outcome]
	(+ (get rpc-value my-play) outcome))

(defn score-round
	([play] (apply score-round play))
	([elf me]
	 (let [diff (- (get rpc-value me) (get rpc-value elf))]
		(cond (zero? diff) 3
					(contains? wins diff) 6
					:else 0))))
(defn rps-tournament [plays]
	(let [my-plays (map last plays)]
		(->> (map score-round plays)
				 (map calc-score my-plays)
				 (apply +)))
	)

(def win-plays {"A" "B" "B" "C" "C" "A"})
(def loss-plays (zipmap (vals win-plays) (keys win-plays)))
(def win? (partial = 6))
(defn get-outcome-score [[_ result]] (get outcome-score result))

(defn determine-play [[elf :as play]]
	(let [result (get-outcome-score play)]
		(cond (zero? result) (get loss-plays elf)
					(win? result) (get win-plays elf)
					:else elf
			)))
(defn determine-plays [plays]
	(map determine-play plays))

(defn calc-score-by-result [plays]
	(let [elf-plays (map first plays)]
		(->> plays
				 determine-plays
				 (map #(vector %1 %2) elf-plays)
				 rps-tournament)))
