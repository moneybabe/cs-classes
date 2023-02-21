clear all;   % This clears all workspaces
close all;   % This closes all figures 
clc;         % This clears the command window
format long; 

name = 'Neo Lee';
id = 'A17050803';
hw_num = 'project';

%% Task 1
load ('terrain.mat');

m = zeros(1,18);
k = zeros(1,18);
l = zeros(1,18);
Xo = zeros(1,18);
Yo = zeros(1,18);
Zo = zeros(1,18);
Uo = zeros(1,18);
Vo = zeros(1,18);
Wo = zeros(1,18);
safety = zeros(1,18);
T = cell(1,18);
X = cell(1,18);
Y = cell(1,18);
Z = cell(1,18);
U = cell(1,18);
V = cell(1,18);
W = cell(1,18);
legend_name = cell(1,18);

for n = 1:18
    [m(n), k(n), l(n), Xo(n), Yo(n), Zo(n), Uo(n), Vo(n), Wo(n)] = read_input('bungee_data.txt', n);
    [T{n}, X{n}, Y{n}, Z{n}, U{n}, V{n}, W{n}, safety(n)] = bungee(m(n), k(n), l(n), Xo(n), Yo(n), Zo(n), Uo(n), Vo(n), Wo(n));
    if safety(n) == 0
        legend_name{n} = sprintf('Exp #%i %s', n, 'danger');
    else
        legend_name{n} = sprintf('Exp #%i', n);
    end

end


%% figure(1)

figure(1)
for n = 1:6
%     [m, k, l, Xo, Yo, Zo, Uo, Vo, Wo] = read_input('bungee_data.txt', n);
%     [T, X, Y, Z, U, V, W, safety] = bungee(m, k, l, Xo, Yo, Zo, Uo, Vo, Wo);
    subplot(2, 3, n)
    hold on
    grid on
    surf(x_terrain, y_terrain, h_terrain);
    colormap('jet');
    shading interp;
    plot3(X{n}, Y{n}, Z{n}, '-k', 'LineWidth', 1)
    plot3(X{n}(end), Y{n}(end), Z{n}(end), 'ro', 'MarkerFaceColor', 'r', 'MarkerSize', 8)
    xlim('tight')
    ylim('tight')
    zlim('tight')
    xlabel('x (m)')
    ylabel('y (m)')
    zlabel('z (m)')    
    xlabel('x (m)')
    ylabel('y (m)')
    zlabel('z (m)')
    title(legend_name{n})

    view(3)
end

%% figure(2)

i = 0;
figure(2)
for n = 7:12
    i = i + 1;
%     [m, k, l, Xo, Yo, Zo, Uo, Vo, Wo] = read_input('bungee_data.txt', n);
%     [T, X, Y, Z, U, V, W, safety] = bungee(m, k, l, Xo, Yo, Zo, Uo, Vo, Wo);
    subplot(2, 3, i)
    hold on
    grid on
    surf(x_terrain, y_terrain, h_terrain);
    colormap('jet');
    shading interp;
    plot3(X{n}, Y{n}, Z{n}, '-k', 'LineWidth', 1)
    plot3(X{n}(end), Y{n}(end), Z{n}(end), 'ro', 'MarkerFaceColor', 'r', 'MarkerSize', 8)
    xlim('tight')
    ylim('tight')
    zlim('tight')
    xlabel('x (m)')
    ylabel('y (m)')
    zlabel('z (m)')
    title(legend_name{n})

    view(3)
end

%% figure(3)

i = 0;
figure(3)
for n = 13:18
    i = i + 1;
%     [m, k, l, Xo, Yo, Zo, Uo, Vo, Wo] = read_input('bungee_data.txt', n);
%     [T, X, Y, Z, U, V, W, safety] = bungee(m, k, l, Xo, Yo, Zo, Uo, Vo, Wo);
    subplot(2, 3, i)
    hold on
    grid on
    surf(x_terrain, y_terrain, h_terrain);
    colormap('jet');
    shading interp;
    plot3(X{n}, Y{n}, Z{n}, '-k', 'LineWidth', 1)
    plot3(X{n}(end), Y{n}(end), Z{n}(end), 'ro', 'MarkerFaceColor', 'r', 'MarkerSize', 8)
    xlim('tight')
    ylim('tight')
    zlim('tight')
    xlabel('x (m)')
    ylabel('y (m)')
    zlabel('z (m)')
    title(legend_name{n})

    view(3)
end

%% figure(4)
% legend_name = cell(1,18);
distance_vector = zeros(1,18);
for n = 1:18
%     [m, k, l, Xo, Yo, Zo, Uo, Vo, Wo] = read_input('bungee_data.txt', n);
%     [T, X, Y, Z, U, V, W, safety] = bungee(m, k, l, Xo, Yo, Zo, Uo, Vo, Wo);
    distance = sqrt( X{n}.^2 + Y{n}.^2 + Z{n}.^2 );
    distance_vector(n) = max(distance);
%     if safety(n) == 0
%         legend_name{n} = sprintf('Exp #%i %s', n, 'danger');
%     else
%         legend_name{n} = sprintf('Exp #%i', n);
%     end
end

figure(4)
subplot(1, 3, 1)
hold on
grid on
plot(m(1), distance_vector(1), 'ro', 'MarkerFaceColor', 'r', 'MarkerSize', 10)
plot(m(2), distance_vector(2), 'yo', 'MarkerFaceColor', 'y', 'MarkerSize', 10)
plot(m(3), distance_vector(3), 'go', 'MarkerFaceColor', 'g', 'MarkerSize', 10)
plot(m(4), distance_vector(4), 'co', 'MarkerFaceColor', 'c', 'MarkerSize', 10)
plot(m(5), distance_vector(5), 'mo', 'MarkerFaceColor', 'm', 'MarkerSize', 10)
plot(m(6), distance_vector(6), 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 10)
plot(m(1:6), distance_vector(1:6), '-k', 'LineWidth', 1)
xlim('tight')
ylim('tight')
title('Effect of mass')
xlabel('mass (kg)')
ylabel('max distance (m)')
legend(legend_name{1}, legend_name{2}, legend_name{3}, legend_name{4}, legend_name{5}, legend_name{6}, 'Location', 'southeast')

subplot(1, 3, 2)
hold on
grid on
plot(k(7), distance_vector(7), 'ro', 'MarkerFaceColor', 'r', 'MarkerSize', 10)
plot(k(8), distance_vector(8), 'yo', 'MarkerFaceColor', 'y', 'MarkerSize', 10)
plot(k(9), distance_vector(9), 'go', 'MarkerFaceColor', 'g', 'MarkerSize', 10)
plot(k(10), distance_vector(10), 'co', 'MarkerFaceColor', 'c', 'MarkerSize', 10)
plot(k(11), distance_vector(11), 'mo', 'MarkerFaceColor', 'm', 'MarkerSize', 10)
plot(k(12), distance_vector(12), 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 10)
plot(k(7:12), distance_vector(7:12), '-k', 'LineWidth', 1)
xlim('tight')
ylim('tight')
title('Effect of spring modulus')
xlabel('spring modulus (N/m)')
ylabel('max distance (m)')
legend(legend_name{7}, legend_name{8}, legend_name{9}, legend_name{10}, legend_name{11}, legend_name{12}, 'Location', 'northeast')

subplot(1, 3, 3)
hold on
grid on
plot(l(13), distance_vector(13), 'ro', 'MarkerFaceColor', 'r', 'MarkerSize', 10)
plot(l(14), distance_vector(14), 'yo', 'MarkerFaceColor', 'y', 'MarkerSize', 10)
plot(l(15), distance_vector(15), 'go', 'MarkerFaceColor', 'g', 'MarkerSize', 10)
plot(l(16), distance_vector(16), 'co', 'MarkerFaceColor', 'c', 'MarkerSize', 10)
plot(l(17), distance_vector(17), 'mo', 'MarkerFaceColor', 'm', 'MarkerSize', 10)
plot(l(18), distance_vector(18), 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 10)
plot(l(13:18), distance_vector(13:18), '-k', 'LineWidth', 1)
xlim('tight')
ylim('tight')
title('Effect of cord length')
xlabel('cord length (m)')
ylabel('max distance (m)')
legend(legend_name{13}, legend_name{14}, legend_name{15}, legend_name{16}, legend_name{17}, legend_name{18}, 'Location', 'southeast')

%% Task 2

number = zeros(1, 18);
max_speed = zeros(1, 18);
max_acceleration = zeros(1,18);
integrated_KE = zeros(1, 18);
travel_distance = zeros(1, 18);

for n = 1:18
    number(n) = n;
    max_speed(n) = max(sqrt(U{n}.^2 + V{n}.^2 + W{n}.^2));
    max_acceleration(n) = max(diff(sqrt(U{n}.^2 + V{n}.^2 + W{n}.^2))/0.02);
    KE = 1/2*m(n)*(sqrt(U{n}.^2 + V{n}.^2 + W{n}.^2).^2);
    integrated_KE(n) = sum((KE(1:end-1)+KE(2:end))/2*0.02, 'all');
    travel_distance(n) = sum(sqrt(diff(X{n}).^2 + diff(Y{n}).^2 + diff(Z{n}).^2),'all');
end

exp_res = repmat(struct('number', number(1), 'max_speed', max_speed(1), 'max_acceleration', max_acceleration(1), 'integrated_KE', integrated_KE(1), ...
    'travel_distance', travel_distance(1)), 1, 18);

for n = 1:18
    exp_res(n).number = number(n);
    exp_res(n).max_speed = max_speed(n);
    exp_res(n).max_acceleration = max_acceleration(n);
    exp_res(n).integrated_KE = integrated_KE(n);
    exp_res(n).travel_distance = travel_distance(n);
end

%% Task 3

fid = fopen('report.txt', 'w+');
fprintf(fid, '%s\n', name);
fprintf(fid, '%s\n', id);
fprintf(fid, 'exp number, max speed (m/s), max acc (m/sˆ2), int KE (J s), travel dist (m)\n');

for n = 1:18
    fprintf(fid, '%10i %15.7e %15.7e %15.7e %15.7e\n', exp_res(n).number, exp_res(n).max_speed, exp_res(n).max_acceleration, exp_res(n).integrated_KE, exp_res(n).travel_distance);
end

fclose(fid);

%% Done

p1a ='See figure 1';
p1b ='See figure 2';
p1c ='See figure 3';
p1d ='See figure 4';
p2a = exp_res(1);
p2b = [exp_res.max_speed];
p2c = [exp_res.max_acceleration];
p2d = [exp_res.integrated_KE];
p2e = [exp_res.travel_distance];
p3 = evalc('type report.txt');