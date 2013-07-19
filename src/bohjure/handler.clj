(ns bohjure.handler
  (:require [compojure.core :refer [defroutes]]
            [bohjure.routes.home :refer [home-routes]]
            [noir.util.middleware :as middleware]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [com.postspectacular.rotor :as rotor]
            [bohjure.routes.cljsexample :refer [cljs-routes messages]]
            [bohjure.models.schema :as schema]
            [bohjure.models.db :as db]))

(defroutes
  app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(defn init
  "init will be called once when
   app is deployed as a servlet on
   an app server such as Tomcat
   put any initialization code here"
  []
  (if-not schema/initialized?
    (schema/create-tables))
  (timbre/set-config!
    [:appenders :rotor]
    {:min-level :info,
     :enabled? true,
     :async? false,
     :max-message-per-msecs nil,
     :fn rotor/append})
  (timbre/set-config!
    [:shared-appender-config :rotor]
    {:path "bohjure.log", :max-size (* 512 1024), :backlog 10})
  (timbre/info "bohjure started successfully")

  (future (loop []
            (Thread/sleep 10000)
            (db/add-message-list @messages)
            (recur))))

(defn destroy
  "destroy will be called when your application
   shuts down, put any clean up code here"
  []
  (timbre/info "bohjure is shutting down..."))

(def app
 (middleware/app-handler
   [cljs-routes home-routes app-routes]
   :middleware
   []
   :access-rules
   []))

(def war-handler (middleware/war-handler app))


