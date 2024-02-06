(ns problems.problem-1
  (:require [clojure.edn :as edn]))

;; Check if an item has a 19% IVA tax
(defn has-iva-19? [item]
  (some #(and (= (:tax/category %) :iva) (= (:tax/rate %) 19))
        (:taxable/taxes item)))

;; Check if an item has a 1% retention for "fuente" category
(defn has-ret-fuente-1? [item]
  (some #(and (= (:retention/category %) :ret_fuente)
              (= (:retention/rate %) 1))
        (:retentionable/retentions item)))

;; Streamlined filter that returns the items that have one of the taxes but not the other
(defn filter-invoice-items [invoice]
  (->> (:invoice/items invoice)
       (map #(vector % (has-iva-19? %)))
       (map #(vector (first %) (second %) (has-ret-fuente-1? (first %))))
       (filter #(let [iva-19? (second %)
                      ret-fuente-1? (nth % 2)]
                  (or (and iva-19? (not ret-fuente-1?))
                      (and ret-fuente-1? (not iva-19?)))))
       (map first))) ;; Extracting just the item from each vector


;; Load the invoice data
(def invoice (edn/read-string (slurp "invoice.edn")))

;; Apply the filter
(filter-invoice-items invoice)

;; Notes
;; The readme file item conditions are not defiend correctly in the problem statement.
;; It requires conditions as if the return of the solution is a list of invoices. ('at least one item...')
