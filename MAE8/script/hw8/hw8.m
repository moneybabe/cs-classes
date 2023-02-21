clear all;   % This clears all workspaces
close all;   % This closes all figures 
clc;         % This clears the command window
format long; 

name = 'Neo Lee';
id = 'A17050803';
hw_num = 8;

%% Problem 1

load note.mat
p1a = size(note);

a = cell(p1a(1), 1);
for n = 1:p1a(1)
    a{n} = note{n};
end
p1b = a;

a = cell(1, p1a(2));
for n = 1:p1a(2)
    a{n} = note{end, n};
end
p1c = a;

p1d = note{3,2};

a = p1b;
for n = 1:length(a)
    [first, last] = strtok(a{n});
    first(1) = upper(first(1));
    last(2) = upper(last(2));
    a{n} = [first last];
end
p1e = a;

%% Problem 2

student(1).name = 'Noah Williams';
student(1).PID = 'A01';
student(1).homework = [70 91 82 93 84 85 96 78];
student(1).project = 96;
student(1).midterm = 93;
student(1).final = 63;

student(2).name = 'Benjamin Frank';
student(2).PID = 'A02';
student(2).homework = [90 81 92 83 67 85 86 92];
student(2).project = 82;
student(2).midterm = 83;
student(2).final = 91;

student(3).name = 'Oliver Harper';
student(3).PID = 'A03';
student(3).homework = [80 71 92 73 64 75 96 77];
student(3).project = 77;
student(3).midterm = 91;
student(3).final = 76;

p2a = student(1);
p2b = student(2);
p2c = student(3);

hw_average = zeros(1, 3);
for n = 1:3
    hw_average(n) = mean(maxk(student(n).homework, 7));
    student(n).hw_average = hw_average(n);
end
p2d = [student.hw_average];

%% Problem 3

a = importdata('class_survey.dat');

day = cell(1, length(a));
experience = cell(1, length(a));
time = cell(1, length(a));
year = cell(1, length(a));
grade = cell(1, length(a));
for n = 1:length(a)
    b = split(a{n});
    day{n} = b{4};
    experience{n} = b{3};
    time{n} = b{6};
    year{n} = b{2};
    grade{n} = b{5};
end

null_count = 0;
tue_count = 0;
wed_count = 0;
thu_count = 0;
fri_count = 0;
for n = 1:length(day)
    if length(day{n}) ~= 3
        null_count = null_count + 1;
    elseif all(day{n} == 'Tue')
        tue_count = tue_count + 1;
    elseif all(day{n} == 'Wed')
        wed_count = wed_count + 1;
    elseif all(day{n} == 'Thu')
        thu_count = thu_count + 1;        
    elseif all(day{n} == 'Fri')
        fri_count = fri_count + 1;        
    end
end

figure(1)
hold on
X = categorical({'Tue','Wed','Thu','Fri'});
X = reordercats(X,{'Tue','Wed','Thu','Fri'});
Y = [tue_count wed_count thu_count fri_count];
bar(X,Y)
xlabel('lab session')
ylabel('number of students')
title('number of students vs most frequently visited lab session')
p3a = 'See figure 1';
       
yes_count = 0;
no_count = 0;
null_count = 0;
for n = 1:length(experience)
    if length(experience{n}) == 4
        null_count = null_count + 1;
    elseif length(experience{n}) == 3
        yes_count = yes_count + 1;
    elseif length(experience{n}) == 2
        no_count = no_count + 1;      
    end
end
p3b = yes_count;

count = 0;
null = 0;
for n = 1:length(time)
    if length(time{n}) ~= 1 && length(time{n}) ~= 2
        null = null + 1;
        continue
    elseif time{n} > '6'
        count = count + 1;
    end
end
p3c = count;

A_count = 0;
for n = 1:length(year)
    if length(year{n}) ~= 9
        continue
    else
        if grade{n} == 'A'
            A_count = A_count + 1;
        end
    end
end
p3d = A_count;