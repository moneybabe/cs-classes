clear all;   % This clears all workspaces
close all;   % This closes all figures 
clc;         % This clears the command window
format long; 

name = 'Neo Lee';
id = 'A17050803';
hw_num = 5;

%% Problem 1
p1a = evalc('help assign_grade')
load('studentA.mat'); [p1b, p1c] = assign_grade(homework, midterm, project, final)
load('studentB.mat'); [p1d, p1e] = assign_grade(homework, midterm, project, final)
load('studentC.mat'); [p1f, p1g] = assign_grade(homework, midterm, project, final)

%% Problem 2
p2a = evalc('help days_in_month')
p2b = days_in_month('jan',0)
p2c = days_in_month('feb',0)
p2d = days_in_month('feb',1)
p2e = days_in_month('apr',0)
p2f = days_in_month('aug',1)
p2g = days_in_month('oct',0)
p2h = days_in_month('nov',1)
p2i = days_in_month('Dec',0)

%% Problem 3
% (a)
p3a = zeros(1,50);
p3a(1:5) = [1 1 2 3 5];
for n = 6:50
    p3a(n) = p3a(n-2) + p3a(n-1);
end
p3a

% (b)
p3b = 0;
for n = 1:50
    p3b = p3b + p3a(n);
end
p3b

% (c)
p3c = zeros(1,50);
for n = 2:50
    p3c(n) = p3a(n)/p3a(n-1);
end
p3c

%% Problem 4
% (a)
p4a = sqrt(2);
for n = 1:9
    p4a = sqrt(p4a + 2);
end
p4a

% (b)
p4b = sqrt(1);
for n = 1:9
    p4b = sqrt(2*p4b + 1)
end
p4b

% (c)
p4c = sqrt(2);
for n = 1:9
    p4c = sqrt(((-1)^n)*p4c + 2);
end
p4c

%% Problem 5
p5 = evalc('type(''survey.dat'')')