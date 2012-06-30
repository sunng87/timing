(ns timing.core
  (:import [org.perf4j StopWatch LoggingStopWatch]))

(defmacro defn-watched [name argvec & body]
  `(defn ~name argvec
     (let [watch# (LoggingStopWatch. ~name)
           result# (do ~@body)]
       (.stop watch#)
       result#)))

(defn watched [func]
  )

(defn wrap-watched [d]
  )

