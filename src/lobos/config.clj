(ns lobos.config
  (:use lobos.connectivity)
  (:require [bohjure.models.schema :as schema]))

(open-global schema/db-spec)