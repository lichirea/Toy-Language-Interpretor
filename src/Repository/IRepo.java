package Repository;
import Model.PrgState;
import Model.exceptions.RepoException;

import java.util.List;
import java.io.IOException;

public interface IRepo {
    void addPrg(PrgState newPrg);
    void logPrgStateExec(PrgState p) throws RepoException, IOException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> l);
}

