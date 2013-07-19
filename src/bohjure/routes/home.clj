(ns bohjure.routes.home
  (:use compojure.core)
  (:require [bohjure.views.layout :as layout]
            [bohjure.util :as util]
            [hiccup.core :as hc]
            [clojure.walk :refer [walk]]))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

(def hic-up
  [:div {:class "hero-unit"}
   [:h1 "This here is some hiccup!"]
   [:p "Wanna see some filler?"]
   [:p (util/md->html "/md/stuff.md")]
   [:div {:class "well"} (str "But funny things start to happen... ")]
    [:a {:href "/flipup" :class "btn btn-primary"} "Flip it!"]])

(defn ftest [f content]
  (if-let [flip f] (vec
                    (cons :div
                          (reverse
                           (filter vector?
                                   (drop-last content))))) content))

(defn hiccup-page [ & f]
  (let [body (ftest f hic-up)]
    (layout/render
      "home.html" {:content (hc/html body)})))



(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/hiccup" [] (hiccup-page))
  (GET "/flipup" [f] (hiccup-page f)))

(ftest 1 hic-up)




