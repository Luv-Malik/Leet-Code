import java.util.*;

class Solution {
    private int index = 0;

    public List<String> braceExpansionII(String expression) {
        index = 0;
        Set<String> resultSet = parseExpr(expression);
        
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    // Parses Union (,) level expressions
    private Set<String> parseExpr(String s) {
        Set<String> res = new HashSet<>();
        
        while (index < s.length() && s.charAt(index) != '}') {
            Set<String> term = parseTerm(s);
            res.addAll(term);
            
            if (index < s.length() && s.charAt(index) == ',') {
                index++; // skip ','
            }
        }
        
        return res;
    }

    // Parses Concatenations (e.g. {a,b}{c,{d,e}})
    private Set<String> parseTerm(String s) {
        Set<String> res = new HashSet<>();
        res.add("");

        while (index < s.length() && s.charAt(index) != ',' && s.charAt(index) != '}') {
            Set<String> factor;
            if (s.charAt(index) == '{') {
                index++; // skip '{'
                factor = parseExpr(s);
                index++; // skip '}'
            } else {
                StringBuilder sb = new StringBuilder();
                while (index < s.length() && Character.isLetter(s.charAt(index))) {
                    sb.append(s.charAt(index));
                    index++;
                }
                factor = new HashSet<>();
                factor.add(sb.toString());
            }
            res = multiply(res, factor);
        }

        return res;
    }

    // Cartesian product concatenation helper
    private Set<String> multiply(Set<String> set1, Set<String> set2) {
        Set<String> res = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                res.add(s1 + s2);
            }
        }
        return res;
    }
}