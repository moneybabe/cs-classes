clear all;   % This clears all workspaces
close all;   % This closes all figures 
clc;         % This clears the command window
format long; 

name = 'Neo Lee';
id = 'A17050803';
hw_num = 6;

%% Problem 1
% (a)
a = 0;
for n = 1:40
    a = a + 2^n/factorial(n);
end
p1a = a

% (b)
a = 0;
for m = 0:40
    for n = 0:40
        a = a + 1/(3^(m+n));
    end
end
p1b = a

% (c)
a = 0;
for m = 0:40
    for n = 0:m
        a = a + 1/(3^(m+n));
    end
end
p1c = a

% (d)
a = 0;
for l = 1:40
    for m = 1:40
        for n = 1:40
            a = a + 1/(2^l * 2^m * 2^n);
        end
    end
end
p1d = a

% (e)
a = 0;
for l = 1:5
    for m = l:5
        for n = l:m
            a = a + 1;
        end
    end
end
p1e = a

% (f)
a = 4/3;
for n = 2:1000
    a = a*4*n^2/(4*n^2 - 1);
end
p1f = a

%% Problem 2
% (a)
n = 0;
while exp(1)/factorial(n+1) >= 10^-7
    n = n +1;
end
p2a = n

% (b)
n = 0;
while 2^(n+1)*(n+1) <= 10^7
    n = n + 1;
end
p2b = n

%% Problem 3
d = 10;
h = 10;
n = 0;
while d < 59.99
    n = n + 1;
    d = d + 2*h*0.75^n;
end
p3a = n
p3b = h*0.75^n

%% Problem 4
% (a-c)
how_count = 0;
are_count = 0;
for_count = 0;
load stringA.mat 
for n = 1:length(stringA)-2
    word = stringA(n:n+2);
    switch word
        case 'how'
            how_count = how_count + 1;
        case 'are'
            are_count = are_count + 1;
        case 'for'
            for_count = for_count + 1;
    end
end
p4a = how_count
p4b = are_count
p4c = for_count

% (d-f)
many_count = 0;
time_count = 0;
loop_count = 0;
load stringA.mat 
for n = 1:length(stringA)-3
    word = stringA(n:n+3);
    switch word
        case 'many'
            many_count = many_count + 1;
        case 'time'
            time_count = time_count + 1;
        case 'loop'
            loop_count = loop_count + 1;
    end
end
p4d = many_count
p4e = time_count
p4f = loop_count

%% Problem 5
% (a, b)
a = (-1)^0 / (2*0 + 1);
n = 0;
while abs(pi-4*a) > 10^-5
    n = n + 1;
    a = a + (-1)^n / (2*n + 1);
end
p5a = 4*a
p5b = n

% (c, d)
a = (-3)^0 / (2*0 + 1);
n = 0;
while abs(pi-sqrt(12)*a) > 10^-5
    n = n + 1;
    a = a + (-3)^(-n) / (2*n + 1);
end
p5c = sqrt(12)*a
p5d = n

% (e)
p5e = 'The series in part (c, d) converges faster'