package Graph;
import java.util.List;

public abstract class AbstractGraph implements Graph
{

    protected List<Vertex> vertices;
    protected List<Edge> edges;

    AbstractGraph(List<Vertex> vertices, List<Edge> edges)
    {
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<Vertex> vertices()
    {
        return vertices;
    }

    public Vertex getVertex(int index)
    {
        return this.vertices.get(index);
    }

    public List<Edge> edges()
    {
        return edges;
    }

}