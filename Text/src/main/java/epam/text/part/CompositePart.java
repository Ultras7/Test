package epam.text.part;


import java.util.ArrayList;
import java.util.List;


public class CompositePart implements IPart {
    private List<IPart> partList = new ArrayList<IPart>();


    @Override
    public void addElement(IPart part) {
        partList.add(part);

    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (IPart pars : partList){
            sb.append(pars.toString());
        }
        return sb.toString();
    }


}
