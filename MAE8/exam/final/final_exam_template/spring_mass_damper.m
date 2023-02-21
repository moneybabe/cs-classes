function [T, X, V] = spring_mass_damper(c)
% Call function with format [T, X, V] = spring_mass_damper(c)
% This functions output T(time), X(displacement), V(velocity) with input
% c(frictional damper factor)

m = 0.1;
k = 40;
Xo = 0.1;
Vo = 10;
dt = 0.001;
total_t = 2;
V = zeros(1, fix(total_t/dt) + 1);
X = zeros(1, fix(total_t/dt) + 1);
T = zeros(1, fix(total_t/dt) + 1);
V(1) = Vo;
X(1) = Xo;

for n = 1:fix(total_t/dt)
    V(n+1) = V(n) - (k*X(n)/m + c*V(n)/m)*dt;
    X(n+1) = X(n) + V(n+1)*dt;
    T(n+1) = T(n) + dt;
end

end

