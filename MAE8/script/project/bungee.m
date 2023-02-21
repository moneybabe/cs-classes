function [T, X, Y, Z, U, V, W, safety] = bungee(m, k, l, Xo, Yo, Zo, Uo, Vo, Wo)

load ('terrain.mat');
Cd = 0.1;
A = pi;
rho = 1.2;
g = 9.81;
dt = 0.02;
total_t = 120;

T = zeros(1, total_t/dt + 1);
X = zeros(1, total_t/dt + 1);
X(1) = Xo;
Y = zeros(1, total_t/dt + 1);
Y(1) = Yo;
Z = zeros(1, total_t/dt + 1);
Z(1) = Zo;
U = zeros(1, total_t/dt + 1);
U(1) = Uo;
V = zeros(1, total_t/dt + 1);
V(1) = Vo;
W = zeros(1, total_t/dt + 1);
W(1) = Wo;

for n = 1:total_t/dt
    r = sqrt(X(n)^2 + Y(n)^2 + Z(n)^2);
    Vmag = sqrt(U(n)^2 + V(n)^2 + W(n)^2);

    U(n+1) = U(n) - ( (k*(r-l)*X(n))/(m*r) + (Cd*rho*A*Vmag*U(n))/(2*m) )*dt;
    V(n+1) = V(n) - ( (k*(r-l)*Y(n))/(m*r) + (Cd*rho*A*Vmag*V(n))/(2*m) )*dt;
    W(n+1) = W(n) - ( (k*(r-l)*Z(n))/(m*r) + (Cd*rho*A*Vmag*W(n))/(2*m) + g )*dt;
    X(n+1) = X(n) + U(n+1)*dt;
    Y(n+1) = Y(n) + V(n+1)*dt;
    Z(n+1) = Z(n) + W(n+1)*dt;
    T(n+1) = T(n) + dt;
    terrain_Z = interp2(x_terrain, y_terrain, h_terrain, X(n+1), Y(n+1));

    if terrain_Z < Z(n+1)
        safety = true;
    else
        safety = false;
        T(n+2:end) = [];
        X(n+2:end) = [];
        Y(n+2:end) = [];
        Z(n+2:end) = [];
        U(n+2:end) = [];
        V(n+2:end) = [];
        W(n+2:end) = [];
        break
    end
end

end

