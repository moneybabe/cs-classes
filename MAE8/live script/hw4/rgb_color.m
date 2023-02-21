function color = rgb_color(rgb)
% rgb_color tells you the final color for different rgb combination
% input [x y z] with only 0 or 1 value

if length(rgb) == 3 && all(rgb == 1 | rgb == 0)
    if rgb(1) == 1 % [1 x x]
        if rgb(2) == 1 % [1 1 x]
            if rgb(3) == 1 %[1 1 1]
                color = 'white';
            else % [1 1 0]
                    color = 'yellow';
            end 
        else 
            if rgb(3) == 1 % [1 0 1]
                color = 'magenta';
            else % [1 0 0]
                color = 'red';
            end 
        end 
    else % [0 x x]
        if rgb(2) == 1 % [0 1 x]
            if rgb(3) == 1 % [0 1 1]
                color = 'cyan';
            else %[0 1 0] 
                color = 'green';
            end 
        else % [0 0 x]
            if rgb(3) == 1 % [0 0 1]
                color = 'blue';
            else % [0,0,0]
                color = 'Invalid input';
            end 
        end 
    end 
else
    color = 'Invalid input';
end 


end % function rgb_color

