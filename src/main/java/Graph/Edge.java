package Graph;

public class Edge
{
    public Vertex vertex1, vertex2;
    public Integer weight;

    public Edge(Vertex vertex1, Vertex vertex2, Integer weight)
    {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public String toString()
    {
        return vertex1.toString() + " " + vertex2.toString() + " " + weight;
    }
}
