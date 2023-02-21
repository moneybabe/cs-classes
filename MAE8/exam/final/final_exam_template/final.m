clear all; close all; clc; format long
name = 'Neo Lee';
id = 'A17050803';
hw_num = 'final';
form = 'B';

%% Problem 1: 
load('temperature.dat')

p1a = max(temperature(temperature(:,1) == 1920,2:end));
p1b = find(temperature(temperature(:,1) == 1920,2:end) == max(temperature(temperature(:,1) == 1920,2:end)));
p1c = max(max(temperature(:,2:end)));
[row, col] = find(temperature == max(max(temperature(:,2:end))));
p1d = row + 1851;
p1e = sum(temperature(1992-1851:2012-1851,2:end) > 71,'all');
a = temperature(:,10:12);
p1f = mean(a,2);

figure(1)
hold on
plot(1852:2020, p1f, '-k', 'LineWidth', 2)
plot(find(p1f == max(p1f))+1851, max(p1f), 'ro', 'MarkerFaceColor', 'r', 'MarkerSize', 10)
plot(find(p1f == min(p1f))+1851, min(p1f), 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 10)
xlim('tight')
xlabel('year')
ylabel('temperatrue (F)')
title('autumn temp vs year')
legend('autumn avg temp changes', 'highest temp', 'lowest temp', 'Location', 'northwest')
p1g = 'See figure 1';

%% Problem 2: 

a = 0;
for k = 1:40
    for l = 1:50
        for m = 1:60
            a = a + 1/(2^k + 2^ l + 2^m);
        end
    end
end
p2a = a;

a = ((2^0)*factorial(0)^2)/factorial(2*0 + 1);
n = 0;
while abs(pi-2*a) > 10^-6
    n = n + 1;
    a = a + ((2^n)*factorial(n)^2)/factorial(2*n + 1);
end
p2b = 2*a;
p2c = n;

x = -11:0.1:11;
y = -12:0.1:12;
f = zeros(length(x), length(y));

for n = 1:length(x)
    for i = 1:length(y)
        f(n,i) = exp(-1*(cos(x(n)/2) + sin(y(i)/3))^2);
    end
end

[n, m] = size(f);
for a = 2:n-1
    for b = 2:m-1
        compare_element = f(a-1:a+1, b-1:b+1);
        if f(a, b) >= max(compare_element(:))
            i = i + 1;
            peakx(i) = x(a);
            peaky(i) = y(b);
            peak_f(i) = f(a,b);
        end
    end
end
figure(2)
hold on
surf(x, y, f')
plot3(peakx, peaky, peak_f, 'bo', 'MarkerFaceColor', 'b', 'MarkerSize', 5)
xlabel('x axis')
ylabel('y axis')
zlabel('z axis')
legend('surface of function f', 'local max')
title('function f')
view(3)
p2d = 'See figure 2';

%% Problem 3: 
load('survey.mat')

a = length(survey);
year = cell(a, 1);
difficulty = cell(a, 1);
for n = 1:a
    separated = split(survey{n});
    year{n} = separated{2};
    difficulty{n} = separated{end};
end

p3a = length(year{2}) == 8 && length(year{end}) == 8;
p3b = length(year{16}) == 8 && length(year{146}) == 8;

count = 0;
for n = 1:length(difficulty)
    if length(difficulty{n}) == 8
        count = count + 1;
    end
end
p3c = count;

count = 0;
for n = 1:length(difficulty)
    if length(difficulty{n}) == 8 && length(year{n}) == 8
        count = count + 1;
    end
end
p3d = count;

freshman_count = 0;
sophomore_count = 0;
null_count = 0;
junior_count = 0;
senior_count = 0;
for n = 1:a
    if length(year{n}) == 8
        freshman_count = freshman_count + 1;
    elseif length(year{n}) == 9
        sophomore_count = sophomore_count + 1;
    elseif length(year{n}) ~= 6
        null_count = null_count + 1;
    elseif all(year{n} == 'Junior')
        junior_count = junior_count + 1;
    elseif all(year{n} == 'Senior')
        senior_count = senior_count + 1;
    end
end

figure(3)
hold on
bar([freshman_count, sophomore_count, junior_count, senior_count, null_count])
xticklabels({'', 'freshman', 'sophomore', 'junior', 'senior', 'no answer'})
xlabel('year')
ylabel('number of student')
ylim([0 110])
title('number of students of diff years')
p3e = 'See figure 3';

%% Problem 4: 
load('SDweather.mat')

p4a = min(SDweather(1970-1851).temperature);
p4b = find(SDweather(1970-1851).temperature == min(SDweather(1970-1851).temperature));
rain = zeros(169, 12);

for n = 1:169
    rain(n, 1:12) = SDweather(n).rainfall;
end
p4c = max(rain(:));
[a, b] = find(rain == max(rain(:)));
p4d = a + 1851;

annual_avg_rain = zeros(1, 169);
for n = 1:169
    annual_avg_rain(n) = SDweather(n).annual_rainfall_avg;
end

figure(4)
hold on
bar(annual_avg_rain, 'grouped')
plot(find(annual_avg_rain == max(annual_avg_rain)), max(annual_avg_rain), 'md', 'MarkerFaceColor', 'm', 'MarkerSize', 10)
plot(find(annual_avg_rain == min(annual_avg_rain)), min(annual_avg_rain), 'gd', 'MarkerFaceColor', 'g', 'MarkerSize', 10)
xlabel('year')
ylabel('rainfall (inch)')
legend('annual avg rainfall', 'most rainfall', 'least rainfall')
title('annual avg rainfall vs year')
xticklabels({1852:20:2020})
p4e = 'See figure 4';
    

%% Problem 5: 

T = cell(1, 6);
V = cell(1, 6);
X = cell(1, 6);
c = [0 0.6 1.2 1.8 2.4 3.2];

for n = 1:6
    [T{n}, X{n}, V{n}] = spring_mass_damper(n);
end

color = {'-r' '-b' '-k' '-b' '-c' '-m'};
figure(5)
hold on
for n = 1:6
    plot(T{n}, X{n}, color{n}, 'LineWidth', 2)
end

xlabel('time (s)')
ylabel('displacement (m)')
legend('c = 0', 'c = 0.6', 'c = 1.2', 'c = 1.8', 'c = 2.4', 'c = 3.2')
title('displacenment vs time')
p5a = 'See figure 5';

omega = zeros(1, 6);

for n = 1:6
    i = find(X{n} == max(X{n}));
    m = find(X{n} == min(X{n}));
    omega(n) = abs(2*pi/(T{n}(i)-T{n}(m)));
end

p5b = omega;


