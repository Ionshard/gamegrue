(ns gamegrue.config
  (:require-macros [adzerk.env :as env]))

(def debug?
  ^boolean goog.DEBUG)

(env/def
  APIKEY :required
  PLAYLIST "UUJPjTumUwnA8vsLcTKzsQfg"
 )
