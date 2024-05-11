package Graph;

public class Vertex
{
    private String label;
    public int x;
    public int y;

    public Vertex(String label)
    {
        this.label = label;
    }

    @Override
    public String toString()
    {
        return label;
    }

    public String getLabel()
    {
        return label;
    }
}
