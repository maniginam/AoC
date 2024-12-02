(defproject advent-of-code "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main advent-of-code.core
  :dependencies [[com.cleancoders.c3kit/apron "2.0.5"]
                 [clj-http "2.3.0"]
                 [org.clojure/clojure "1.8.0"]
                 ]
  :profiles {:dev {:dependencies [[speclj "3.4.1"]]}}
  :plugins [[speclj "3.4.1"]]
  :test-paths ["spec"]
  :aliases {"new-day" ["run" "-m" "advent-of-code.import-data"]}
  )
