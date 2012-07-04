# timing

Timing is a simple library to log call time using perf4j.

## Usage

```
[timing "0.1.2"]
```

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

There's also a built-in ring middleware to log call time for every
request.

```clojure
(wrap-timed handler)
```

## License

Copyright © 2012 [Sun Ning](http://github.com/sunng87)

Distributed under the Eclipse Public License, the same as Clojure.
