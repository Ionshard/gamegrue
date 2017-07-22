(ns user
  (:require [gamegrue.core :as gamegrue]
            [figwheel-sidecar.repl-api :as figwheel]))

(defn start []
  (figwheel/start-figwheel!)
  (gamegrue/start-server))

(defn cljs-repl []
  (figwheel/cljs-repl))
