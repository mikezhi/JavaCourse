package ru.croc.project12;

public class Application {

    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("Отсутствую аргументы");
        } else {
            //TODO classpath problem, execute problem
            //String pathToConfig = args[0];
            //Config.getConfig(pathToConfig);

            new MasterMenu().menu();
        }
    }
}