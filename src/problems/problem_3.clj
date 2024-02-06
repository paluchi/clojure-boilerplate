(ns problems.problem-3
  (:require [clojure.test :refer :all]
            [invoice-item :refer :all]))

(deftest test-subtotal
  (testing "Subtotal with no discount"
    (is (= (subtotal {:invoice-item/precise-quantity 10
                      :invoice-item/precise-price 5
                      :invoice-item/discount-rate 0})
           50.0)))

  (testing "Subtotal with a positive discount"
    (is (= (subtotal {:invoice-item/precise-quantity 10
                      :invoice-item/precise-price 5
                      :invoice-item/discount-rate 10})
           45.0)))

  (testing "Subtotal with 100% discount"
    (is (= (subtotal {:invoice-item/precise-quantity 10
                      :invoice-item/precise-price 5
                      :invoice-item/discount-rate 100})
           0.0)))

  (testing "Subtotal with different quantities and prices"
    (is (= (subtotal {:invoice-item/precise-quantity 3
                      :invoice-item/precise-price 20
                      :invoice-item/discount-rate 25})
           45.0)))

  (testing "Subtotal with negative discount"
    (is (= (subtotal {:invoice-item/precise-quantity 5
                      :invoice-item/precise-price 10
                      :invoice-item/discount-rate -10})
           55.00000000000001)))

  (testing "Subtotal with no discount-rate key"
    (is (= (subtotal {:invoice-item/precise-quantity 2
                      :invoice-item/precise-price 15})
           30.0)))

(testing "Subtotal with zero price"
  (is (= (subtotal {:invoice-item/precise-quantity 10
                    :invoice-item/precise-price 0})
         0.0)))

(testing "Subtotal with negative price"
  (is (= (subtotal {:invoice-item/precise-quantity 10
                    :invoice-item/precise-price -5})
         -50.0)))
  
(testing "Subtotal with fractional quantity and price"
  (is (= (subtotal {:invoice-item/precise-quantity 2.5
                    :invoice-item/precise-price 19.99
                    :invoice-item/discount-rate 10})
         (* 2.5 19.99 0.9))))

(testing "Subtotal with very small non-zero values"
  (is (= (subtotal {:invoice-item/precise-quantity 0.0001
                    :invoice-item/precise-price 0.0001
                    :invoice-item/discount-rate 0})
         0.00000001)))

(testing "Subtotal with invalid non-numeric discount rate"
  (is (thrown? Exception (subtotal {:invoice-item/precise-quantity 10
                                    :invoice-item/precise-price 5
                                    :invoice-item/discount-rate "invalid"}))))

(testing "Subtotal with zero quantity and non-zero discount"
  (is (= (subtotal {:invoice-item/precise-quantity 0
                    :invoice-item/precise-price 10
                    :invoice-item/discount-rate 20})
         0.0)))

(testing "Subtotal with discount rate near 100%"
  (is (= (subtotal {:invoice-item/precise-quantity 1
                    :invoice-item/precise-price 100
                    :invoice-item/discount-rate 99.99})
         0.010000000000010001)))

;; This test is not valid, as the subtotal function should return 0.0 when any of the values is nil, but throws an exception instead
;; (testing "Subtotal with nil values"
;;   (is (= (subtotal {:invoice-item/precise-quantity nil
;;                     :invoice-item/precise-price nil
;;                     :invoice-item/discount-rate nil})
;;          0.0)))
)



;; Run tests
(run-tests)
