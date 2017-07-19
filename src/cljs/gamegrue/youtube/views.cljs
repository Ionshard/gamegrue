(ns gamegrue.youtube.views
  (:require [clojure.string :as st]
            [re-frame.core :as re-frame]
            [soda-ash.core :as sa]
            [gamegrue.config :as config]
            [gamegrue.youtube.events :as events]
            [gamegrue.youtube.subs :as subs]))

(defn fetch-btn []
  [sa/Button {:primary true
              :icon :download
              :onClick #(re-frame/dispatch [::events/fetch-playlist-items config/PLAYLIST])}
   "Fetch Items"])

(defn thumbnail-src
  ([video] (thumbnail-src :medium video))
  ([quality video]
   (get-in video [:thumbnails quality :url])))

(defn video-card
  [{:keys [title] :as video}]
  [sa/Card {:raised true
            :color "red"}
   [sa/Image {:src (thumbnail-src video)}]
   [sa/CardHeader title]])

(defn video-deck
  [playlist-id]
  (let [playlist-items (re-frame/subscribe [::subs/playlist-items playlist-id])]
    (fn []
      (let [playlist-items @playlist-items]
        [sa/Container
         [sa/Header (count playlist-items) " videos for " config/PLAYLIST]
         (into [sa/CardGroup {}]
               (map video-card playlist-items))]))))


(defn test-component []
  [sa/Container
   [sa/Header "Testing Youtube Components"]
   [video-deck config/PLAYLIST]
   [sa/ButtonGroup [fetch-btn]]])
