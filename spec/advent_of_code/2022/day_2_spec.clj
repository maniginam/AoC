(ns advent-of-code.2022.day-2-spec
	(:require
	 [advent-of-code.2022.day-2 :as sut]
	 [advent-of-code.2022.inputs.day-2 :as inputs]
	 [clojure.string :as str]
	 [speclj.core :refer :all]
	 ))

(defn format-plays [plays]
	(->> (str/split-lines plays)
			(map #(str/split % #" "))
			 (map #(remove str/blank? %))
			 (map vec)))
(def test-str "A Y\n B X\n C Z")
(def test-plays [["A" "Y"] ["B" "X"] ["C" "Z"]])
(def input-plays (format-plays inputs/plays))

(describe "Rock Paper Scissors Tournament"

	(it "parses data"
		(should= test-plays (format-plays test-str)))

	(for [[[elf me :as play] result]
				[[["A" "X"] 3]
				 [["A" "Y"] 6]
				 [["A" "Z"] 0]
				 [["B" "X"] 0]
				 [["B" "Y"] 3]
				 [["B" "Z"] 6]
				 [["Z" "X"] 6]
				 [["Z" "Y"] 0]
				 [["Z" "Z"] 3]]]
		(it (str "determines outcome: " elf " vs " me)
			(should= result (sut/score-round play))))

	(for [[my-play outcome points]
				[["X" 0 1]
				 ["X" 3 4]
				 ["X" 6 7]
				 ["Y" 0 2]
				 ["Y" 3 5]
				 ["Y" 6 8]
				 ["Z" 0 3]
				 ["Z" 3 6]
				 ["Z" 6 9]]]
		(it (str "calcs score my-play: " my-play " outcome: " outcome)
			(should= points (sut/calc-score my-play outcome))))

	(it "determines play"
		(should= ["C"] (sut/determine-plays [["A" "X"]]))
		(should= ["A"] (sut/determine-plays [["A" "Y"]]))
		(should= ["B"] (sut/determine-plays [["A" "Z"]]))
		(should= ["A"] (sut/determine-plays [["B" "X"]]))
		(should= ["B"] (sut/determine-plays [["B" "Y"]]))
		(should= ["C"] (sut/determine-plays [["B" "Z"]]))
		(should= ["B"] (sut/determine-plays [["C" "X"]]))
		(should= ["C"] (sut/determine-plays [["C" "Y"]]))
		(should= ["A"] (sut/determine-plays [["C" "Z"]]))
		(should= ["A" "A" "A"] (sut/determine-plays test-plays)))

	(it "calcs final score"
			(should= 8 (sut/rps-tournament (take 1 test-plays)))
			(should= 9 (sut/rps-tournament (take 2 test-plays)))
			(should= 15 (sut/rps-tournament test-plays))
			(should= 10595 (sut/rps-tournament input-plays)))

	(for [[[elf-play result :as plays] points]
				[[[["A" "X"]] 3]
				 [[["B" "X"]] 1]
				 [[["C" "X"]] 2]
				 [[["A" "Y"]] 4]
				 [[["B" "Y"]] 5]
				 [[["C" "Y"]] 6]
				 [[["A" "Z"]] 8]
				 [[["B" "Z"]] 9]
				 [[["C" "Z"]] 7]
				 [test-plays 12]
				 [input-plays 9541]]]
		(it (str "calcs final score by round result: elf: " elf-play " result: " result)
			(should= points (sut/calc-score-by-result plays))))

	)