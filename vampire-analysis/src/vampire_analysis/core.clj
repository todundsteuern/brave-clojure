(ns vampire-analysis.core
  (:gen-class))

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(comment
  (map unify-diet-data human-consumption critter-consumption)
  
  (take-while #(< (:month %) 3) food-journal)
  (drop-while #(< (:month %) 3) food-journal)
  (->> food-journal
       (drop-while #(< (:month %) 2))
       (take-while #(< (:month %) 4)))
  
  (filter #(< (:human %) 5) food-journal)
  (filter #(< (:month %) 3) food-journal)
  
  (some #(> (:critter %) 5) food-journal)
  (some #(> (:critter %) 3) food-journal)
  (some #(and (> (:critter %) 3) %) food-journal)
  
  (sort [3 1 2])
  (sort-by count ["aaa" "c" "bb"])
  (concat [1 2] [3 4])
  )
