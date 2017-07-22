(ns gamegrue.core
  (:require [org.httpkit.server :as server]
            [ring.util.response :as response]))

(defn app [{:keys [uri] :as req}]
  #_{:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello HTTP!<br>" req)}
  (response/resource-response uri {:root "public"}))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn start-server []
  (when (nil? @server)
    (reset! server (server/run-server #'app {:port 8787}))))
