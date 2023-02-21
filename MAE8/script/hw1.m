clear all;   % This clears all workspaces
close all;   % This closes all figures 
clc;         % This clears the command window
format long; 

name = 'Neo Lee';
id = 'A17050803';
hw_num = 1;

%% Problem 1: 
% (a) ratio of π to 0, put the answer in p1a
p1a = pi/0

% (b) ratio of 0 to 0, put the answer in p1b
p1b = 0/0

% (c) Square root of −4π, put the answer in p1c
p1c = sqrt(-4*pi)

% (d) Cosine of 75◦, put the answer in p1d
p1d = cosd(75)

% (e) Sine of π/3 radians, put the answer in p1e
p1e = sin(pi/3)

% (f) 1234 raised to the 5th power, put the answer in p1f
p1f = 1234^5

% (g) Ninth root of 512, put the answer in p1g
p1g = nthroot(512, 9)

% (h) Logarithm of 16,384 using base 2, put the answer in p1h
p1h = log2(16384)

% (i) Logarithm of 1,000,000 using base 10, put the answer in p1i
p1i = log10(1000000)

% (j) Natural logarithm of Euler’s number, put the answer in p1j
p1j = log(exp(1))

% (k) Inverse tangent of 1, put the answer (in degrees) in p1k
p1k = atand(1)

% (l) Hyperbolic sine of 6, put the answer in p1l
p1l = sinh(6)

% (m) Inverse hyperbolic tangent of 1, put the answer in p1m
p1m = atanh(1)

%% Problem 2:
% (a) Typecast real number 32π into character type and put the answer in p2a.
p2a = char(32*pi)

% (b) Typecast real number 16 into character type and put the answer in p2b.
p2b = char(16)

% (c) Typecast the answer in part (b) into double-precision type and put the answer in p2c.
p2c = double(p2b)

% (d) Typecast character Z into 16-bit integer type and put the answer in p2d.
p2d = int16('Z')

% (e) Typecast character Z into 32-bit integer type and put the answer in p2e.
p2e = int32('Z')

% (f) Typecast character Y into single-precision real type and put the answer in p2f.
p2f = single(char('Y'))

% (g) Typecast character X into double-precision real type and put the answer in p2g.
p2g = double('X')

% (h) Use function class to find the data type of variable p2a and put the answer in p2h.
p2h = class(p2a)

% (i) Compute the product of character Y and character Z. Put the answer in p2i.
p2i = 'Y'*'Z'

% (j) Is double('Y') equal to int64('Y')? Put the answer in p2j.
p2j = double('Y') == int64('Y')


%% Problem 3: Use MATLAB to find which of the following statements is true. Your answer should be logical with 0 for false and 1 for true.
% (a) Character y is equal to character Y . Put the answer in p3a.
p3a = 'y' == 'Y'

% (b) Character y is larger than character X. Put the answer in p3b.
p3b = 'y' > 'X'

% (c) Character z is smaller than character x. Put the answer in p3c.
p3c = 'z' < 'x'

% (d) log2(1024) is equal to 10. Put the answer in p3d.
p3d = log2(1024) == 10

% (e) sin(100π) is not equal to 0. Put the answer in p3e.
p3e = sin(100*pi) ~= 0

% (f) 3\9 + 1 is less than 3. Put the answer in p3f.
p3f = (3\9 + 1) < 3

% (g) 3/9 + 1 is less than 3. Put the answer in p3g.
p3g = (3/9 + 1) < 3

% In the following parts, create variables a = 3, b = 4 and c = 5:
a = 3;
b = 4;
c = 5;

% (h) a is greater than b and a is greater than c. Put the answer in p3h.
p3h = a > b && a > c

% (i) a is less than b and a is greater than c. Put the answer in p3i.
p3i = a < b && a > c

% (j) a is greater than b or a is greater than c. Put the answer in p3j.
p3j = a > b || a > c

% (k) a is greater than b or a less than c. Put the answer in p3k.
p3k = a > b || a < c 

% (l) a is exclusively smaller than b or c. Put the answer in p3l.
p3l = xor(a < b, a < c)

%% Problem 4: Use help elfun or experiment to answer the following questions. Your answers should be logical variables with 1 for true if the expressions are the same and 0 for false if the expressions are not the same:
% (a) Is fix(2.5) the same as floor(2.5)? Put the answer in p4a.
p4a = fix(2.5) == floor(2.5)

% (b) Is fix(2.4) the same as fix(-2.4)? Put the answer in p4b.
p4b = fix(2.4) == fix(-2.4)

% (c) Is fix(2.2) the same as floor(2.2)? Put the answer in p4c.
p4c = fix(2.2) == floor(2.2)

% (d) Is fix(-2.2) the same as floor(-2.2)? Put the answer in p4d.
p4d = fix(-2.2) == floor(-2.2)

% (e) Is fix(-2.2) the same as ceil(-2.2)? Put the answer in p4e.
p4e = fix(-2.2) == ceil(-2.2)

% (f) Is rem(5,3) the same as mod(5,3)? Put the answer in p4f.
p4f = rem(5,3) == mod(5,3)

% (g) Is rem(5,-3) the same as mod(5,-3)? Put the answer in p4g.
p4g = rem(5,-3) == mod(5,-3)

%% Problem 5: Explore the format command in more detail. Use help format to find options. Your answers should be reported as strings (i.e. if the correct format for part a was short your answer would be reported as p5a='short')
% (a) What format will display 123.45 as +? Put the answer in p5a.
p5a = '+'

% (b) What format will display π as 355/113? Put the answer in p5b.
p5b = 'rat'

% (c) Which format will display 63.43567 as 63.44? Put the answer in p5c.
p5c = 'bank'

% (d) What format will display 6666.333333 as 6666.3? Put the answer in p5d.
p5d = 'shortG'

%% Problem 6:
% In the ASCII character encoding, the letters of the alphabet are in order: 'a'comes before 'b'and also 'A'comes before 'B'.
% (a) Which comes first - lower-case or upper-case letters? Use the strings 'lower'or 'upper' for your response. Put the answer in p6a.
p6a = 'upper'

% (b) What is the absolute integer offset value between lower-case and upper-case letters? Put the answer in p6b.
p6b = abs('A' - 'a')

% (c) Is the answer from part (b) the same throughout the letter alphabet? The answer should be a logical statement, and put it in p6c.
p6c = isequal(abs(('A':'Z') - ('a':'z')), repelem(32, 26))

%% Problem 7: It is important for engineers and scientists to be able to work with colleagues in different parts of the world. Correct conversion of data from one system of units to another is critically important (for example, from the metric system to the American system or vice versa). Perform the following exercises:
% (a) Create a variable pounds = 1,000 to store a weight in pounds. Write an expression for a variable kilos to convert the weight into kilograms. The conversion factor is 1 kilogram = 2.2 pounds. What is the value of kilos? Put the answer in p7a.
pounds = 1000
kilos = pounds/2.2
p7a = kilos

% (b) Create a variable fn = 5.6 to store measurement of force in Newtons. Write an expression for the variable dynes to convert the force in Newtons into dynes. The conversion factor is 1 Newton = 105dynes. What is the value of dynes? Put the answer in p7b.
fn = 5.6
dynes = fn*105
p7b = fn *105

% (c) Create a variable ftemp = 212 to store a temperature in degrees Fahrenheit. Write an expression for a variable ctemp to convert the temperature into degrees Celsius (C). The conversion factor is C = (F −32) ∗5/9. What is the value of ctemp? Put the answer in p7c.
ftemp = 212
ctemp = (ftemp - 32)*5/9
p7c = ctemp

% (d) Create a variable mph = 65 to store a speed in miles per hour. Write an expression for a variable kmph to convert the speed into kilometers per hour. The conversion factor is 1 mile = 1.6093 kilometers. What is the value of kmph? Put the answer in p7d.
mph = 65
kmph = mph*1.6093
p7d = kmph

%% Problem 8:
% (a) Using function linspace, create a row vector p8a containing even integer numbers starting from 2 and ending at 998.
p8a = linspace(2, 998, 499)

% (b) Using colon operator, create a row vector p8b containing odd integer numbers starting from 1 and ending at 999.
p8b = 1:2:999

% (c) Combine the vectors created in parts (b) and (a) into to a longer vector p8c. Put part (b) first, then part (a).
p8c = [p8b p8a]

% (d) What is the length of the vector in part (c) ? Put the answer to p8d.
p8d = length(p8c)

% (e) Which element of the vector in part (c) is the number 500? Use function find. Put the answer to p8e.
p8e = find(p8c == 500)

% (f) Add the number 0 at the beginning of the vector in part (c) to create vector p8f.
p8f = [0 p8c]

% (g) Write an expression to extract the second quarter of the vector in part (f) into vector p8g.
p8g = p8f(251:500)

% (h) Write an expression to extract the fourth quarter of the vector in part (f) into vector p8h.
p8h = p8f(751:1000)

% (i) Create a column vector p8i to list all odd numbers from -1 to -1999. The vector should be listed in decreasing order.
p8i = (-1:-1:-1999)'

% (j) Square all of the elements of the vector in part (i) and put the answer in p8j.
p8j = p8i.^2

% (k) Sum all elements of the vector in part (i) and put the answer in p8k.
p8k = sum(p8i, 'all')

% (l) Find the product of the last five elements of the vector in part (i) and put the answer in p8l.
p8l = prod(p8i(end-4: end),'all')

% (m) Find the cumulative sum of the vector in part (i). Put the answer in p8m.
p8m = cumsum(p8i)

%% Problem 9:
% (a) Create the following matrix and put the answer in p9a. Do not input
% element by element. 
% 1 2 0 0 5 5 0 0 2 1
% 3 4 0 0 5 5 0 0 4 3
% 0 0 1 2 5 5 2 1 0 0 
% 0 0 3 4 5 5 4 3 0 0 
% 5 5 5 5 1 2 5 5 5 5
% 5 5 5 5 3 4 5 5 5 5
% 0 0 2 1 5 5 1 2 0 0
% 0 0 4 3 5 5 3 4 0 0
% 2 1 0 0 5 5 0 0 1 2
% 4 3 0 0 5 5 0 0 3 4
a = [1 2; 3 4];
b = [2 1; 4 3];
c = [5 5; 5 5];
d = zeros(2);
p9a = [a d c d b; d a c b d; c c a c c; d b c a d; b d c d a]

% (b) Sum all elements on the fifth column of the matrix in part (a) and put the answer in p9b.
p9b = sum(p9a(:,5))

% (c) Sum all elements on the two diagonals of the matrix in part (a) and put the answer in p9c.
p9c = sum(diag(p9a) + diag(fliplr(p9a)))

% (d) Sum all elements of the matrix in part (a) and put the answer in p9d.
p9d = sum(p9a, 'all')

% (e) How many elements of the matrix in part (a) are greater than 2? Put the answer in p9e.
p9e = sum(p9a > 2, 'all')