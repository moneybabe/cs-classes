def falling(n, k):
    """Compute the falling factorial of n to depth k.

    >>> falling(6, 3)  # 6 * 5 * 4
    120
    >>> falling(4, 3)  # 4 * 3 * 2
    24
    >>> falling(4, 1)  # 4
    4
    >>> falling(4, 0)
    1
    """
    "*** YOUR CODE HERE ***"
    
    if k == 0:
        return 1
    
    sum = n
    for i in range(1, k):
        sum = sum * (n - i)

    return sum
        


def sum_digits(y):
    """Sum all the digits of y.

    >>> sum_digits(10) # 1 + 0 = 1
    1
    >>> sum_digits(4224) # 4 + 2 + 2 + 4 = 12
    12
    >>> sum_digits(1234567890)
    45
    >>> a = sum_digits(123) # make sure that you are using return rather than print
    >>> a
    6
    """
    "*** YOUR CODE HERE ***"

    # string = str(y)
    # sum = 0

    # for i in string:
    #     sum = sum + int(i)

    # return sum

    current = 0
    sum = 0
    
    while y > 0:
        current = y % 10
        sum += current
        y = y // 10

    return sum


def divisible_by_k(n, k):
    """
    >>> a = divisible_by_k(10, 2)  # 2, 4, 6, 8, and 10 are divisible by 2
    2
    4
    6
    8
    10
    >>> a
    5
    >>> b = divisible_by_k(3, 1)  # 1, 2, and 3 are divisible by 1
    1
    2
    3
    >>> b
    3
    >>> c = divisible_by_k(6, 7)  # There are no integers up to 6 divisible by 7
    >>> c
    0
    """
    "*** YOUR CODE HERE ***"

    count = 0
    for i in range(1, n + 1):
        if i % k == 0:
            count += 1
            print(i)

    return count
    

def double_eights(n):
    """Return true if n has two eights in a row.
    >>> double_eights(8)
    False
    >>> double_eights(88)
    True
    >>> double_eights(2882)
    True
    >>> double_eights(880088)
    True
    >>> double_eights(12345)
    False
    >>> double_eights(80808080)
    False
    """
    "*** YOUR CODE HERE ***"

    # word = str(n)
    # for i in range (0, len(word) - 1):
    #     if word[i] == '8' and word[i + 1] == '8':
    #         return True
        
    # return False

    current = 0
    while n > 0 :
        current = n % 10
        next = (n // 10) % 10
        if current == 8 and next == 8:
            return True
        n = n // 10

    return False