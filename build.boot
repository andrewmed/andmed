(require 'clojure.xml)

(defn build-edn [x]  (let [[groupdId] (:groupId x) [artifactId] (:artifactId x) [version] (:version x)]
                       (vector (symbol (str groupdId "/" artifactId)) (str version))))

(defn convert [result x] (let [val (:content x) key (:tag x)] (assoc result key val)))

(defn build-deps [col] (map (partial reduce convert {}) col))

(defn get-deps [x]   (when-let [tag (:tag x)]  (if (= tag :dependencies)   (:content x))))

(defn maven-import "Makes a vector of maps of deps from a local pom.xml file" []
  (when-let [rdr (java.io.File. "pom.xml")]
    (vec (map build-edn (build-deps (map :content (mapcat get-deps (:content (clojure.xml/parse rdr)))))))))

(set-env! :dependencies (maven-import))
