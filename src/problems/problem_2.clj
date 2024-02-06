(ns problems.problem-2
  (:require [invoice-spec :as invoice-spec]
            [utils.utils :as utils]
            [clojure.string :as str]))


;; Function to transform the JSON map to match the spec structure
(defn transform-json-to-spec [json-map]
  (let [invoice-data (get json-map :invoice)
        date-formatter (java.time.format.DateTimeFormatter/ofPattern "dd/MM/yyyy") ;; Date format in the JSON
        local-date (java.time.LocalDate/parse (:issue_date invoice-data) date-formatter) ;; Get local date from the JSON
        issue-instant (utils/local-date-to-instant local-date)] ;; Convert local date to instant
    {:invoice/issue-date issue-instant ;; Add the issue date to the map
     :invoice/customer   {:customer/name  (:company_name (:customer invoice-data)) ;; Add the customer data to the map
                          :customer/email (:email (:customer invoice-data))} ;; Add the customer data to the map
     :invoice/items      (mapv (fn [item] ;; Add the item data to the map
                                 {:invoice-item/price    (double (:price item)) 
                                  :invoice-item/quantity (double (:quantity item))
                                  :invoice-item/sku      (:sku item) 
                                  :invoice-item/taxes    (mapv (fn [tax] 
                                                                 {:tax/category (keyword (str/lower-case (:tax_category tax)))
                                                                  :tax/rate     (double (:tax_rate tax))})
                                                               (:taxes item))})
                               (:items invoice-data))}))


;; Validate invoice data against the spec
(defn validate-invoice [invoice-data]
  (utils/validate-against-spec ::invoice-spec/invoice invoice-data))


(defn process-invoice-file [filename]
  (->> filename
       utils/read-json-file
       transform-json-to-spec
       validate-invoice))


;; Run the function with error handling
(utils/with-error-handling process-invoice-file "invoice.json")
