# template

A [re-frame](https://github.com/Day8/re-frame) application with material ui and possibility to create cordova applications

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```

## Cordova builds

```
cd resources
cordova platform add android | ios | browser
e.g. cordova build android
```
