function amount = lottery(ticket)
% lottery function tells you how much you win
% input [a b c d e f] with the sixth integers on your ticket

winning_num = load('winning_number.dat');
matching_num = length(intersect(winning_num, ticket));

if matching_num == 1
    amount = 10;
elseif matching_num == 2
    amount = 100;
elseif matching_num == 3
    amount = 1000;
elseif matching_num == 4
    amount = 10000;
elseif matching_num == 5
    amount = 1000000;
elseif matching_num == 6
    amount = 100000000;
else
    amount = 0;
end

