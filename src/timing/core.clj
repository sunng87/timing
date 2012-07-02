(ns timing.core
  (:import [org.perf4j StopWatch LoggingStopWatch]))

(defprotocol LoggingStopWatchFactory
  (get-stop-watch [this tag]))

(defn try-slf4j []
  (try
    (Class/forName "org.slf4j.Logger")
    (eval
     `(reify LoggingStopWatchFactory
        (get-stop-watch [this# tag#]
          (org.perf4j.slf4j.Slf4JStopWatch. tag#))))
    (catch ClassNotFoundException e nil)))

(defn try-log4j []
  (try
    (Class/forName "org.apache.log4j.Logger")
    (eval
     `(reify LoggingStopWatchFactory
        (get-stop-watch [this# tag#]
          (org.perf4j.log4j.Log4JStopWatch. tag#))))
    (catch ClassNotFoundException e nil)))

(defn failback []
  (reify LoggingStopWatchFactory
    (get-stop-watch [this tag]
      (LoggingStopWatch. tag))))

(def preferred-factory
  (or
   (try-slf4j)
   (try-log4j)
   (failback)))

(defmacro timed
  "Time some forms"
  [tag & forms]
  `(let [watch# (get-stop-watch preferred-factory (str ~tag))
         result# (do ~@forms)]
     (.stop watch#)
     result#))

(defn wrap-timed
  "An ring middleware to summarize calling time of each request"
  [handler]
  (fn [req]
    (timed (:uri req) (handler req))))


