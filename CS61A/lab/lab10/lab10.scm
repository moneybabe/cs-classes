(define (make-adder num) 
  ; (lambda (x) (+ x num))

  (define (add x)
    (+ num x)
  )
  add

)

(define (composed f g) 
  ; (lambda (x) (f (g x)))

  (define (run x)
    (f (g x))
  )
  run

)

; (define (my-filter pred s)
;   (if (null? s) 
;     nil
;     (if (pred (car s )) 
;       (cons (car s) (my-filter pred (cdr s)))
;       (my-filter pred (cdr s))
;     )
;   )
; )

(define (reverse s)
  (define (default-nil-reverse s built-lst)
    (if (null? s) 
      built-lst
      (default-nil-reverse (cdr s) (cons (car s) built-lst))
    )
  )
  (default-nil-reverse s nil)
)

(define (my-filter pred s) 
  (define (tail-reverse-filter pred s built-lst)
    (if (null? s) 
      built-lst
      (if (pred (car s))
        (tail-reverse-filter pred (cdr s) (cons (car s) built-lst))
        (tail-reverse-filter pred (cdr s) built-lst)
      )
    )
  )
  (reverse (tail-reverse-filter pred s nil))
)

(define (exp b n)
  (define (helper b n so-far) 
    (if (= n 0) 
      so-far
      (helper b (- n 1) (* b so-far))
    )
  )
  (helper b n 1)
)

; (define (interleave lst1 lst2) 

;   (define (add-lst1 lst1 lst2)
;     (if (null? lst1) 
;       lst2
;       (cons (car lst1) (add-lst2 (cdr lst1) lst2))
;     ) 
;   )

;   (define (add-lst2 lst1 lst2)
;     (if (null? lst2) 
;       lst1
;       (cons (car lst2) (add-lst1 lst1 (cdr lst2)))
;     )
;   )

;   (add-lst1 lst1 lst2)

; )

(define (interleave lst1 lst2)
  
  (define (helper lst1 lst2 add-lst1 built-lst)
    (cond
      ((and (null? lst1) (null? lst2)) 
        built-lst
      )
      (add-lst1 
        (if (null? lst1)
          (helper lst1 lst2 #f built-lst)
          (helper (cdr lst1) lst2 #f (cons (car lst1) built-lst))
        )
      )
      (else
        (if (null? lst2)
          (helper lst1 lst2 #t built-lst)
          (helper lst1 (cdr lst2) #t (cons (car lst2) built-lst))
        )
      )
    )
  )

  (reverse (helper lst1 lst2 #t nil))

)

(define (square n) (* n n))

(define (pow base exp) 
  (cond 
    ((= exp 1) 
      base
    )
    ((even? exp) 
      (square (pow base (/ exp 2)))
    )
    (else
      (* base (pow base (- exp 1)))
    )
  )
)

