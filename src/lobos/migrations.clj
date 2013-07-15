(ns lobos.migrations
  (:refer-clojure
     :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema config)))

(defmigration add-todos-table
  (up [] (create
          (table :todos
                 (int :id :primary-key)
                 (varchar :title 30)
                 (varchar :details 255)
                 (boolean :complete)
                 (time :created_at)
                 (time :compelted_at))))
  (down [] (drop (table :todos))))