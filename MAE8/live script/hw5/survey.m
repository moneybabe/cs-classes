function survey
% SURVEY uses function menu to take a class survey.
% Call format: survey

%%
Q1 = menu('What is your class level?',...
          'Freshman','Sophomore','Junior','Senior');
switch Q1
    case 1
        A1 = 'Freshman';
    case 2
        A1 = 'Sophomore';
    case 3
        A1 = 'Junior';
    case 4
        A1 = 'Senior';
    otherwise
        A1 = 'Null';
end

%%
Q2 = menu('Do you have any MATLAB / coding experience prior to the course?', ...
          'Yes','No');
switch Q2
    case 1
        A2 = 'Yes';
    case 2
        A2 = 'No';
    otherwise
        A2 = 'Null';        
end
 
%%
Q3 = menu('Which of the following lab sessions do you attend most frequently?', ...
          'Tue', 'Wed', 'Thu', 'Fri');
switch Q3
    case 1
        A3 = 'Tue';
    case 2
        A3 = 'Wed';
    case 3
        A3 = 'Thu';  
    case 4
        A3 = 'Fri';     
    otherwise
        A3 = 'Null';        
end

%%
Q4 = menu('What grade do you expect for the course?',...
          'A','B','C','D','F');
switch Q4
    case 1
        A4 = 'A';
    case 2
        A4 = 'B';
    case 3
        A4 = 'C';
    case 4
        A4 = 'D';  
    case 5
        A4 = 'F';
    otherwise
        A4 = 'Null';        
end

%%
Q5 = menu({'On average how many hours per week', ...
           'do you spend studying outside of class?'}, ...
          '0 - 3','4 - 6','7 - 9','>= 10');
switch Q5
    case 1
        A5 = '0 - 3';
    case 2
        A5 = '4 - 6';
    case 3
        A5 = '7 - 9';
    case 4
        A5 = '>= 10';  
    otherwise
        A5 = 'Null';        
end

%%
Q6 = menu('How difficult was the midterm?', ...
          'Easy','Moderate','Difficult');
switch Q6
    case 1
        A6 = 'Easy';
    case 2
        A6 = 'Moderate';
    case 3
        A6 = 'Difficult';
    otherwise
        A6 = 'Null';        
end

%% Write results to file
fid = fopen('survey.dat','w');
fprintf(fid,'%10s',A1,A2,A3,A4,A5,A6);
fclose(fid);

end % function survey
