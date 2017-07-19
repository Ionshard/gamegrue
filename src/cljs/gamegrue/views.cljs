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

(defn nav-bar []
  [sa/Menu {:floated :right
            :fluid false}
   [sa/MenuItem {:href "#/"} "Home"]
   [sa/MenuItem {:href "#/about"} "About"]])

(defn header []
  [sa/GridRow {:color :blue
               :columns 2}
   [sa/GridColumn [sa/Header {:size :huge} "Game Grue"]]
   [sa/GridColumn [nav-bar]]])

;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  [sa/Grid
   [header]
   [sa/GridRow
    [show-panel @(re-frame/subscribe [::subs/active-panel])]]])
