(ns bohjure.routes.cljsexample
  (:require [compojure.core :refer :all]
            [noir.response :as response]
            [bohjure.views.layout :as layout]
            [bohjure.models.db :as db]))

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



(defroutes cljs-routes
  (GET "/cljsexample" [] (layout/render "cljsexample.html"))
  (GET "/messages" [] (response/edn @messages))
  (POST "/add-message" [message user]
        (response/edn
          (dosync
            (let [messages (commute messages conj {:message message :user user})
                  db-agent (agent nil)]
              (send-off db-agent (fn []
                                   (do
                                     (db/add-message-list @messages)
                                     (println "Agent is done!"))
                                   nil))
              messages)))))

(dosync
            (let [messages (commute messages conj {:message "smack" :user "fuzzy"})
                  db-agent (agent 0)]
              (send-off db-agent (fn []
                                    (do
                                   (db/add-message-list @messages)
                                    (println "Agent is done!"))
                                   )
              messages)))


;(restart-agent new-agent nil)
;(dosync (commute messages conj {:message "Wowza!" :user "Soop"}))

;(db/add-message-list @messages)

(db/get-messages)

