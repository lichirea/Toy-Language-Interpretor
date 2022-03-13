package View;

import Controller.Controller;
import Model.PrgState;
import Model.adt.*;
import Model.exp.*;
import Model.stmt.*;
import Model.types.*;
import Model.value.BoolValue;
import Model.value.IValue;
import Model.value.IntValue;
import Model.value.StringValue;
import Repository.Repo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Objects;

public class Window1Code {

    ArrayList<Controller> ctrls;

    @FXML
    public ListView<String> ListPrgs;


    @FXML
    public void mouseClickEvent(MouseEvent mouseEvent) {
        try {
            ObservableList<String> selected = ListPrgs.getSelectionModel().getSelectedItems();
            for (Controller c : ctrls
            ) {
                if (Objects.equals(selected.get(0), c.getExeStackString())) {
                    Node node = (Node) mouseEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    Parent root = FXMLLoader.load(getClass().getResource("Window2.fxml"));
                    Stage stage1 = new Stage();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    stage1.setUserData(c);
                    stage1.setTitle("Program Analysis");
                    stage1.setScene(new Scene(root));
                    stage1.show();

                }
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Something went wrong, check the console", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    private void initialize(){
        try{
            ctrls = new ArrayList<Controller>();

            IStmt ex1=new CompStmt(new VarDeclStmt("v",new IntType()),
                    new CompStmt(new AssignStmt("v",new ConstExp(new IntValue(2))),
                            new PrintStmt(new VarExp("v"))));
            IHeap<Integer, IValue> heapTable = new Heap<Integer, IValue>();
            MyStack<IStmt> exeStack = new MyStack<IStmt>();
            IDict<String, BufferedReader> fileTable = new Dict<String, BufferedReader>();
            IDict<String, IValue> symTable = new Dict<String, IValue>();
            IList<IValue> out = new List<IValue>();
            ex1.typecheck(new Dict<String, IType>());
            ISemaphore st1 = new Semaphore();
            PrgState prg1 = new PrgState(exeStack, symTable, out, fileTable,heapTable, ex1,st1);
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
            ISemaphore st2 = new Semaphore();
            PrgState prg2 = new PrgState(exeStack2, symTable2, out2, fileTable2,heapTable2, ex2, st2);
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
            ISemaphore st3 = new Semaphore();
            PrgState prg3 = new PrgState(exeStack3, symTable3, out3, fileTable3,heapTable3, ex3, st3);
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
            ISemaphore st4 = new Semaphore();
            PrgState prg4 = new PrgState(exeStack4, symTable4, out4, fileTable4,heapTable4, ex4, st4);
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
            ISemaphore st5 = new Semaphore();
            PrgState prg5 = new PrgState(exeStack5, symTable5, out5, fileTable5,heapTable5, ex5, st5);
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
            ISemaphore st6 = new Semaphore();
            PrgState prg6 = new PrgState(exeStack6, symTable6, out6, fileTable6,heapTable6, ex6, st6);
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
            ISemaphore st7 = new Semaphore();
            PrgState prg7 = new PrgState(exeStack7, symTable7, out7, fileTable7,heapTable7, ex7, st7);
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
            ISemaphore st8 = new Semaphore();
            PrgState prg8 = new PrgState(exeStack8, symTable8, out8, fileTable8,heapTable8, ex8, st8);
            Repo repo8 = new Repo("log8.txt");
            Controller ctr8 = new Controller(repo8);
            ctr8.addProgram(prg8);
            ctrls.add(ctr8);



            //Ref int a; Ref int b; int v;
            //new(a,0); new(b,0);
            //wh(a,1); wh(b,2);
            //v=(rh(a)<rh(b))?100:200;
            //print(v);
            //v= ((rh(b)-2)>rh(a))?100:200;
            //print(v);
            IStmt ex9 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("b", new RefType(new IntType())),
                            new CompStmt(new VarDeclStmt("v", new IntType()),
                                    new CompStmt(new AllocateHeapStmt("a", new ConstExp(new IntValue(0))),
                                            new CompStmt(new AllocateHeapStmt("b", new ConstExp(new IntValue(0))),
                                                    new CompStmt(new WriteHeapStmt("a", new ConstExp(new IntValue(1))),
                                                            new CompStmt(new WriteHeapStmt("b", new ConstExp(new IntValue(2))),
            new CompStmt(new CondAssignStmt("v", new RelationalExp("<", new ReadHeapExp(new VarExp("a")), new ReadHeapExp(new VarExp("b"))), new ConstExp(new IntValue(100)),new ConstExp(new IntValue(200))),
                    new CompStmt(new PrintStmt(new VarExp("v")),
            new CompStmt(new CondAssignStmt("v", new RelationalExp(">", new ArithExp('-',new ReadHeapExp(new VarExp("b")), new ConstExp(new IntValue(2))), new ReadHeapExp(new VarExp("b"))),new ConstExp(new IntValue(100)),new ConstExp(new IntValue(200))),
                                    new PrintStmt(new VarExp("v"))))))))))));
            MyStack<IStmt> exeStack9 = new MyStack<IStmt>();
            IHeap<Integer, IValue> heapTable9 = new Heap<Integer, IValue>();
            IDict<String, BufferedReader> fileTable9 = new Dict<String, BufferedReader>();
            IDict<String, IValue> symTable9 = new Dict<String, IValue>();
            IList<IValue> out9 = new List<IValue>();
            ex9.typecheck(new Dict<String, IType>());
            ISemaphore st9 = new Semaphore();
            PrgState prg9 = new PrgState(exeStack9, symTable9, out9, fileTable9,heapTable9, ex9, st9);
            Repo repo9 = new Repo("log9.txt");
            Controller ctr9 = new Controller(repo9);
            ctr9.addProgram(prg9);
            ctrls.add(ctr9);


            //int v; int x; int y; v=0;
            //(repeat (fork(print(v);v=v-1);v=v+1) until v==3);
            //x=1;nop;y=3;nop;
            //print(v*10)
            IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new VarDeclStmt("x", new IntType()),
                            new CompStmt(new VarDeclStmt("y", new IntType()),
                                    new CompStmt(new AssignStmt("v", new ConstExp(new IntValue(0))),
            new CompStmt(new RepeatUntilStmt(new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(new IntValue(1)))))),
                    new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ConstExp(new IntValue(1))))), new RelationalExp("==", new VarExp("v"), new ConstExp(new IntValue(3)))),
                                                    new CompStmt(new AssignStmt("x", new ConstExp(new IntValue(1))),
                                                            new CompStmt(new NopStmt(),
                                                                    new CompStmt(new AssignStmt("y", new ConstExp(new IntValue(3))),
                                                                            new CompStmt(new NopStmt(),
                                                                                    new PrintStmt(new ArithExp('*', new VarExp("v"), new ConstExp(new IntValue(10)))))))))))));
            MyStack<IStmt> exeStack10 = new MyStack<IStmt>();
            IHeap<Integer, IValue> heapTable10 = new Heap<Integer, IValue>();
            IDict<String, BufferedReader> fileTable10 = new Dict<String, BufferedReader>();
            IDict<String, IValue> symTable10 = new Dict<String, IValue>();
            IList<IValue> out10 = new List<IValue>();
            ex10.typecheck(new Dict<String, IType>());
            ISemaphore st10 = new Semaphore();
            PrgState prg10 = new PrgState(exeStack10, symTable10, out10, fileTable10,heapTable10, ex10, st10);
            Repo repo10 = new Repo("log10.txt");
            Controller ctr10 = new Controller(repo10);
            ctr10.addProgram(prg10);
            ctrls.add(ctr10);



            //THIS IS WRITTEN WRONG!!!!!!!!
            //Ref int v1; int cnt;
            //new(v1,2);newSemaphore(cnt,rH(v1),1);
            //fork(acquire(cnt);wh(v1,rh(v1)*10));print(rh(v1));release(cnt)); <- THIS SHOULD ALL BE IN THE FORK
            //fork(acquire(cnt);wh(v1,rh(v1)*10));wh(v1,rh(v1)*2));print(rh(v1));release(cnt <- SAME HERE! :((((
            //));
            //acquire(cnt);
            //print(rh(v1)-1);
            //release(cnt)
            //The final Out should be {20,200,199} or {20,19,200} OR something with 399/400 ????
            IStmt ex11 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("cnt", new IntType()),
                            new CompStmt(new AllocateHeapStmt("v1", new ConstExp(new IntValue(3))),
                    new CompStmt(new CreateSemaphoreStmt("cnt", new ReadHeapExp(new VarExp("v1"))),
                    new CompStmt(new ForkStmt(new CompStmt(new AcquireStmt("cnt"),new WriteHeapStmt("v1", new ArithExp('*', new ReadHeapExp(new VarExp("v1")), new ConstExp(new IntValue(10)))))),
                    new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                    new CompStmt(new ReleaseStmt("cnt"),
                    new CompStmt(new ForkStmt(new CompStmt(new AcquireStmt("cnt"),new WriteHeapStmt("v1", new ArithExp('*', new ReadHeapExp(new VarExp("v1")), new ConstExp(new IntValue(10)))))),
                    new CompStmt(new WriteHeapStmt("v1", new ArithExp('*', new ReadHeapExp(new VarExp("v1")), new ConstExp(new IntValue(2)))),
                    new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                    new CompStmt(new ReleaseStmt("cnt"),
                    new CompStmt(new AcquireStmt("cnt"),
                    new CompStmt(new PrintStmt(new ArithExp('-',new ReadHeapExp(new VarExp("v1")),new ConstExp(new IntValue(1)))),
                    new ReleaseStmt("cnt"))))))))))))));

            MyStack<IStmt> exeStack11 = new MyStack<IStmt>();
            IHeap<Integer, IValue> heapTable11 = new Heap<Integer, IValue>();
            IDict<String, BufferedReader> fileTable11 = new Dict<String, BufferedReader>();
            IDict<String, IValue> symTable11 = new Dict<String, IValue>();
            IList<IValue> out11 = new List<IValue>();
            ISemaphore st11 = new Semaphore();
            ex11.typecheck(new Dict<String, IType>());
            PrgState prg11 = new PrgState(exeStack11, symTable11, out11, fileTable11,heapTable11, ex11, st11);
            Repo repo11 = new Repo("log11.txt");
            Controller ctr11 = new Controller(repo11);
            ctr11.addProgram(prg11);
            ctrls.add(ctr11);



            //Ref int v1; int cnt;new(v1,1);
            // createSemaphore(cnt,rH(v1));
            // fork(acquire(cnt);wh(v1,rh(v1)*10);print(rh(v1));release(cnt));
            // fork(acquire(cnt);wh(v1,rh(v1)*10);wh(v1,rh(v1)*2);print(rh(v1));release(cnt));
            //acquire(cnt);print(rh(v1)-1);release(cnt)
            // The final Out should be {10,200,9} or {10,9,200}.

            IStmt ex12 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("cnt", new IntType()),
                            new CompStmt(new AllocateHeapStmt("v1", new ConstExp(new IntValue(1))),
                                    new CompStmt(new CreateSemaphoreStmt("cnt",new ReadHeapExp(new VarExp("v1"))),
                    new CompStmt(new ForkStmt(new CompStmt(new AcquireStmt("cnt"),
                                new CompStmt(new WriteHeapStmt("v1", new ArithExp('*',new ReadHeapExp(new VarExp("v1")),new ConstExp(new IntValue(10)))),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                                                new ReleaseStmt("cnt"))))),
                    new CompStmt(new ForkStmt(new CompStmt(new AcquireStmt("cnt"),
                                new CompStmt(new WriteHeapStmt("v1", new ArithExp('*',new ReadHeapExp(new VarExp("v1")),new ConstExp(new IntValue(10)))),
                                        new CompStmt(new WriteHeapStmt("v1", new ArithExp('*',new ReadHeapExp(new VarExp("v1")),new ConstExp(new IntValue(2)))),
                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                                                        new ReleaseStmt("cnt")))))),
                    new CompStmt(new AcquireStmt("cnt"),
                            new CompStmt(new PrintStmt(new ArithExp('-', new ReadHeapExp(new VarExp("v1")), new ConstExp(new IntValue(1)))),
                                    new ReleaseStmt("cnt")))))))));


            MyStack<IStmt> exeStack12 = new MyStack<IStmt>();
            IHeap<Integer, IValue> heapTable12 = new Heap<Integer, IValue>();
            IDict<String, BufferedReader> fileTable12 = new Dict<String, BufferedReader>();
            IDict<String, IValue> symTable12 = new Dict<String, IValue>();
            IList<IValue> out12 = new List<IValue>();
            ISemaphore st12 = new Semaphore();
            ex12.typecheck(new Dict<String, IType>());
            PrgState prg12 = new PrgState(exeStack12, symTable12, out12, fileTable12,heapTable12, ex12, st12);
            Repo repo12 = new Repo("log12.txt");
            Controller ctr12 = new Controller(repo12);
            ctr12.addProgram(prg12);
            ctrls.add(ctr12);


            for (Controller c : ctrls
            ) {
                ListPrgs.getItems().add(c.getExeStackString());
            }

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK);
            alert.show();
        }

    }
}
