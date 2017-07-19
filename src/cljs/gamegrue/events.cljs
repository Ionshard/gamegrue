(ns gamegrue.events
  (:require [re-frame.core :as re-frame]
            [gamegrue.db :as db]
            [gamegrue.youtube.events :as youtube.events]))

(defn initialize-db
  [_ _]
  {:db {db/key db/default-db}
   :dispatch-n [[::youtube.events/initialize-db]]})

(re-frame/reg-event-fx ::initialize-db initialize-db)


(defn set-active-panel
  [db [_ active-panel]]
  (assoc-in db [db/key :active-panel] active-panel))

(re-frame/reg-event-db ::set-active-panel set-active-panel)

