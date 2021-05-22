package ru.vadim;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static String[] inputParameters;

    public static void main(String[] args) throws IOException {
        if(args[0].equals("-")){
            consoleInputMode();
        }
        else{
            fileInputMode(args[0],args[1]);
        }
    }

    private static void consoleInputMode(){
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();

        inputParameters = str.split(" ");

        float result = switchProcedureGetResult();

        System.out.println("Ответ: "+(int)result);
    }

    private static void fileInputMode(String inputFileName,String outputFileName) throws IOException {
        String inputFileData = (Files.lines(Paths.get(inputFileName)).collect(Collectors.toList())).get(0);
        inputParameters = inputFileData.split(" ");

        float result = switchProcedureGetResult();

        String outputString = ""+(int)result;

        Files.write(Paths.get(outputFileName), Collections.singleton(outputString));
    }

    private static float switchProcedureGetResult(){

        float result;

        switch (inputParameters[0]){
            case "add": result=addProcedure();
                break;
            case "mul": result=mulProcedure();
                break;
            case "muladd": result=mulAddProcedure();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + inputParameters[0]);
        }

        return result;
    }


    private static float addProcedure(){
        float sum=0;
        for(int i = 1; i < inputParameters.length; i++){
                sum+= Float.parseFloat(inputParameters[i]);
        }
        return sum;
    }

    private static float mulProcedure(){
        float product = Float.parseFloat(inputParameters[1]);
        for(int i = 2; i < inputParameters.length; i++){
                product*= Float.parseFloat(inputParameters[i]);
        }
        return product;
    }

    private static float mulAddProcedure(){
        float mulSum = Float.parseFloat(inputParameters[1])*Float.parseFloat(inputParameters[2]);
        mulSum+=Float.parseFloat(inputParameters[3]);
        return mulSum;
    }

}
