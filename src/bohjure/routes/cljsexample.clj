(ns bohjure.routes.cljsexample
  (:require [compojure.core :refer :all]
            [noir.response :as response]
            [bohjure.views.layout :as layout]
            [bohjure.models.db :as db]))

;(def validate-messages
;  (partial every? #(and (:foo %) (:baz %))))

;(def messages
;  (atom
;    [{:message "Hello world"
;      :user    "Foo"}
;     {:message "Ajax is fun"
;      :user    "Bar"}]))

(def messages
  (ref
   (bohjure.models.db/get-messages)))

(defroutes cljs-routes
  (GET "/cljsexample" [] (layout/render "cljsexample.html"))
  (GET "/messages" [] (response/edn @messages))
  (POST "/add-message" [message user]
        (response/edn
          (dosync (commute messages conj {:message message :user user})))))

(dosync (commute messages conj {:message "Wowza!" :user "Soop"}))

(db/add-message-list @messages)

