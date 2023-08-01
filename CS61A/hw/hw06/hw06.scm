(define (reverse lst)
  (define (tail lst so-far)
    (if (null? lst)
      so-far
      (tail (cdr lst) (cons (car lst) so-far))
    )
  )
  (tail lst nil)
)

(define (no-repeats lst) 
  (define (tail-no-repeats lst so-far)
    (if (null? lst)
      so-far
      (if (= 
            (length 
              (filter (lambda(x) (= x (car lst))) so-far)
            )
            1
          )
          (tail-no-repeats (cdr lst) so-far)
          (tail-no-repeats (cdr lst) (cons (car lst) so-far))
      )
    )
  )
  (reverse (tail-no-repeats lst nil))
)

(define (student-attend-class student class)
  (student-create
    (student-get-name student)
    (cons class (student-get-classes student))
  )  
)

(define (teacher-hold-class teacher)
  (teacher-create
    (teacher-get-name teacher)
    (teacher-get-class teacher)
    (map 
      (lambda(student) 
        (student-attend-class student (teacher-get-class teacher))
      )
      (teacher-get-students teacher)
    )
  )
)

(define (add-leaf t x)
  (if (is-leaf t)
      t
      (begin (define mapped-branches
                     (map (lambda(b) (add-leaf b x)) (branches t)))
             (tree (label t)
                   (append mapped-branches (list (tree x nil)))))))
