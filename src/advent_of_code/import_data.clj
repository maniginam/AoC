(ns advent-of-code.import-data
  (:require [c3kit.apron.time :as time]
            [clj-http.client :as http]))

(def cookie "session=53616c7465645f5f7a0f38c219d4f5d44bee55600922b56c4b1ef8bfee1d74f27566a3a6385e9f16ca43c5471aab5d91f1f6d56fee1ce9f70480aeede1ec153c")

(defn build-url [year day] (str "https://adventofcode.com/" year "/day/" day "/input"))

(defn import-day
  ([day] (import-day (time/year (time/now)) day))
  ([year day]
   (println (http/get (build-url year day)
              {:authority                 "adventofcode.com"
               :method                    "GET"
               :path                      "/2022/auth/login"
               :scheme                    "https"
               :Accept                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8"
               :Accept-Encoding           "gzip, deflate, br"
               :Accept-Language           "en-US,en;q=0.7"
               :Cache-Control             "max-age=0"
               :Cookie                    "session=53616c7465645f5f7366f7d1d45e8de88e93487c7d2c8180a7fbc42e8ce4a9688b23c8f17f4bbac2129cd7cc5056f6ba7153741a436ff8875c01db03c35b0d96"
               :Referer                   "https://adventofcode.com/2022/day/2"
               :Sec-Ch-Ua                 "\"Brave\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\""
               :Sec-Ch-Ua-Mobile          "?0"
               :Sec-Ch-Ua-Platform        "macOS"
               :Sec-Fetch-Dest            "document"
               :Sec-Fetch-Mode            "navigate"
               :Sec-Fetch-Site            "same-origin"
               :Sec-Fetch-User            "?1"
               :Sec-Gpc                   1
               :Upgrade-Insecure-Requests 1
               :User-Agent                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"

               }))))

(defn -main [& days]
  (doseq [day days]
    (import-day 2022 day)))