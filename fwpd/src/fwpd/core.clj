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
  (->> records
       (filter #(>= (:glitter-index %) minimum-glitter))
       (map :name)))

(defn validate
  [kw-to-validation-fns record]
  (let [keys (keys kw-to-validation-fns)]
    (->> (reduce (fn [result key]
                   (conj result (contains? record key)))
                 []
                 keys)
         (every? true?))))

(defn append
  [records record]
  (if (validate conversions record)
    (conj records record)
    records))

(defn stringify
  [list-of-maps]
  (let [headers (->> (first list-of-maps)
                     keys
                     (map name)
                     (str/join ",")) 
        records (->> (map vals list-of-maps)
                     (map #(str/join "," %))
                     )]
    (->> (into [headers] records)
         (str/join "\n"))))

(comment
  (->> filename
       slurp
       parse
       mapify
       (glitter-filter 3))

  (-> filename
      slurp
      parse
      mapify
      (append {:name "McFishwich" :glitter-index 0}))
  
  (-> filename
      slurp
      parse
      mapify
      (append {:name "McFishwich" :no-glitter true}))
  
  (-> filename
      slurp
      parse
      mapify
      stringify)
  )
