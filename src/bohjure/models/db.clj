(ns bohjure.models.db
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [bohjure.models.schema :as schema]
            [bohjure.util :only (current-timestamp format-time)]))

(defdb db schema/db-spec)

(defentity todos)

(defentity users)

(defn create-todo [todo]
  (insert todos
          (values todo)))

(defn update-user [id first-name last-name email]
  (update users
  (set-fields {:first_name first-name
               :last_name last-name
               :email email})
  (where {:id id})))

(defn get-user [id]
  (first (select users
                 (where {:id id})
                 (limit 1))))
