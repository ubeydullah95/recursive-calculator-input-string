class Scratch {

    static int evaluateE(String s) {
        int bracketsStart = s.indexOf("(");
        if (bracketsStart > 0) {
            int bracketsEnd = s.indexOf(")");
            if (bracketsEnd > 0) {
                String newData = s.substring(bracketsStart + 1, bracketsEnd);
                String ss = String.valueOf(evaluateE(newData));
                s = s.substring(0, bracketsStart) + ss + s.substring(bracketsEnd + 1, s.length());
            }
        }


        //Base case
        if (!s.contains("+") && !s.contains("-") && !s.contains("*") && !s.contains("/")) {
            return Integer.parseInt(s);
        }

        int i;

        // search for '+' and '-' first
        for (i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                break;
            }
        }
        if (i < 0) {
            // if '+' and '-' were not found, search for '*' and '/'
            for (i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                    break;
                }
            }
        }

        String r1 = s.substring(0, i);
        String r2 = s.substring(i + 1, s.length());

        int result = 0;

        switch (s.charAt(i)) {
            case '+':
                result = evaluateE(r1) + evaluateE(r2);
                break;
            case '-':
                result = evaluateE(r1) - evaluateE(r2);
                break;
            case '*':
                result = evaluateE(r1) * evaluateE(r2);
                break;
            case '/':
                int right = evaluateE(r2);
                if (right == 0) //if denominator is zero
                {
                    System.out.println("Invalid divisor");
                    System.exit(1);
                } else {
                    result = evaluateE(r1) / right;
                }
                break;
        }
        return result;

    }

    public static void main(String[] args) {
        String data = "23+57-2*(45/5+2/2-3)";

        System.out.println(evaluateE(data));
    }
}