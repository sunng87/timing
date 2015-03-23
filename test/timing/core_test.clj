(ns timing.core-test
  (:use clojure.test
        timing.core))

(defn demofunction [t]
  (Thread/sleep t)
  t)

(deftest simple-test
  (let [t 1000]
    (is (= t (timed :demo (demofunction t))))))

(deftest timed-fn-test
  (let [wrapped-timefn (timed-fn demofunction)
        t 1000]
    (is (= t (wrapped-timefn t)))))

(defn-timed demofunction2 [t]
  (Thread/sleep t)
  t)

(deftest defn-timed-test
  (let [t 1000]
    (is (= t (demofunction2 t)))))
