(ns bohjure.routes.cljsexample
  (:require [compojure.core :refer :all]
            [noir.response :as response]
            [bohjure.views.layout :as layout]
            [bohjure.models.db :as db]
            [bohjure.models.schema :as schema]))

(def validate-messages
  (partial every? #(and (:user %) (:message %))))

;(def messages
;  (atom
;    [{:message "Hello world"
;      :user    "Foo"}
;     {:message "Ajax is fun"
;      :user    "Bar"}]))

(def messages
  (ref
    (db/get-messages)))

(defn add-message
  [message user]
  (dosync
            (let [messages (commute messages conj {:message message :user user})
                  db-agent (agent nil)]
              (send-off db-agent (fn [_]
                                   (do
                                     (db/add-message-list @messages))
                                   nil))
              messages)))



(defroutes cljs-routes
  (GET "/cljsexample" [] (layout/render "cljsexample.html"))
  (GET "/messages" [] (response/edn @messages))
  (POST "/add-message" [message user]
        (response/edn
          (add-message message user))))




