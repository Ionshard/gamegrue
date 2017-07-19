(ns gamegrue.youtube.events
  (:require [ajax.core :as ajax]
            [re-frame.core :as re-frame]
            [gamegrue.config :as config]
            [gamegrue.youtube.db :as db]))

(def google-api "https://www.googleapis.com/youtube/v3/")

(defn url
  [endpoint]
  (str google-api endpoint))

(defn initialize-db
  [{:keys [db]} _]
  {:db (assoc db db/key db/default-db)
   :dispatch-n [[::fetch-playlist-items config/PLAYLIST]]})

(re-frame/reg-event-fx ::initialize-db initialize-db)

(defn fetch-playlist-items
  [{:keys [db]} [_ playlist-id]]
  (if (>= (get-in db [db/key :playlist-items playlist-id :total-results] 0)
          (count (get-in db [db/key :playlist-items playlist-id :items])))
    {:http-xhrio {:method :get
                  :uri (url "playlistItems")
                  :params {:key config/APIKEY
                           :part "snippet"
                           :playlistId playlist-id
                           :pageToken (get-in db [db/key :playlist-items playlist-id :next-page-token])
                           :maxResults 30}
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [::update-playlist-items playlist-id]}}))

(re-frame/reg-event-fx ::fetch-playlist-items fetch-playlist-items)

(defn add-playlist-items
  [items playlist-items]
  (into (or items []) (->> playlist-items :items (map :snippet))))

(defn update-playlist-items
  [{:keys [db]} [_ playlist-id playlist-items]]
  {:db (-> db
           (assoc-in [db/key :playlist-items playlist-id :total-results] (get-in playlist-items [:pageInfo :totalResults]))
           (assoc-in [db/key :playlist-items playlist-id :next-page-token] (:nextPageToken playlist-items))
           (update-in [db/key :playlist-items playlist-id :items] add-playlist-items playlist-items))})

(re-frame/reg-event-fx ::update-playlist-items update-playlist-items)
