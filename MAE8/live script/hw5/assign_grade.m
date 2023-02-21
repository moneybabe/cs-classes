function [total_score, letter] = assign_grade(homework, midterm, project, final)
% assign_grade tells you your total score and leter grade
% input your all your homework, midterm, project, and final scores

highest_homework_score = maxk(homework, 7);
highest_homework_sum = sum(highest_homework_score, 'all');
total_score1 = 0.25*highest_homework_sum/7 + 0.25*midterm + 0.2*project + 0.3*final;
total_score2 = 0.25*highest_homework_sum/7 + 0.2*project + 0.55*final;

if total_score1 > total_score2
    total_score = total_score1;
else
    total_score = total_score2;
end

if total_score > 93
    letter = 'A';
elseif total_score >= 90
    letter = 'A-';
elseif total_score >= 87
    letter = 'B+';
elseif total_score >= 83
    letter = 'B';
elseif total_score >= 80
    letter = 'B-';
elseif total_score >= 77
    letter = 'C+';
elseif total_score >= 73
    letter = 'C';
elseif total_score >= 70
    letter = 'C-';
elseif total_score >= 60
    letter = 'D';
elseif total_score < 60
    letter = 'F';
else
    letter = 'invalid input';
end
end

