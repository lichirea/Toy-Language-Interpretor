package Repository;
import Model.PrgState;

import Model.exceptions.RepoException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Repo implements IRepo {

    List<PrgState> myPrgStates;
    String logFilePath;

    public Repo(String logfilePath) {
        logFilePath = logfilePath;
        myPrgStates = new ArrayList<PrgState>();
    }


    @Override
    public void logPrgStateExec(PrgState p) throws RepoException, IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter("D:\\Data\\UBB\\An2\\MAP\\Lab3 - SEMAPHORE\\" + logFilePath, true)));
        logFile.println(p.getId() + "\n");
        logFile.println("ExeStack: ");
        logFile.println(p.getExeStack().toString());

        logFile.println("SymTable: ");
        logFile.println(p.getSymTable().toString());

        logFile.println("Out: ");
        logFile.println(p.getOutput().toString());

        logFile.println("FileTable: ");
        logFile.println(p.getFileTable().toString());

        logFile.println("HeapTable: ");
        logFile.println(p.getHeapTable().toString());

        logFile.println("SemaphoreTable: ");
        logFile.println(p.getSemaphoreTable().getSemaphore().toString());

        logFile.println();
        logFile.flush();
        logFile.close();
    }

    @Override
    public List<PrgState> getPrgList() {
        return myPrgStates;
    }


    @Override
    public void setPrgList(List<PrgState> l) {
        myPrgStates = l;
    }

    @Override
    public void addPrg(PrgState newPrg) {
        myPrgStates.add(newPrg);
    }
}
