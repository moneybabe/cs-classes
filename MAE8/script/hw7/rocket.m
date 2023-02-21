function [T, Z, W] = rocket(Tf, dt)
% This functions solves for the motion of the rocket
% Call function with format [T, Z, W] = rocket(Tf, dt)
% T is time, Z is altitude, W is velocity
% Tf is total traveling time, dt is time step

T = zeros(1, fix(Tf/dt)+1);
W = zeros(1, fix(Tf/dt)+1);
Z = zeros(1, fix(Tf/dt)+1);
    for n = 1:fix(Tf/dt)
        T(n+1) = n*dt;
        W(n+1) = W(n) + (-gravity(Z(n)) + thrust(T(n))/10)*dt;
        Z(n+1) = Z(n) + W(n+1)*dt;
    end
end



function [Th] = thrust(t)
% This function calculates the thrust in different time interval
% Thrust = 670N for 0 <= t < 2
% Thrust = 1360N for 2 <= t < 4
% Thrust = 0N for 4 <= t
% Call format: [Th] = thrust(t)
% Th is thrust, t is time
    if t >= 0 && t < 2
    Th = 670;
    elseif t >=2 && t < 4
    Th  = 1360;
    elseif t >= 4 
    Th = 0;
    end
end




function [g] = gravity(z)
% This function calculates the gravity force in different altitude
% g = 9.81*(1-(z/10000)^3) for 0 <= z < 10000
% g = 0 for 10000 <= z
% Call format: [g] = gravity(z)
% g is gravity, z is altitude
    if z >= 0 && z < 10000
        g = 9.81*(1-(z/10000)^3);
    elseif z >= 10000
        g = 0;
    end
end