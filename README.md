# timing

Timing is a simple library to log call time using perf4j.

## Usage

```
[timing "0.1.2"]
```

### timed

Just wrap forms you want to calculate call time:

```clojure
(timed :tag
  (look-up-db ...)
  (assemble-data ...))
```

Note that you have to provide a `tag` to identify these forms in
timing log.

Timing will select a logging provider automatically by looking up your
classpath (slf4j, log4j and stderr). Timing doesn't depend on any
logging provider at compile time.

### timed-fn

Convert a predefined function to a timed one.

```clojure
(defn count-how-many-people-on-the-earth [req]
  )

(defroute my-website
  (GET "/count" (timed-fn count-how-many-people-on-the-earth)))
```

### defn-timed

Define a function who is born to be timed.

```clojure
(defn-timed count-how-many-ants-on-the-earth [req]
  )
```

### wrap-timed

There's also a built-in ring middleware to log call time for every
request. `wrap-timed` uses uri as timing tags.

```clojure
(wrap-timed handler)
```

## License

Copyright © 2012 [Sun Ning](http://github.com/sunng87)

Distributed under the Eclipse Public License, the same as Clojure.
