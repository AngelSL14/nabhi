package us.gonet.serverutils.model.node;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeSingleModel {

    private int idNode;
    private String nodeName;

    public int getIdNode () {
        return idNode;
    }

    public void setIdNode ( int idNode ) {
        this.idNode = idNode;
    }

    public String getNodeName () {
        return nodeName;
    }

    public void setNodeName ( String nodeName ) {
        this.nodeName = nodeName;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}
