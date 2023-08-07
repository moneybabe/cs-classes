def prune_min(t):
    """Prune the tree mutatively.

    >>> t1 = Tree(6)
    >>> prune_min(t1)
    >>> t1
    Tree(6)
    >>> t2 = Tree(6, [Tree(3), Tree(4)])
    >>> prune_min(t2)
    >>> t2
    Tree(6, [Tree(3)])
    >>> t3 = Tree(6, [Tree(3, [Tree(1), Tree(2)]), Tree(5, [Tree(3), Tree(4)])])
    >>> prune_min(t3)
    >>> t3
    Tree(6, [Tree(3, [Tree(1)])])
    """
    if t.is_leaf():
        return
    left_branch, right_branch = t.branches[0], t.branches[1]
    t.branches = [left_branch] if left_branch.label < right_branch.label else [right_branch]
    if left_branch.label < right_branch.label:
        prune_min(left_branch)
    else:
        prune_min(right_branch)


def reverse_other(t):
    """Mutates the tree such that nodes on every other (odd-depth)
    level have the labels of their branches all reversed.

    >>> t = Tree(1, [Tree(2), Tree(3), Tree(4)])
    >>> reverse_other(t)
    >>> t
    Tree(1, [Tree(4), Tree(3), Tree(2)])
    >>> t = Tree(1, [Tree(2, [Tree(3, [Tree(4), Tree(5)]), Tree(6, [Tree(7)])]), Tree(8)])
    >>> reverse_other(t)
    >>> t
    Tree(1, [Tree(8, [Tree(3, [Tree(5), Tree(4)]), Tree(6, [Tree(7)])]), Tree(2)])
    """
    "*** YOUR CODE HERE ***"
    def reverse_order_track(t, is_odd):
        if t.is_leaf():
            return
        if is_odd:
            reverse_label_lst = [b.label for b in t.branches][::-1]
            for i in range(len(t.branches)):
                t.branches[i].label = reverse_label_lst[i]
        [reverse_order_track(branch, not is_odd) for branch in t.branches]
    reverse_order_track(t, True)



def link_pop(lnk, index=-1):
    '''Implement the pop method for a Linked List.
    
    >>> lnk = Link(1, Link(2, Link(3, Link(4, Link(5)))))
    >>> removed = link_pop(lnk)
    >>> print(removed)
    5
    >>> print(lnk)
    <1 2 3 4>
    >>> link_pop(lnk, 2)
    3
    >>> print(lnk)
    <1 2 4>
    >>> link_pop(lnk)
    4
    >>> link_pop(lnk)
    2
    >>> print(lnk)
    <1>
    '''
    if index == -1:
        while lnk.rest.rest is not Link.empty:
            lnk = lnk.rest
        removed = lnk.rest.first
        lnk.rest = Link.empty
    else:
        while index > 1:
            lnk = lnk.rest
            index -= 1
        removed = lnk.rest.first
        lnk.rest = lnk.rest.rest
    return removed


def crispr_gene_insertion(lnk_of_genes, insert):
    """Takes a linked list of genes and returns the genes with the INSERT codon added the correct number of times.

    >>> link = Link(Link("AUG", Link("GCC", Link("ACG"))), Link(Link("ATG", Link("AUG", Link("ACG", Link("GCC"))))))
    >>> print(link)
    <<AUG GCC ACG> <ATG AUG ACG GCC>>
    >>> crispr_gene_insertion(link, "TTA")
    >>> print(link)
    <<AUG TTA GCC ACG> <ATG AUG TTA TTA ACG GCC>>
    >>> link = Link(Link("ATG"), Link(Link("AUG", Link("AUG")), Link(Link("AUG", Link("GCC")))))
    >>> print(link)
    <<ATG> <AUG AUG> <AUG GCC>>
    >>> crispr_gene_insertion(link, "TTA") # first gene has no AUG so unchanged, 2nd gene has 2 AUGs but only first considered for insertion
    >>> print(link)
    <<ATG> <AUG TTA TTA AUG> <AUG TTA TTA TTA GCC>>
    >>> link = Link.empty # empty linked list of genes returns Link.empty
    >>> crispr_gene_insertion(link, "TTA")
    >>> print(link)
    ()
    """
    "*** YOUR CODE HERE ***"
    index = 0
    while lnk_of_genes is not Link.empty:
        gene = lnk_of_genes.first
        index += 1
        while gene is not Link.empty:
            if gene.first == "AUG":
                for _ in range(index):
                    gene.rest = Link(insert, gene.rest)
                    gene = gene.rest
                break
            gene = gene.rest
        lnk_of_genes = lnk_of_genes.rest


def transcribe(dna):
    """Takes a string of DNA and returns a Python list with the RNA codons.

    >>> DNA = "TACCTAGCCCATAAA"
    >>> transcribe(DNA)
    ['AUG', 'GAU', 'CGG', 'GUA', 'UUU']
    """
    dict = {'A': 'U', 'T': 'A', 'G': 'C', 'C': 'G'}
    return [dict[first] + dict[second] + dict[third] for first, second, third in zip(dna[::3], dna[1::3], dna[2::3])]


def partition_gen(n):
    """
    >>> partitions = [sorted(p) for p in partition_gen(4)]
    >>> for partition in sorted(partitions): # note: order doesn't matter
    ...     print(partition)
    [1, 1, 1, 1]
    [1, 1, 2]
    [1, 3]
    [2, 2]
    [4]
    """
    def yield_helper(num, segment):
        if num == 0:
            yield []
        elif num > 0 and segment > 0:
            for small_part in yield_helper(num - segment, segment):
                yield small_part + [segment]
            yield from yield_helper(num, segment - 1)
    yield from yield_helper(n, n)



def make_test_random():
    """A deterministic random function that cycles between
    [0.0, 0.1, 0.2, ..., 0.9] for testing purposes.

    >>> random = make_test_random()
    >>> random()
    0.0
    >>> random()
    0.1
    >>> random2 = make_test_random()
    >>> random2()
    0.0
    """
    rands = [x / 10 for x in range(10)]

    def random():
        rand = rands[0]
        rands.append(rands.pop(0))
        return rand
    return random


random = make_test_random()

# Phase 1: The Player Class


class Player:
    """
    >>> random = make_test_random()
    >>> p1 = Player('Hill')
    >>> p2 = Player('Don')
    >>> p1.popularity
    100
    >>> p1.debate(p2)  # random() should return 0.0
    >>> p1.popularity
    150
    >>> p2.popularity
    100
    >>> p2.votes
    0
    >>> p2.speech(p1)
    >>> p2.votes
    10
    >>> p2.popularity
    110
    >>> p1.popularity
    135
    >>> p1.speech(p2)
    >>> p1.votes
    13
    >>> p1.popularity
    148
    >>> p2.votes
    10
    >>> p2.popularity
    99
    >>> for _ in range(4):  # 0.1, 0.2, 0.3, 0.4
    ...     p1.debate(p2)
    >>> p2.debate(p1)
    >>> p2.popularity
    49
    >>> p2.debate(p1)
    >>> p2.popularity
    0
    """

    def __init__(self, name):
        self.name = name
        self.votes = 0
        self.popularity = 100

    def debate(self, other):
        "*** YOUR CODE HERE ***"

    def speech(self, other):
        "*** YOUR CODE HERE ***"

    def choose(self, other):
        return self.speech


# Phase 2: The Game Class
class Game:
    """
    >>> p1, p2 = Player('Hill'), Player('Don')
    >>> g = Game(p1, p2)
    >>> winner = g.play()
    >>> p1 is winner
    True
    >>> # Additional correctness tests
    >>> winner is g.winner()
    True
    >>> g.turn
    10
    >>> p1.votes = p2.votes
    >>> print(g.winner())
    None
    """

    def __init__(self, player1, player2):
        self.p1 = player1
        self.p2 = player2
        self.turn = 0

    def play(self):
        while not self.game_over():
            "*** YOUR CODE HERE ***"
        return self.winner()

    def game_over(self):
        return max(self.p1.votes, self.p2.votes) >= 50 or self.turn >= 10

    def winner(self):
        "*** YOUR CODE HERE ***"


# Phase 3: New Players
class AggressivePlayer(Player):
    """
    >>> random = make_test_random()
    >>> p1, p2 = AggressivePlayer('Don'), Player('Hill')
    >>> g = Game(p1, p2)
    >>> winner = g.play()
    >>> p1 is winner
    True
    >>> # Additional correctness tests
    >>> p1.popularity = p2.popularity
    >>> p1.choose(p2) == p1.debate
    True
    >>> p1.popularity += 1
    >>> p1.choose(p2) == p1.debate
    False
    >>> p2.choose(p1) == p2.speech
    True
    """

    def choose(self, other):
        "*** YOUR CODE HERE ***"


class CautiousPlayer(Player):
    """
    >>> random = make_test_random()
    >>> p1, p2 = CautiousPlayer('Hill'), AggressivePlayer('Don')
    >>> p1.popularity = 0
    >>> p1.choose(p2) == p1.debate
    True
    >>> p1.popularity = 1
    >>> p1.choose(p2) == p1.debate
    False
    >>> # Additional correctness tests
    >>> p2.choose(p1) == p2.speech
    True
    """

    def choose(self, other):
        "*** YOUR CODE HERE ***"


def deck(suits, ranks):
    """Creates a deck of cards (a list of 2-element lists) with the given
    suits and ranks. Each element in the returned list should be of the form
    [suit, rank].

    >>> deck(['S', 'C'], [1, 2, 3])
    [['S', 1], ['S', 2], ['S', 3], ['C', 1], ['C', 2], ['C', 3]]
    >>> deck(['S', 'C'], [3, 2, 1])
    [['S', 3], ['S', 2], ['S', 1], ['C', 3], ['C', 2], ['C', 1]]
    >>> deck([], [3, 2, 1])
    []
    >>> deck(['S', 'C'], [])
    []
    """
    "*** YOUR CODE HERE ***"
    return ______


def intersection(lst_of_lsts):
    """Returns a list of distinct elements that appear in every list in
    lst_of_lsts.

    >>> lsts1 = [[1, 2, 3], [1, 3, 5]]
    >>> intersection(lsts1)
    [1, 3]
    >>> lsts2 = [[1, 4, 2, 6], [7, 2, 4], [4, 4]]
    >>> intersection(lsts2)
    [4]
    >>> lsts3 = [[1, 2, 3], [4, 5], [7, 8, 9, 10]]
    >>> intersection(lsts3)         # No number appears in all lists
    []
    >>> lsts4 = [[3, 3], [1, 2, 3, 3], [3, 4, 3, 5]]
    >>> intersection(lsts4)         # Return list of distinct elements
    [3]
    """
    elements = []
    "*** YOUR CODE HERE ***"
    return elements


import re


def party_planner(text):
    """
    Returns all strings representing valid phone numbers with 314, 510, 310, or 514 area codes.
    The area code may or may not be surrounded by parentheses. Valid phone numbers
    have 10 digits and follow this format: XXX-XXX-XXXX, where each X represents a digit.

    >>> party_planner("(408)-996-3325, (510)-658-7400, (314)-3333-22222")
    True
    >>> party_planner("314-826-0705, (510)-314-3143, 408-267-7765")
    True
    >>> party_planner("5103143143")
    False
    >>> party_planner("514-300-2002, 310-265-4242") # invite your friends in LA and Montreal
    True
    """
    return bool(re.search(__________, text))


class Tree:
    """
    >>> t = Tree(3, [Tree(2, [Tree(5)]), Tree(4)])
    >>> t.label
    3
    >>> t.branches[0].label
    2
    >>> t.branches[1].is_leaf()
    True
    """

    def __init__(self, label, branches=[]):
        for b in branches:
            assert isinstance(b, Tree)
        self.label = label
        self.branches = list(branches)

    def is_leaf(self):
        return not self.branches

    def __repr__(self):
        if self.branches:
            branch_str = ', ' + repr(self.branches)
        else:
            branch_str = ''
        return 'Tree({0}{1})'.format(self.label, branch_str)

    def __str__(self):
        def print_tree(t, indent=0):
            tree_str = '  ' * indent + str(t.label) + "\n"
            for b in t.branches:
                tree_str += print_tree(b, indent + 1)
            return tree_str
        return print_tree(self).rstrip()


class Link:
    """A linked list.

    >>> s = Link(1)
    >>> s.first
    1
    >>> s.rest is Link.empty
    True
    >>> s = Link(2, Link(3, Link(4)))
    >>> s.first = 5
    >>> s.rest.first = 6
    >>> s.rest.rest = Link.empty
    >>> s                                    # Displays the contents of repr(s)
    Link(5, Link(6))
    >>> s.rest = Link(7, Link(Link(8, Link(9))))
    >>> s
    Link(5, Link(7, Link(Link(8, Link(9)))))
    >>> print(s)                             # Prints str(s)
    <5 7 <8 9>>
    """
    empty = ()

    def __init__(self, first, rest=empty):
        assert rest is Link.empty or isinstance(rest, Link)
        self.first = first
        self.rest = rest

    def __repr__(self):
        if self.rest is not Link.empty:
            rest_repr = ', ' + repr(self.rest)
        else:
            rest_repr = ''
        return 'Link(' + repr(self.first) + rest_repr + ')'

    def __str__(self):
        string = '<'
        while self.rest is not Link.empty:
            string += str(self.first) + ' '
            self = self.rest
        return string + str(self.first) + '>'
