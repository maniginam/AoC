(ns advent-of-code.2022.day-1-spec
	(:require [advent-of-code.2022.day-1 :as sut]
						[advent-of-code.2022.inputs.day-1 :as input]
						[clojure.string :as str]
						[speclj.core :refer :all]))

(defn partition-loads [loads]
	(->> (str/split-lines loads)
				(partition-by str/blank?)
			 (remove (partial = [""]))
			 (map (fn [load] (map read-string load)))))

(def test-elves [[1000 2000 3000] [4000] [5000 6000] [7000 8000 9000] [10000]])
(def calorie-loads (partition-loads input/calorie-loads))

(describe "Day 1 - Calories"

	(it "partitions loads"
		(should= [[1 2] [3 4] [5 6]]
						 (partition-loads "1\n2\n\n3\n4\n\n5\n6")))

	(for [[expected calories] [
														 [0 [0]]
														 [1 [1]]
														 [2 [2]]
														 [10 [1 10]]]]
		(it (str "max calories of " calories)
			(should= expected (sut/max-calories calories))))

	(for [[total elf-loads] [
													 [[0] [[0]]]
													 [[0] [[0]]]
													 [[1] [[1]]]
													 [[0 1] [[0] [1]]]
													 [[1 2] [[1] [2]]]
													 [[2 4] [[1 1] [2 2]]]
													 [[6000 4000 11000 24000 10000] test-elves]]]
		(it (str "adds all loads: " total)
			(should= total (sut/add-all elf-loads))))

	(it "gets the biggest"
		(should= 0 (sut/find-biggest-load [[0]]))
		(should= 10 (sut/find-biggest-load [[5] [10]]))
		(should= 40 (sut/find-biggest-load [[5 5] [10 10] [20 20]]))
		(should= 24000 (sut/find-biggest-load test-elves))
		(should= 67622 (sut/find-biggest-load calorie-loads))
		)

	(it "finds total of top 3 elf-loads"
		(should= 45000 (sut/sum-top-three-loads test-elves))
		(should= 201491 (sut/sum-top-three-loads calorie-loads))
		)

	)
