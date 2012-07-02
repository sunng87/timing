(ns timing.core
  (:import [org.perf4j StopWatch LoggingStopWatch]))

(defmacro timed
  "Time some forms"
  [tag & forms]
  `(let [watch# (LoggingStopWatch. (str ~tag))
         result# (do ~@forms)]
     (.stop watch#)
     result#))

(defn wrap-timed
  "An ring middleware to summarize calling time of each request"
  [handler]
  (fn [req]
    (timed (:uri req) (handler req))))


