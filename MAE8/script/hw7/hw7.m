clear all;   % This clears all workspaces
close all;   % This closes all figures 
clc;         % This clears the command window
format long; 

name = 'Neo Lee';
id = 'A17050803';
hw_num = 7;

%% Problem 1
% (a - d)
i = 0;
load terrain.mat
[n, m] = size(altitude)
for a = 2:n-1
    for b = 2:m-1
        compare_element = altitude(a-1:a+1, b-1:b+1);
        if altitude(a, b) >= max(compare_element(:))
            i = i + 1;
            peakx(i) = x(a);
            peaky(i) = y(b);
            peak_altitude(i) = altitude(a,b);
        end
    end
end
p1a = i
p1b = peakx
p1c = peaky
p1d = peak_altitude

% (e - g)
[a, b] = find(altitude > 1100);
p1e = x(a)
p1f = y(b)
p1g = altitude(altitude > 1100)'

% for extra credit (i)
n = find(x == 8000)
m = find(y == -8000)
i = 1;
compare_element = altitude(n-1:n+1, m-1:m+1)
altitude_track(1) = altitude(n, m)
trackx(1) = x(n)
tracky(1) = y(m)
while altitude(n, m) > min(compare_element(:))
    i = i+1;
    [a, b] = find(compare_element == min(compare_element(:)))
    n = n + a - 2
    m = m + b - 2
    altitude_track(i) = min(compare_element(:))
    trackx(i) = x(n)
    tracky(i) = y(m)
    compare_element = altitude(n-1:n+1, m-1:m+1)
end

% plot graph for (h) & (i)
figure(1)
hold on
grid on
surf(x, y, altitude')
shading interp
plot3(peakx, peaky, peak_altitude, 'ro', 'MarkerSize', 10, 'MarkerFaceColor', 'r')
plot3(p1e, p1f, p1g, 'go', 'MarkerSize', 4, 'MarkerFaceColor', 'g')
plot3(trackx, tracky, altitude_track, '-m', 'LineWidth', 4)
view(3)
xlabel('x-axis (m)')
ylabel('y-axis (m)')
zlabel('altitude (km)')
title('p1h & p1i')
legend('terrain', 'peak', 'snow', 'ball trajectory')
p1h = 'See figure 1'
p1i = 'See ball trajectory'

%% Problem 2
load matB.mat
% (a)
[n, m] = size(matB)
element_sum = 0;
for b = 2:m
    for a = 1:n
        if a == b
            break
        end
        element_sum = element_sum + matB(a,b);
    end
end
p2a = element_sum

% (b)
element_product = 1;
for a = 2:n
    for b = 1:m
        if a == b
            break
        end
        element_product = element_product*matB(a, b);
    end
end
p2b = element_product

% (c)
element_sum = 0;
for a = 1:n
    for b = 1:m
        if b == 2*a
            continue
        end
        element_sum = element_sum + matB(a, b);
    end
end
p2c = element_sum

% (d)
element_product = 1;
for a = 1:n
    for b = 1:m
        if matB(a, b) > a
            continue
        end
        element_product = element_product*matB(a, b);
    end
end
p2d = element_product

%% Problem 3
% (a)
p3a = evalc('help car')

% (b - d)
[p3b, p3c, p3d] = car(60, 10)

% (e - g)
[p3e, p3f, p3g] = car(60, 1)

% (h)
figure(2)
subplot(2,1,1)
hold on
plot(p3e, p3f, '-co', 'LineWidth', 2, 'MarkerFaceColor', 'c', 'MarkerSize', 3)
plot(p3b, p3c, '-ro', 'LineWidth', 2, 'MarkerFaceColor', 'r', 'MarkerSize', 3)
xlabel('time (s)')
ylabel('distance (m)')
title('distance vs time')
legend('1s time step', '10s time step', 'Location','northwest')

subplot(2,1,2)
hold on
plot(p3e, p3g, '-co', 'LineWidth', 2, 'MarkerFaceColor', 'c', 'MarkerSize', 3)
plot(p3b, p3d, '-ro', 'LineWidth', 2, 'MarkerFaceColor', 'r', 'MarkerSize', 3)
xlabel('time (s)')
ylabel('velocity (m/s)')
title('velocity vs time')
legend('1s time step', '10s time step', 'Location','northwest')

p3h = 'See figure 2'

%% Problem 4
p4a = evalc('help rocket')
p4b = evalc('help gravity')
p4c = evalc('help thrust')
p4d = evalc('help rocket>gravity')
p4e = evalc('help rocket>thrust')
[T, Z, W] = rocket(120, 0.1)
p4f = Z(end)
p4g = W(end)

figure(3)
subplot(2,1,1)
plot(T, Z, '-ro', 'LineWidth', 2, 'MarkerSize', 1, 'MarkerFaceColor', 'r')
xlabel('time (s)')
ylabel('altitude (m)')
legend('altitude vs time', 'Location','southeast')
title('altitude vs time')

subplot(2,1,2)
plot(T, W, '-co', 'LineWidth', 2, 'MarkerSize', 1, 'MarkerFaceColor', 'c')
xlabel('time (s)')
ylabel('velocity (m/s)')
legend('veclocity vs time', 'Location', 'northeast')
title('veclocity vs time')
p4h = 'See figure 3'