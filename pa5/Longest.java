class Longest {
    public static void main(String[] args) {
        String longest = "";
        for (String i: args) {
            if (i.length() > longest.length()) longest = i;
        }
        System.out.println(longest);
    }
}