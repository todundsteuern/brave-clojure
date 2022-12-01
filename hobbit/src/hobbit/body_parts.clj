(ns hobbit.body-parts
  (:require [clojure.string :as str]))

(def base-weird-space-alien-body-parts
  [{:name "head" :size 3}
   {:name "eye-1" :size 1}
   {:name "ear-1" :size 1}
   {:name "mouth-1" :size 1}
   {:name "nose-1" :size 1}
   {:name "neck" :size 2}
   {:name "shoulder-1" :size 3}
   {:name "upper-arm-1" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "forearm-1" :size 3}
   {:name "abdomen" :size 6}
   {:name "kidney-1" :size 1}
   {:name "hand-1" :size 2}
   {:name "knee-1" :size 2}
   {:name "thigh-1" :size 4}
   {:name "lower-leg-1" :size 3}
   {:name "achilles-1" :size 1}
   {:name "foot-1" :size 2}])

(defn matching-parts
  [part]
  (map (fn [part-num]
         {:name (str/replace (:name part) #"-1$" (str "-" part-num))
          :size (:size part)})
       [2 3 4 5]))

(defn symmetrize-space-alien-body-parts
  [base-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set (conj (matching-parts part) part))))
          []
          base-body-parts))

(defn symmetrize-body-parts-general
  [body-parts num-matching-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts
                  (-> (map (fn [part-num]
                             {:name (str/replace (:name part)
                                                 #"-1$"
                                                 (str "-" part-num))
                              :size (:size part)})
                           (range 1 (inc num-matching-parts)))
                      set)))
          []
          body-parts))

(comment
  (matching-parts {:name "eye-1" :size 1})
  (matching-parts {:name "abdomen" :size 6})

  (symmetrize-space-alien-body-parts base-weird-space-alien-body-parts)

  (symmetrize-body-parts-general base-weird-space-alien-body-parts 3))
