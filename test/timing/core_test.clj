(ns timing.core-test
  (:use clojure.test
        timing.core))

(defn demofunction [t]
  (Thread/sleep t)
  t)

(deftest simple-test
  (let [t 1000]
    (is (= t (timed :demo (demofunction t))))))


