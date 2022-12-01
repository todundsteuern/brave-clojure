(ns fwpd.core
  (:require [clojure.string :as str]))

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(str/split % #",")
       (str/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter)
          records))

(comment
  (->> filename
       slurp
       parse
       first
       (map vector vamp-keys)
       (reduce (fn [result [vamp-key value]]
                 (assoc result vamp-key (convert vamp-key value)))
               {}))

  (-> filename
      slurp
      parse
      mapify)

  (->> filename
       slurp
       parse
       mapify
       (glitter-filter 3)))
