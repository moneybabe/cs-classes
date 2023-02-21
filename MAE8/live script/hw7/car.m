function [T, X, U] = car(Tf, dt)
% This function solves for the motion of the car
% Call function with format [T, X, U] = car(Tf, dt)
% T is time, X is distance, U is velocity of the car
% Tf is total traveling time, dt is time step


U = zeros(1, fix(Tf/dt)+1);
X = zeros(1, fix(Tf/dt)+1);
T = zeros(1, fix(Tf/dt)+1);
for n = 1:fix(Tf/dt)
    T(n+1) = n*dt;
    U(n+1) = U(n) + 5*(sech(T(n)/20)^2)*(tanh(T(n)/20))*dt;
    X(n+1) = X(n) + U(n)*dt;
end


end

