package us.gonet.serverutils.model.jke;

import com.google.gson.GsonBuilder;
import us.gonet.serverutils.model.node.NodeSingleModel;

public class PinBlockModel extends TMK{

    private NodeSingleModel nodeSingle;

    public NodeSingleModel getNodeSingle () {
        return nodeSingle;
    }

    public void setNodeSingle ( NodeSingleModel nodeSingle ) {
        this.nodeSingle = nodeSingle;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}

