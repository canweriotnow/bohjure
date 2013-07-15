(ns bohjure.models.db
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [bohjure.models.schema :as schema]
            [bohjure.util :only (current-timestamp format-time)]))

(defdb db schema/db-spec)

(defentity users)

(defn create-user [user]
  (insert users
          (values user)))

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

(defentity todos
  (prepare (fn [todo]
             (assoc todo :updated_at (bohjure.util/current-timestamp)))))

(defn create-todo [todo]
  (insert todos
          (values (assoc todo :created_at (bohjure.util/current-timestamp)))))

(defn complete-todo [id]
  (update todos
          (set-fields {:completed_at (bohjure.util/current-timestamp)})
          (where {:id id})))




