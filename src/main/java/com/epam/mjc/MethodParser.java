package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */

    public MethodSignature parseFunction(String signatureString) {

        int open = signatureString.indexOf(40);
        int close = signatureString.indexOf(41) + 1;

        MethodSignature methodSignature;
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        String[] declaration = signatureString.substring(0, open).split(" ");
        String argument = open + 1 == close - 1 ? ", " : signatureString.substring(open + 1, close - 1);
        String[] args = argument.split(", ", 0);

        if (args.length > 0) {
            for (String s : args) {
                String[] val = s.split(" ");
                arguments.add(new MethodSignature.Argument(val[0], val[1]));
            }
        }

        methodSignature = new MethodSignature(declaration[declaration.length - 1], arguments);
        if (declaration.length == 3) {
            methodSignature.setAccessModifier(declaration[0]);
            methodSignature.setReturnType(declaration[1]);
        } else {
            methodSignature.setReturnType(declaration[0]);
        }

        return methodSignature;
    }
}
