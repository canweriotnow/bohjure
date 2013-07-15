# bohjure

First, you'll need to install [Leiningen](https://github.com/technomancy/leiningen), the project management and build tool for Clojure.

1. Make sure you have a Java JDK version 6 or later.
2. [Download the script.](https://raw.github.com/technomancy/leiningen/stable/bin/lein)
3. Place it on your `$PATH`. (`~/bin` is a good choice if it is on your path)
4. Set it to be executable. (`chmod 755 ~/bin/lein`)

Lein handles its own dependencies, so the first run will take a little longer, but then you'll be all set.

Interestingly, creating this project requires an extra step: Leiningen doesn't n
ormally allow names containing \*jure, so an extra flag has to be set:

```
Sorry, names such as clojure or \*jure are not allowed.
If you intend to use this name ironically, please set the
LEIN\_IRONIC\_JURE environment variable and try again.
```


## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2013 FIXME
