function [m, k, l, Xo, Yo, Zo, Uo, Vo, Wo] = read_input(input_filename, exp_num)

if exp_num <= 18 && exp_num >= 1
    if int8(exp_num) ~= exp_num
        m = NaN;
        k = NaN;
        l = NaN;
        Xo = NaN;
        Yo = NaN;
        Zo = NaN;
        Uo = NaN;
        Vo = NaN;
        Wo = NaN;
        sprintf('exp_num unavailable')
        return
    end
else
    m = NaN;
    k = NaN;
    l = NaN;
    Xo = NaN;
    Yo = NaN;
    Zo = NaN;
    Uo = NaN;
    Vo = NaN;
    Wo = NaN;
    sprintf('exp_num unavailable')
    return
end

imported_data = importdata(input_filename);
data = imported_data.data;
required_data = data(exp_num, 2:end);
m = required_data(1);
k = required_data(2);
l = required_data(3);
Xo = required_data(4);
Yo = required_data(5);
Zo = required_data(6);
Uo = required_data(7);
Vo = required_data(8);
Wo = required_data(9);

end

