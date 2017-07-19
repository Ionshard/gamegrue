(ns gamegrue.youtube.subs
  (:require [re-frame.core :as re-frame]
            [gamegrue.youtube.db :as db]))

(defn playlist-items
  [db [_ playlist-id]]
  (get-in db [db/key :playlist-items playlist-id :items]))

(re-frame/reg-sub ::playlist-items playlist-items)

