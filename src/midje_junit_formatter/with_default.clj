(ns ^ {:doc "JUnit formatter for Midje that keeps original stdout output."}
  midje-junit-formatter.with-default
  (:require [midje-junit-formatter.core :as junit]
            [midje.emission.plugins.default :as default]
            [midje.emission.state :as state]))

(defn merge-functions [oldf newf]
  (fn [& args]
    (apply oldf args)
    (apply newf args)))

(def emission-map (merge-with merge-functions
                              default/emission-map
                              junit/emission-map))

(state/install-emission-map emission-map)
