package View;

import Controller.Controller;
import Model.PrgState;
import Model.adt.IStack;
import Model.exceptions.RepoException;
import Model.stmt.IStmt;
import Model.value.IValue;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Window2Code {
    public TextField nrPrgStates;
    public TableView<HeapTableElement> heap;
    public ListView<String> Out;
    public ListView<String> Files;
    public ListView<String> States;
    public TableView<SymTableElement> Sym;
    public ListView<String> Exe;
    public Button loadButton;
    public Button STEP;
    public TableView<SemaphoreTableElement> st;

    Controller c;

    @FXML
    public void initialize(){
        try{
            Sym.getColumns().get(0).setCellValueFactory(
                    new PropertyValueFactory<>("name")
            );
            Sym.getColumns().get(1).setCellValueFactory(
                    new PropertyValueFactory<>("value")
            );

            heap.getColumns().get(0).setCellValueFactory(
                    new PropertyValueFactory<>("address")
            );
            heap.getColumns().get(1).setCellValueFactory(
                    new PropertyValueFactory<>("value")
            );

            st.getColumns().get(0).setCellValueFactory(
                    new PropertyValueFactory<>("index")
            );
            st.getColumns().get(1).setCellValueFactory(
                    new PropertyValueFactory<>("value")
            );
            st.getColumns().get(2).setCellValueFactory(
                    new PropertyValueFactory<>("values")
            );


            States.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1){
                    String item = States.getSelectionModel().getSelectedItem();
                    if (item!=null){
                        int id = Integer.parseInt(item);
                        c.r.getPrgList().forEach(prg ->{
                            if(prg.getId() == id){
                                Exe.getItems().clear();
                                ArrayList<String> x = new ArrayList<String>();
                                ArrayList<IStmt> y = prg.getExeStack().getList();
                                for (IStmt elem: y
                                ) {x.add(elem.toString());}
                                Collections.reverse(x);
                                Exe.getItems().addAll(x);
                                Sym.getItems().clear();
                                prg.getSymTable().getContent().keySet().forEach(key ->{
                                    Sym.getItems().add(new SymTableElement(key, prg.getSymTable().getContent().get(key)));
                                });
                            }
                        });
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }

    public void loadEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) loadButton.getScene().getWindow();
        c = (Controller) stage.getUserData();

        List<PrgState> ps = c.getPrgStates();

        nrPrgStates.setText(Integer.toString(ps.size()));
        for (PrgState p: ps
             ) { States.getItems().add(Integer.toString((p.getId())));
        }


    }

    public void stepEvent(ActionEvent actionEvent) {
        try {
            c.executor = Executors.newFixedThreadPool(2);
            List<PrgState> prgList = c.removeCompletedPrg(c.getPrgStates());

            List<Integer> l = new ArrayList<Integer>();
            prgList.forEach(prg -> {
                try {
                    l.addAll(c.getAddrFromSymTable(prg.getSymTable().getContent().values()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            if(prgList.isEmpty()){
                throw new RepoException("Nothing left to execute!");
            }
            prgList.get(0).getHeapTable().setContent(c.garbageCollector(l,
                    c.getAddrFromHeap(prgList.get(0).getHeapTable().getContent().values()),
                    prgList.get(0).getHeapTable().getContent()
            ));
            c.oneStepForAllPrg(prgList);

            prgList = c.removeCompletedPrg(c.getPrgStates());

            //now we update the window
            //update prgstates
            List<PrgState> ps = c.getPrgStates();
            States.getItems().clear();
            for (PrgState p: ps
            ) { States.getItems().add(Integer.toString((p.getId())));
            }


            //update sym and exe
            c.r.getPrgList().forEach(prg ->{
                    Exe.getItems().clear();
                    ArrayList<String> x = new ArrayList<String>();
                    ArrayList<IStmt> y = prg.getExeStack().getList();
                    for (IStmt elem: y
                         ) {x.add(elem.toString());
                    }
                    Collections.reverse(x);
                    Exe.getItems().addAll(x);
                    Sym.getItems().clear();
                    prg.getSymTable().getContent().keySet().forEach(key ->{
                        Sym.getItems().add(new SymTableElement(key, prg.getSymTable().getContent().get(key)));
                    });
            });

            //update out and heap and file
            c.r.getPrgList().forEach(prg ->{
                Files.getItems().clear();
                ArrayList<String> yy = prg.getFileTable().getKeys();
                Files.getItems().addAll(yy);
                Out.getItems().clear();
                ArrayList<String> x = new ArrayList<String>();
                ArrayList<IValue> y = prg.getOutput().getList();
                for (IValue elem: y
                ) {x.add(elem.toString());
                }
                Out.getItems().addAll(x);
                heap.getItems().clear();
                prg.getHeapTable().getContent().keySet().forEach(key ->{
                    heap.getItems().add(new HeapTableElement(key, prg.getHeapTable().getContent().get(key).toString()));
                });
            });


            //update nr of states
            nrPrgStates.setText(Integer.toString(ps.size()));


            //UPDATE SYNC TABLE
            c.r.getPrgList().forEach(prg ->{
                st.getItems().clear();
                prg.getSemaphoreTable().getSemaphore().getContent().keySet().forEach(key ->{
                    st.getItems().add(new SemaphoreTableElement(key, prg.getSemaphoreTable().getSemaphore().lookup(key).getKey(),
                            prg.getSemaphoreTable().getSemaphore().lookup(key).getValue().getList()));
                });
            });

        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }
}
