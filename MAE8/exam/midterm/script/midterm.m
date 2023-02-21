clear all; close all; clc; format long
name = 'Neo Lee';
id = 'A17050803';
hw_num = 'midterm';
form = 'A'

%% Problem 1
% (a)
p1a = 1:199

% (b)
p1b = sqrt(2:200)

% (c)
p1c = sum(p1a./p1b, 'all')

%% Problem 2
A = [1 5 6; 8 3 4; 7 2 8]
b = [7 2 5]'
x = A\b
p2 = x

%% Problem 3
rng(int8(form)); matA = randi(50,10)

% (a)
p3a = sum(matA(end,:), 'all')

% (b)
a = [matA(1,:) matA(3,:) matA(5,:) matA(7,:) matA(10,:)]
p3b = mean(a, 'all')

% (c)
p3c = prod(matA(isprime(matA) == 0), 'all')

% (d)
p3d = matA
p3d(matA == max(matA(:))) = min(matA(:))

% (e)
p3e = sprintf('%7.2f', matA(1,(end-4):end))

%% Problem 4
theta = 1:1080
x = 2*cosd(theta) + 3*cosd(2*theta/3)
y = 2*sind(theta) - 3*sind(2*theta/3)

% (a) 
p4a = sum(sqrt(diff(x).^2 + diff(y).^2), 'all')

% (b)
p4b = diff(x)/1

% (c)
p4c = diff(y)/1

% (d)
p4d = sqrt(p4b.^2 + p4c.^2)

% (e)
a = p4d(1:end-1) + p4d(2:end)
b = a/2*1
p4e = sum(b, 'all')

% (f)
p4f = abs(p4a - p4e)

% (g)
figure(1)
hold on
plot(x, y, '-r', 'LineWidth', 2)
plot(min(x), y(x == min(x)), 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 10)
plot(max(x), y(x == max(x)), 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 10)
plot(x(y == max(y)), y(y == max(y)), 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 10)
plot(x(y == min(y)), y(y == min(y)), 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 10)
title('Problem 4')
xlabel('x')
ylabel('y')
legend('parametric curve', 'corners of the star', 'Location','northwest' )
axis([-5 5 -5 5])
p4g = 'See figure 1'