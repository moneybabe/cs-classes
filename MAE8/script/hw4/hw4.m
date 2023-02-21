clear all;   % This clears all workspaces
close all;   % This closes all figures 
clc;         % This clears the command window
format long; 

name = 'Neo Lee';
id = 'A17050803';
hw_num = 4;

%% Problem 1: 
A = [log(1/2) log(1/3) log(1/4) log(1/5);
    log(1/6) log(1/7) log(1/8) log(1/9);
    log(1/10) log(1/11) log(1/12) log(1/13)]
% (a)
p1a = sprintf('%9.6f\n', A(:,4))

% (b)
p1b = sprintf('%7.4f ', A(3,:))

% (c)
p1c = sprintf('%10.7f %10.7f %10.7f %10.7f\n', A')

% (d)
p1d = sprintf('%10.7e %10.7e %10.7e %10.7e\n', A')

%% Problem 2
temp = load('temperature.dat')
temp_only = temp(:,2:13)
% (a) & (b)
[a, b] = find(temp_only == max(temp_only(:)))
p2a = b
p2b = a + 1851

% (c)
p2c = sum(temp_only(find(temp == 1900):find(temp == 2000),:) > 75, 'all')

% (d)
p2d = sum(temp_only(find(temp == 1900):find(temp == 2000),6:8) > 75, 'all')

% (e)
average_temp = mean(temp_only)
figure(1)
bar(average_temp)
xlabel('months')
ylabel('average temperature')
title('p2e')
p2e = 'See figure 1'

% (f)
p2f = 'In general, the temperature peaks in August'

% (g)
temp_cycle = [1:12; average_temp]'
save('annual_cycle.dat', 'temp_cycle', '-ascii')
p2g= evalc('type(''annual_cycle.dat'')')

% (h)
annual_mean_temp = mean(temp_only')
figure(2)
hold on
plot(1852:2020,annual_mean_temp, '-k', 'LineWidth', 2)
plot(find(annual_mean_temp > 65) + 1851, annual_mean_temp(annual_mean_temp > 65), 'ro', 'MarkerFaceColor', 'r')
plot(find(annual_mean_temp < 60) + 1851, annual_mean_temp(annual_mean_temp < 60), 'bd', 'MarkerFaceColor', 'b')
y_diff = diff(annual_mean_temp)
up_x1 = find(y_diff == max(y_diff))
up_x2 = up_x1 + 1
plot([up_x1+1851 up_x2+1851], [annual_mean_temp(up_x1) annual_mean_temp(up_x2)], '-r', 'LineWidth', 4)
down_x1 = find(y_diff == min(y_diff))
down_x2 = down_x1 + 1
plot([down_x1+1851 down_x2+1851], [annual_mean_temp(down_x1) annual_mean_temp(down_x2)], '-b', 'LineWidth', 4)
title('p2h')
xlabel('Year')
ylabel('Annual Mean Temperature')
legend('temp', 'temp > 65F', 'temp < 60F', 'rapid rise', 'rapid drop')
p2h = 'See figure 2'

% (i)
p2i = 'In general, the temperature get warmer over the years'

%% Problem 3
p3a = evalc('help lottery')
p3b = lottery([2, 3, 4, 5, 6, 7])
p3c = lottery([12, 23, 24, 34, 50, 61])
p3d = lottery([22, 33, 44, 50, 51, 61])
p3e = lottery([32, 43, 54, 44, 51, 61])
p3f = lottery([42, 53, 34, 44, 51, 61])
p3g = lottery([42, 23, 34, 44, 51, 61])
p3h = lottery([12, 23, 34, 44, 51, 61])

%% Problem 4
p4a = evalc('help piecewise2d')
p4b = piecewise2d(1, 1)
p4c = piecewise2d(1, -1)
p4d = piecewise2d(-1, 1)
p4e = piecewise2d(-1, -1)
p4f = piecewise2d(0, 0)
p4g = piecewise2d(0, 1)
p4h = piecewise2d(0, -1)
p4i = piecewise2d(1, 0)
p4j = piecewise2d(-1, 0)

%% Problem 5
p5a = evalc('help rgb_color')
p5b = rgb_color([1 1 1])
p5c = rgb_color([1 0 0])
p5d = rgb_color([0 1 0])
p5e = rgb_color([0 0 1])
p5f = rgb_color([1 1 0])
p5g = rgb_color([0 1 1])
p5h = rgb_color([1 0 1])
p5i = rgb_color([0 0 0])