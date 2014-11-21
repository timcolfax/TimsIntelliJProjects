package org.colfax.org.colfax.interview_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by colfax on 11/17/2014.
 */
public class test_area {
    public static void main(String[] args) throws IOException {
        System.out.println("Expression: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expr = br.readLine();

        Stack<Integer> operands  = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();

        char[] chars = expr.toCharArray();
        for(char c : chars){
            if(c == '(' || c == ' ')
                continue;
            else if(c == ')'){
                char op  = operators.pop();
                Integer l = operands.pop();
                Integer r = operands.pop();
                int result = 0;
                switch(op){
                    case '*':
                        result = l * r;
                        break;
                    case '/':
                        result = l / r;
                        break;
                    case '+':
                        result = l + r;
                        break;
                    case '-':
                        result = l - r;
                        break;
                    case '%':
                        result = l % r;
                        break;
                }

                operands.push(result);
            }
            else if(c == '*' || c == '/' || c == '+' || c == '-' || c == '%')
                operators.push(c);
            else
                operands.push(Integer.parseInt(String.valueOf(c)));
        }
        System.out.println("Result: " + operands.pop());
    }
}