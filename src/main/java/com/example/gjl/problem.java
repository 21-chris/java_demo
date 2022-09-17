package com.example.gjl;

import java.util.Stack;

public class problem {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

//        String string = "(div 2 (sub 5 (add 3 2)))";//这里改成标准输入
        String string = "(sub 6 (add 3 2))";//这里改成标准输入

        StringBuffer inputString = new StringBuffer(string);

        Stack<Integer> numberBuffer = new Stack<Integer>();
        Stack<String> operationBuffer = new Stack<String>();
        int mark = 0;
        int ParameterOne = 0;
        int ParameterTwe = 0;

        for(int index = 0;index<inputString.length();index++) {
            if('(' == inputString.charAt(index)) {
                operationBuffer.push(inputString.substring(index+1, index+4));
                index+=4;
                mark = index+1;
            }else if (')' == inputString.charAt(index)) {
                if(mark<index) {
                    numberBuffer.push(Integer.parseInt(inputString.substring(mark, index)));
                    index++;
                    mark = index+1;
                }
                ParameterTwe = numberBuffer.pop();
                ParameterOne = numberBuffer.pop();

                switch(operationBuffer.pop()) {
                    case "add":
                        numberBuffer.push(ParameterOne + ParameterTwe);
                        break;
                    case "sub":
                        numberBuffer.push(ParameterOne - ParameterTwe);
                        break;
                    case "mul":
                        numberBuffer.push(ParameterOne * ParameterTwe);
                        break;
                    case "div":
                        if(ParameterTwe == 0) {
                            System.out.println("error");//这里是error输出
                            return;
                        }
                        else{
                            numberBuffer.push(ParameterOne / ParameterTwe);
                        }
                        break;
                }

            }else {
                if(' ' == inputString.charAt(index)) {
                    if(mark<index) {
//                        将使用到的数据加进去
                        numberBuffer.push(Integer.parseInt(inputString.substring(mark, index)));
                        //index++;
                        mark = index+1;
                    }
                }
            }
        }

        while(!operationBuffer.isEmpty()) {
            ParameterTwe = numberBuffer.pop();
            ParameterOne = numberBuffer.pop();

            switch(operationBuffer.pop()) {
                case "add":
                    numberBuffer.push(ParameterOne + ParameterTwe);
                    break;
                case "sub":
                    numberBuffer.push(ParameterOne - ParameterTwe);
                    break;
                case "mul":
                    numberBuffer.push(ParameterOne * ParameterTwe);
                    break;
                case "div":
                    if(ParameterTwe == 0) {
                        System.out.println("error");
                    }
                    else{
                        numberBuffer.push(ParameterOne / ParameterTwe);
                    }
                    break;
            }

        }

        System.out.println(numberBuffer.pop().toString());//这里改成标准输出
        return;
    }

}


