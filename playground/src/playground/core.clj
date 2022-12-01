(ns playground.core
  (:require [clojure.repl :refer [doc]]
            [clojure.string :as str]
            [clojure.test :as t])
  (:gen-class))

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
       "MAN SLASH WOMAN SLASH APACHE HELICOPTER EVER "
       "I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

(defn no-params
  []
  "I take no parameters!")

(defn one-param
  [x]
  (str "I take one parameter: " x))

(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
       "together to spite you! " x y))

(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))

(defn weird-arity
  ([]
   "Destiny dressed you this morning, my friend, and now Fear is
    trying to pull off your pants. If you give up, if you give in,
    you're gonna end up naked with Fear just standing there laughing
    at your dangling unmentionables! - The Tick")
  ([number]
   (inc number)))

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: " 
       (str/join ", " things)))

(defn my-first
  [[first-thing]]
  first-thing)

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (str/join ", " unimportant-choices))))

;; (defn announce-treasure-location
;;   [{lat :lat lng :lng}]
;;   (println (str "Treasure lat: " lat))
;;   (println (str "Treasure lng: " lng)))

(defn announce-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  (println (str "Treasure location: " treasure-location)))

(comment
  (doc too-enthusiastic)

  (too-enthusiastic "Zelda")

  (no-params)
  (one-param "Bob")
  (two-params "Sponge" "Bob")

  (x-chop "Kanye West" "slap")
  (x-chop "Kanye East")

  (weird-arity)
  (weird-arity 41)

  (codger "Billy" "Anne-Marie" "The Incredible Bulk")
  
  (favorite-things "Doreen" "gum" "shoes" "kara-te")
  
  (my-first ["oven" "bike" "war-axe"])
  
  (chooser ["Marmalade" "Handsome Jack" "Pigpen" "Aquaman"])

  (announce-treasure-location {:lat 28.22 :lng 81.33})
  )
