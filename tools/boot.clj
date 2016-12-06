; using clojure.data.zip.xml would give better way to parse xml but I do not want
; to introduce extra dependency on this stage

(ns andmed.tools.boot
  {:boot/export-tasks true}
  (require [clojure.xml :as xml :refer [parse]]))

(defn build-edn [x]
  (let [[groupdId] (:groupId x)
        [artifactId] (:artifactId x)
        [version] (:version x)]
    (vector (symbol (str groupdId "/" artifactId)) (str version))))

(defn convert [result x]
  (let [val (:content x) key (:tag x)]
    (assoc result key val)))

(defn build-deps [col]
  (map (partial reduce convert {}) col))

(defn find-deps [x]
  (when-let [tag (:tag x)]
    (cond
      (= tag :dependencies)
      (:content x))))

(defn get-deps [file]
  (map :content
       (mapcat find-deps (:content (parse file)))))

(defn maven-import "Makes a vector of maps of deps from a local pom.xml file" []
  (when-let [file (java.io.File. "pom.xml")]
    (vec (map build-edn (build-deps (get-deps file))))))

;(set-env! :dependencies (maven-import))
