package View;

import Controller.Controller;
import Model.PrgState;
import Model.adt.*;
import Model.exp.*;
import Model.stmt.*;
import Model.types.*;
import Model.value.*;
import Repository.Repo;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.management.ValueExp;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Main extends Application implements EventHandler<ActionEvent>{

    static java.util.List<Controller> ctrls  = new ArrayList<Controller>();


    public static <IStack> void main(String[] args) throws Exception{

        /*IStmt ex1=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ConstExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        IHeap<Integer, IValue> heapTable = new Heap<Integer, IValue>();
        MyStack<IStmt> exeStack = new MyStack<IStmt>();
        IDict<String, BufferedReader> fileTable = new Dict<String, BufferedReader>();
        IDict<String, IValue> symTable = new Dict<String, IValue>();
        IList<IValue> out = new List<IValue>();
        ex1.typecheck(new Dict<String, IType>());
        PrgState prg1 = new PrgState(exeStack, symTable, out, fileTable,heapTable, ex1);
        Repo repo1 = new Repo("log1.txt");
        Controller ctr1 = new Controller(repo1);
        ctr1.addProgram(prg1);
        ctrls.add(ctr1);


        IStmt ex2=new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+',new ConstExp(new IntValue(2)),new ArithExp('*',
                        new ConstExp(new IntValue(3)), new ConstExp(new IntValue(5))))),  new CompStmt(
                        new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ConstExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));
        IHeap<Integer, IValue> heapTable2 = new Heap<Integer, IValue>();
        MyStack<IStmt> exeStack2 = new MyStack<IStmt>();
        IDict<String, BufferedReader> fileTable2 = new Dict<String, BufferedReader>();
        IDict<String, IValue> symTable2 = new Dict<String, IValue>();
        IList<IValue> out2 = new List<IValue>();
        ex2.typecheck(new Dict<String, IType>());
        PrgState prg2 = new PrgState(exeStack2, symTable2, out2, fileTable2,heapTable2, ex2);
        Repo repo2 = new Repo("log2.txt");
        Controller ctr2 = new Controller(repo2);
        ctr2.addProgram(prg2);
        ctrls.add(ctr2);


        IStmt ex3= new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()),new CompStmt(new AssignStmt("a", new ConstExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(new IntValue(2))),
                        new AssignStmt("v", new ConstExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));
        IHeap<Integer, IValue> heapTable3 = new Heap<Integer, IValue>();
        MyStack<IStmt> exeStack3 = new MyStack<IStmt>();
        IDict<String, BufferedReader> fileTable3 = new Dict<String, BufferedReader>();
        IDict<String, IValue> symTable3 = new Dict<String, IValue>();
        IList<IValue> out3 = new List<IValue>();
        ex3.typecheck(new Dict<String, IType>());
        PrgState prg3 = new PrgState(exeStack3, symTable3, out3, fileTable3,heapTable3, ex3);
        Repo repo3 = new Repo("log3.txt");
        Controller ctr3 = new Controller(repo3);
        ctr3.addProgram(prg3);
        ctrls.add(ctr3);

        IStmt  ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ConstExp(new StringValue("test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));
        MyStack<IStmt> exeStack4 = new MyStack<IStmt>();
        IHeap<Integer, IValue> heapTable4 = new Heap<Integer, IValue>();
        IDict<String, BufferedReader> fileTable4 = new Dict<String, BufferedReader>();
        IDict<String, IValue> symTable4 = new Dict<String, IValue>();
        IList<IValue> out4 = new List<IValue>();
        ex4.typecheck(new Dict<String, IType>());
        PrgState prg4 = new PrgState(exeStack4, symTable4, out4, fileTable4,heapTable4, ex4);
        Repo repo4 = new Repo("log4.txt");
        Controller ctr4 = new Controller(repo4);
        ctr4.addProgram(prg4);
        ctrls.add(ctr4);

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new AllocateHeapStmt("v", new ConstExp(new IntValue(20))),
                        new CompStmt( new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt( new WriteHeapStmt("v", new ConstExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ConstExp(new IntValue(5))))
                                                ))));

        MyStack<IStmt> exeStack5 = new MyStack<IStmt>();
        IHeap<Integer, IValue> heapTable5 = new Heap<Integer, IValue>();
        IDict<String, BufferedReader> fileTable5 = new Dict<String, BufferedReader>();
        IDict<String, IValue> symTable5 = new Dict<String, IValue>();
        IList<IValue> out5 = new List<IValue>();
        ex5.typecheck(new Dict<String, IType>());
        PrgState prg5 = new PrgState(exeStack5, symTable5, out5, fileTable5,heapTable5, ex5);
        Repo repo5 = new Repo("log5.txt");
        Controller ctr5 = new Controller(repo5);
        ctr5.addProgram(prg5);
        ctrls.add(ctr5);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new AllocateHeapStmt("v",new ConstExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                                new CompStmt(new AllocateHeapStmt("a", new VarExp("v")),
                                        new CompStmt(new AllocateHeapStmt("v", new ConstExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))))
                                                        )))));

        MyStack<IStmt> exeStack6 = new MyStack<IStmt>();
        IHeap<Integer, IValue> heapTable6 = new Heap<Integer, IValue>();
        IDict<String, BufferedReader> fileTable6 = new Dict<String, BufferedReader>();
        IDict<String, IValue> symTable6 = new Dict<String, IValue>();
        IList<IValue> out6 = new List<IValue>();
        ex6.typecheck(new Dict<String, IType>());
        PrgState prg6 = new PrgState(exeStack6, symTable6, out6, fileTable6,heapTable6, ex6);
        Repo repo6 = new Repo("log6.txt");
        Controller ctr6 = new Controller(repo6);
        ctr6.addProgram(prg6);
        ctrls.add(ctr6);

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ConstExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ConstExp(new IntValue(0))),
                                 new CompStmt(new PrintStmt(new VarExp("v")),
                                         new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(new IntValue(1)))))),
                new PrintStmt(new VarExp("v")))));

        MyStack<IStmt> exeStack7 = new MyStack<IStmt>();
        IHeap<Integer, IValue> heapTable7 = new Heap<Integer, IValue>();
        IDict<String, BufferedReader> fileTable7 = new Dict<String, BufferedReader>();
        IDict<String, IValue> symTable7 = new Dict<String, IValue>();
        IList<IValue> out7 = new List<IValue>();
        ex7.typecheck(new Dict<String, IType>());
        PrgState prg7 = new PrgState(exeStack7, symTable7, out7, fileTable7,heapTable7, ex7);
        Repo repo7 = new Repo("log7.txt");
        Controller ctr7 = new Controller(repo7);
        ctr7.addProgram(prg7);
        ctrls.add(ctr7);

        //int v; Ref int a; v=10;new(a,22);  fork(wH(a,30);v=32;print(v);print(rH(a)));
        //print(v);print(rH(a))

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ConstExp(new IntValue(10))),
                            new CompStmt(new AllocateHeapStmt("a", new ConstExp(new IntValue(22))),
                                    new CompStmt(new ForkStmt(new CompStmt( new WriteHeapStmt("a", new ConstExp(new IntValue(30))),
                                            new CompStmt(new AssignStmt("v", new ConstExp(new IntValue(32))),
                                                    new CompStmt(new PrintStmt(new VarExp("v")),
                                                            new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
        MyStack<IStmt> exeStack8 = new MyStack<IStmt>();
        IHeap<Integer, IValue> heapTable8 = new Heap<Integer, IValue>();
        IDict<String, BufferedReader> fileTable8 = new Dict<String, BufferedReader>();
        IDict<String, IValue> symTable8 = new Dict<String, IValue>();
        IList<IValue> out8 = new List<IValue>();
        ex8.typecheck(new Dict<String, IType>());
        PrgState prg8 = new PrgState(exeStack8, symTable8, out8, fileTable8,heapTable8, ex8);
        Repo repo8 = new Repo("log8.txt");
        Controller ctr8 = new Controller(repo8);
        ctr8.addProgram(prg8);
        ctrls.add(ctr8);*/

//        TextMenu menu = new TextMenu();
//        menu.addCommand(new ExitCommand("0", "exit"));
//        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
//        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
//        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
//        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
//        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
//        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
//        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
//        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
//        menu.show();

            launch(args);
    }

    Stage primary;
    Scene listscene;
    Scene executionscene;
    Button go;
    ListView<String> nicelist;

    TextField numberPrgStates;
    TableView<String> heap;
    ListView<PrgState> listPrgStates;


    public void start(Stage primaryStage) throws Exception{
        primary = primaryStage;
        primary.setTitle("Available programs to execute");

        nicelist = new ListView<String>();
        for (Controller c : ctrls
             ) {nicelist.getItems().add(c.getExeStackString());
        }

        go = new Button();
        go.setPrefWidth(100);
        go.setText("Go");
        go.setOnAction(this);


        VBox layout = new VBox();
        layout.getChildren().add(nicelist);
        layout.getChildren().add(go);


        listscene = new Scene(layout, 960, 275);
        primary.setScene(listscene);
        primary.show();
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == go){
            ObservableList<String> selected = nicelist.getSelectionModel().getSelectedItems();
            for (Controller c : ctrls
                 ) {
                if(Objects.equals(selected.get(0), c.getExeStackString())){

                    numberPrgStates = new TextField();
                    numberPrgStates.setText("NONE");
                    numberPrgStates.setEditable(false);


                    heap = new TableView<String>();
                    String s = c.getHeapString();
                    System.out.println(s);

                    TilePane layoutt = new TilePane();
                    layoutt.setHgap(50);
                    layoutt.setPrefColumns(2);

                    layoutt.getChildren().add(numberPrgStates);
                    layoutt.getChildren().add(heap);

                    executionscene = new Scene(layoutt, 1000, 1000);
                    primary.close();
                    primary.setScene(executionscene);
                    primary.setTitle("Execution Analysis");
                    primary.show();
                }
            }
        }


    }
}
