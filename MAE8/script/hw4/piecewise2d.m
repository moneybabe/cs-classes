function f = piecewise2d(x,y)
% piecewise2d equates differently according to different (x,y) input
% input (x,y) to use the function
if x >= 0 && y > 0
    f = 10*x + 10*y;
elseif x < 0 && y >= 0
    f = -10*x + 10*y;
elseif x <= 0 && y < 0
    f = -10*x - 10*y;
elseif x > 0 && y<= 0
    f = 10*x - 10*y;
else
    f = 0;
end

