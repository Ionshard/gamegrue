(ns gamegrue.subs
  (:require [re-frame.core :as re-frame]
            [gamegrue.db :as db]))

(defn active-panel
  [db _]
  (get-in db [db/key :active-panel]))

(re-frame/reg-sub ::active-panel active-panel)
