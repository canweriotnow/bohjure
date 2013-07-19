(ns bohjure.models.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]))

(def db-store "site.db")

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname (str (io/resource-path) db-store)
              :user "sa"
              :password ""
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (new java.io.File (str (io/resource-path) db-store ".h2.db"))))

(defn create-users-table
  []
  (sql/with-connection db-spec
    (sql/create-table
      :users
      [:id "bigint auto_increment PRIMARY KEY"]
      [:first_name "varchar(30)"]
      [:last_name "varchar(30)"]
      [:email "varchar(30)"]
      [:admin :boolean]
      [:last_login :time]
      [:is_active :boolean]
      [:pass "varchar(100)"])))

(defn create-todos-table
  []
  (sql/with-connection db-spec
     (sql/create-table
       :todos
       [:id "bigint auto_increment PRIMARY KEY"]
       [:title "varchar(30)"]
       [:details "varchar(100)"]
       [:created_at "timestamp default current_timestamp() not null"]
       [:updated_at "timestamp default current_timestamp not null"]
       [:completed_at "timestamp"])))

(defn create-messages-table
  []
  (sql/with-connection db-spec
     (sql/create-table
      :messages
      [:id "bigint auto_increment primary key"]
      [:message "varchar(120)"]
      [:user "varchar(30)"])))

(defn drop-messages-table
  []
  (sql/with-connection db-spec
                       (sql/drop-table :messages)))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-messages-table))

(defn drop-tables
  "Drop ALL the tables!"
  []
  (drop-messages-table))













