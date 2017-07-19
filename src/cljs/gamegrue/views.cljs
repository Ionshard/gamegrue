(ns gamegrue.views
  (:require [re-frame.core :as re-frame]
            [soda-ash.core :as sa]
            [gamegrue.subs :as subs]
            [gamegrue.youtube.views :as youtube.views]))

;; home

(defn home-panel []
  [sa/Container
   [youtube.views/test-component]])


;; about

(defn about-panel []
  [sa/Container "This is the About Page."])


;; header

(defn header []
  [sa/Menu {:fluid true
            :inverted true
            :color :blue}
   [sa/MenuItem {:header true} "Game Grue"]

   [sa/MenuMenu {:position :right}
    [sa/MenuItem {:href "#/"} "Home"]
    [sa/MenuItem {:href "#/about"} "About"]]])

;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  [sa/Container
   [header]
   [show-panel @(re-frame/subscribe [::subs/active-panel])]])
