(define (make-even t) 
    (if (null? t)
        nil
        (if (even? (label t))
            (tree (label t) 
                    (map make-even (branches t))
            )
            (tree (+ (label t) 1)
                    (map make-even (branches t))
            )
        )
    )
)

(define (find t x) 
    (cond 
        ((= (label t) x) 
            #t)
        ((is-leaf t) 
            #f)
        (else
            (>= (length 
                    (filter 
                        (lambda(b) b) 
                        (map 
                            (lambda(b) (find b x)) 
                            (branches t)
                        )
                    ) 
                )
                1
            )
        )
    )
)

(define (n-ary t n) 
    (if (is-leaf t)
        #t
        (and 
            (= (length (branches t)) n)
            (=
                (length
                    (filter 
                        (lambda(b) b)
                        (map 
                            (lambda(b) (n-ary b n)) 
                            (branches t)
                        )
                    )
                )
                (length (branches t))
            )
        )
    )
)
