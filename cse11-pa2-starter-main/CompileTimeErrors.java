class CompileTimeErrors{
    String checkNumber(double myNumber){
        if (myNumber > 0){
            return "The number " + myNumber + " is positive.";
        }
        else if (myNumber < 0){ 
            return "The number " + myNumber + " is negative."; 
        }
        return "The number is zero.";
    }
    String answer1 = checkNumber(1);
    String answer2 = checkNumber(0); 
    String answer3 = checkNumber(-10.5); 
}
