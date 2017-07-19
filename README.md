# GameGrue Website

A [re-frame](https://github.com/Day8/re-frame) application designed to display information about the Game Grue Youtube channel as a website.

## Development Mode

### Start Cider from Emacs:

Put this in your Emacs config file:

```
(setq cider-cljs-lein-repl "(do (use 'figwheel-sidecar.repl-api) (start-figwheel!) (cljs-repl))")
```

Navigate to a clojurescript file and start a figwheel REPL with `cider-jack-in-clojurescript` or (`C-c M-J`)

### Run application:

```
lein clean
APIKEY="YOUR API KEY HERE" lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3450](http://localhost:3450).

## Production Build


To compile clojurescript to javascript:

```
lein clean
APIKEY="YOUR API KEY HERE" lein cljsbuild once min
```
