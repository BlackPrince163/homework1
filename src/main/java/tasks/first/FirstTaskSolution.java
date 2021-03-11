package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        HashSet<Integer> setNode = new HashSet<>();
        ArrayList<String> answer = new ArrayList<>();


        setNode.add(startIndex);
        deque.addLast(startIndex);

        while (!deque.isEmpty()){

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if((!setNode.contains(i+1))&(adjacencyMatrix[startIndex-1][i])){
                    setNode.add(i+1);
                    deque.addLast(i+1);
                }
            }

            answer.add(String.valueOf(deque.pollFirst()));
            if(!deque.isEmpty()) {
                startIndex = deque.peekFirst();
            }
        }
        return answer.toString();
    }

    @Override
    public Boolean validateBrackets(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                if (!stack.isEmpty()){
                    Character pop = stack.pop();
                    if ((pop.equals('(') && s.charAt(i) != ')') ||
                            (pop.equals('[') && s.charAt(i) != ']') ||
                            (pop.equals('{') && s.charAt(i) != '}')) {
                        return false;
                    }
                } else return false;
            }
        }
        if (!stack.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Long polishCalculation(String s) {
        long firstElement;
        long secondElement;
        Stack<Long> stack = new Stack<Long>();
        String[] strings = s.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (isNumber(strings[i])) {
                stack.push(Long.parseLong(strings[i]));
            } else {
                firstElement = stack.pop();
                secondElement = stack.pop();
                switch (strings[i]){
                    case "+":
                        stack.push(firstElement + secondElement);
                        break;
                    case "-":
                        stack.push(secondElement - firstElement);
                        break;
                    case "*":
                        stack.push(firstElement * secondElement);
                        break;
                    case "/":
                        stack.push(secondElement / firstElement);
                        break;
                }
            }
        }
        if (!stack.empty()) {
            return stack.pop();
        } else {
            return 0L;
        }
    }

    public boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
