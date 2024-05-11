package Path;

import Graph.Vertex;

public class PathVertex extends Vertex
{
    public Integer distance;
    public Vertex parent;
    public boolean visited;

    PathVertex(String label)
    {
        super(label);
        this.distance = Integer.MAX_VALUE;
        this.parent = null;
        this.visited = false;
    }

    public String info()
    {
        String parentString = (parent == null) ? "null" : parent.toString();
        return "(parent: " + parentString + " distance: " + this.distance + " destination: " + this.getLabel() + ")";
    }
}
