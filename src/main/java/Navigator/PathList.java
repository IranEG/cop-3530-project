package Navigator;

public class PathList
{
    String vertex;
    int x;
    int y;

    public PathList(String vertex, int x, int y)
    {
        this.vertex = vertex;
        this.x = x;
        this.y = y;
    }

    public String getVertex()
    {
        return vertex;
    }

    public int getX()
    {
        return x;
    }

    public int gety()
    {
        return y;
    }

    public String toString()
    {
        return vertex + " " + x + " " + y;
    }
}
