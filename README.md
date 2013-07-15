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
Sorry, names such as clojure or *jure are not allowed.
If you intend to use this name ironically, please set the
LEIN_IRONIC_JURE environment variable and try again.
```


## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2013 Jason Lewis

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
