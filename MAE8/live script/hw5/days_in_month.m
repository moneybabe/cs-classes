function days = days_in_month(month,leap)
% days_in_month tells you how many days are there in the month
% input the month, e.g. 'jan', and whether it's a leap year

switch leap 
    case 1 
        switch month
            case 'jan'
                days = 31;
            case 'feb'
                days = 29;
            case 'mar'
                days = 31;
            case 'apr'
                days = 30;
            case 'may'
                days = 31;
            case 'jun'
                days = 30;
            case 'jul'
                days = 31;
            case 'aug'
                days = 31;
            case 'sep'
                days = 30;
            case 'oct'
                days = 31;
            case 'nov'
                days = 30;
            case 'dec'
                days = 31;
            otherwise
                days = 'Invalid inputs';
        end
    case 0
        switch month
            case 'jan'
                days = 31;
            case 'feb'
                days = 28;
            case 'mar'
                days = 31;
            case 'apr'
                days = 30;
            case 'may'
                days = 31;
            case 'jun'
                days = 30;
            case 'jul'
                days = 31;
            case 'aug'
                days = 31;
            case 'sep'
                days = 30;
            case 'oct'
                days = 31;
            case 'nov'
                days = 30;
            case 'dec'
                days = 31;
            otherwise
                days = 'Invalid inputs';
        end

end

