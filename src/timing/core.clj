(ns timing.core
  (:import [org.perf4j StopWatch LoggingStopWatch]))

(defmacro defn-timed
  "define a function which is timed by soul"
  [name argvec & body]
  `(defn ~name argvec
     (let [watch# (LoggingStopWatch. ~name)
           result# (do ~@body)]
       (.stop watch#)
       result#)))

(defn timed
  "Wraps a function to be a timed function"
  [tag func]
  (fn [& args]
    (let [watch (LoggingStopWatch. tag)
          result (apply func args)]
      (.stop watch)
      result)))

(defn wrap-timed
  "An ring middleware to summarize calling time of each request"
  [handler]
  (fn [req]
    (let [watch (LoggingStopWatch. (:uri req))]
      (handler req)
      (.stop watch))))


