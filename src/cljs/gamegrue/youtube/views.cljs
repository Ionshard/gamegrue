(ns gamegrue.youtube.views
  (:require [clojure.string :as st]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [soda-ash.core :as sa]
            [gamegrue.config :as config]
            [gamegrue.youtube.events :as events]
            [gamegrue.youtube.subs :as subs]))

(defn fetch-btn []
  [sa/Button {:primary true
              :icon :download
              :onClick #(re-frame/dispatch [::events/fetch-playlist-items config/PLAYLIST])}
   "View More"])

(defn thumbnail-src
  ([video] (thumbnail-src :medium video))
  ([quality video]
   (get-in video [:thumbnails quality :url])))

(defn video-modal
  [{:keys [title description] :as video} on-close]
  [sa/Modal {:closeIcon true
             :open true
             :onClose on-close}
   [sa/ModalHeader title]
   [sa/ModalContent
    [sa/Embed {:source :youtube
               :placeholder (thumbnail-src :high video)
               :id (get-in video [:resourceId :videoId])}]
    [sa/ModalDescription description]]])

(defn video-card-internal
  [{:keys [title] :as video}]
  (let [open? (reagent/atom false)]
    (fn [{:keys [title] :as video}]
      [sa/Card {:raised true
                :onClick #(swap! open? not)
                :color "red"
                :centered true}
       [sa/Image {:src (thumbnail-src video)}]
       [sa/CardHeader [sa/Header title]]
       (when @open?
         [video-modal video #(swap! open? not)])])))

(defn video-card
  [video]
  [video-card-internal video])

(defn video-deck
  [playlist-id]
  (into [sa/CardGroup {}]
        (map video-card @(re-frame/subscribe [::subs/playlist-items playlist-id]))))


(defn test-component []
  [sa/Container
   [sa/Header "Latest Videos"]
   [video-deck config/PLAYLIST]
   [sa/Segment {:inverted true
                :textAlign :center}
    [sa/ButtonGroup {:attached :right} [fetch-btn]]
    "Copyright Corey Ling 2017"]])
