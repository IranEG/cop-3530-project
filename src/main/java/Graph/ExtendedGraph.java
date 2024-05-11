package Graph;

import java.util.ArrayList;
import java.util.List;

public class ExtendedGraph extends AbstractGraph
{
    public ExtendedGraph(List<Vertex> vertices, List<Edge> edges)
    {
        super(vertices, edges);
    };

    public List<Edge> incidentEdges(Vertex v)
    {
        ArrayList<Edge> incidentEdges = new ArrayList<>();

        for (Edge e: edges)
        {
            if (e.vertex1.toString().equals(v.toString()) || e.vertex2.toString().equals(v.toString()))
            {
                incidentEdges.add(e);
            }
        }

        return incidentEdges;
    };



}