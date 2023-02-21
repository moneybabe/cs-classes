clear all;   % This clears all workspaces
close all;   % This closes all figures 
clc;         % This clears the command window
format long; 

name = 'Neo Lee';
id = 'A17050803';
hw_num = 3;

%% Problem 1: 
A = zeros(30)
A(1:31:900) = 5
A(2:31:900) = -2
A(31:31:900) = -2
b = [-2 repelem(0, 28) 1]'
p1 = A\b

%% Problem 2:
% (a)
x = 0:0.1:10
p2a = x

% (b)
f = tanh(0.5.*x).^4.*exp(-1*(sin(x).^2))
p2b = f

% (c)
p2c = diff(f)./diff(x)

% (d)
p2d = p2c(x == 5)

%% Problem 3
% (a)
z = -5:0.1:5
p3a = z

% (b)
g = sech(z).^2.*sin(4*z).^4
p3b = g

% (c)
a = g(1:100) + g(2:101)
b = a/2*0.1
p3c = sum(b, 'all')

%% Problem 4
matA = 1:100; matA = abs(fix(100*cos(matA))); matA = reshape(matA,10,10);

% (a)
p4a = matA
p4a(p4a == max(p4a)) = -1

% (b)
p4b = matA
p4b(p4b == max(p4b(:))) = -2

% (c)
p4c = any(isprime(matA(:)))

% (d)
p4d = find(isprime(matA) == 1)

%% Problem 5
% (a)
p5a = clock

% (b)
p5b = sprintf('%i:%02i:%02i', p5a(1), p5a(2), p5a(3))

% (c)
p5c = sprintf('%02i:%02i:%07.4f', p5a(4), p5a(5), p5a(6))

% (d)
p5c(end-4:end) = []
p5d = p5c

% (e)
p5e = sprintf('%s %s', p5b, p5d)

%% Problem 6
% (a)
theta = 1:1:360
x = 16*sind(theta).^3
y = 13*cosd(theta) - 5*cosd(2*theta) - 2*cosd(3*theta) - cosd(4*theta)
figure(1)
hold on 
plot(x, y, '-rd', 'LineWidth', 5, 'MarkerFaceColor','cyan', 'MarkerEdgeColor', 'cyan', 'MarkerSize', 30, 'MarkerIndices', 360)
xlabel('x-axis')
ylabel('y-axis')
legend('heart')
title('Problem 6')
p6a = 'See figure 1'

% (b)
p6b = sum(sqrt(diff(x).^2 + diff(y).^2), 'all')

%% Problem 7
% (a)
theta = 0:0.5:1200
x = (1+0.25*cosd(50*theta)).*cosd(theta)
y = (1+0.25*cosd(50*theta)).*sind(theta)
z = (pi*theta)/180 + 2*sind(50*theta)
p7a = sum(sqrt(diff(x).^2 + diff(y).^2 + diff(z).^2), 'all')

% (b)
a = cumsum(sqrt(diff(x).^2 + diff(y).^2 + diff(z).^2))
[val, place] = min(abs(a-500))
p7b = [x(place + 1) y(place + 1) z(place + 1)]

% (c)
figure(2)
hold on 
plot3(x, y, z, '-mo', 'LineWidth', 0.5, ...
    'MarkerEdgeColor', 'k', 'MarkerFaceColor', 'k', 'MarkerSize', 10, 'MarkerIndices', 1 ...
    )
plot3(x, y, z, '-m^', 'LineWidth', 0.5, ...
    'MarkerEdgeColor', 'r', 'MarkerFaceColor', 'r', 'MarkerSize', 10, 'MarkerIndices', 2401 ...
    )
plot3(x, y, z, '-ms', 'LineWidth', 0.5, ...
    'MarkerEdgeColor', 'b', 'MarkerFaceColor', 'b', 'MarkerSize', 10, 'MarkerIndices', place+1 ...
    )
xlabel('x-axis')
ylabel('y-axis')
zlabel('z-axis')
legend('initial point', 'terminal point', '500 length point')
title('Problem 7')
view(3)
p7c = 'See figure 2'