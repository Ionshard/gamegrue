(ns gamegrue.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [re-frisk.core :as re-frisk]
            [gamegrue.events :as events]
            [gamegrue.routes :as routes]
            [gamegrue.views :as views]
            [gamegrue.config :as config]
            [day8.re-frame.http-fx]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (re-frisk/enable-re-frisk!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
