(ns bohjure.routes.todos
  (:require [compojure.core :refer :all]
            [noir.response :as response]
            [bohjure.views.layout :as layout]))

(def messages
  (atom
    [{:message "Hello world"
      :user    "Foo"}
     {:message "Ajax is fun"
      :user    "Bar"}]))

(defroutes todos-routes
  (GET "/todolist" [] (layout/render "todolist.html"))
  (GET "/todos" [] (response/edn @messages))
  (POST "/add-todo" [message user]
        (response/edn
          (swap! messages conj {:message message :user user}))))