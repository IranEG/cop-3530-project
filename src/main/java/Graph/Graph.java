package Graph;
import java.util.List;

public interface Graph
{
    public List<Vertex> vertices();

    public List<Edge> edges();

    public List<Edge> incidentEdges(Vertex v);
}
