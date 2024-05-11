package Path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Graph.Vertex;
import Graph.Edge;
import Graph.ExtendedGraph;
import PriorityQueue.PriorityQueue;

public class Path 
{
    String source;
    String destination;
    List<Vertex> vertices;
    List<Edge> edges;
    ExtendedGraph graph;
    List<PathVertex> result;
    Vertex end;

    public Path() throws Exception
    {
        this.vertices = readVertices("src/main/resources/vertex.txt");
        this.edges = readEdges("src/main/resources/edge.txt");

        this.graph = new ExtendedGraph(vertices, edges);
    }

    public List<Vertex> getVertices()
    {
        return vertices;
    }

    public void calcPath(String source, String destination)
    {
        this.source = source;
        this.destination = destination;
        int index = 0;

        for (int i = 0; i < vertices.size(); i++)
        {
            if (vertices.get(i).getLabel().equals(source))
            {
                index = i;
                break;
            }
        }

        this.result = dijkstra(graph, graph.getVertex(index));
    }

    public int getDistance()
    {
        int distance = 0;
        
        for (int i = 0; i < result.size(); i++)
        {
            if (result.get(i).getLabel().equals(destination))
            {
                end = vertices.get(i);
                distance = result.get(i).distance;
            }
        }

        return distance;
    }

    public LinkedList<Vertex> getPath()
    {
        LinkedList<Vertex> path = new LinkedList<>();
        String dest = destination;
        Boolean done = false;

        path.add(end);
        
        while (!done)
        {
            for (int i = 0; i < result.size(); i++)
            {
                if (result.get(i).getLabel().equals(dest))
                {
                    if (result.get(i).parent == null)
                    {
                        done = true;
                        break;
                    }
                    
                    dest = result.get(i).parent.toString();
                    path.add(result.get(i).parent);
                    break;
                }
            }
        }
    
        return path;
    }

    public static List<PathVertex> dijkstra(ExtendedGraph g, Vertex v)
    {
        // Instantiating and assigning the paths to be tracked after each iteration
        List <PathVertex> pathvertexlist = initializeSingleSource(g, v);

        // Create a priority queue that is exactly the same thing as paths but
        // used for extracting minimum. This gets one element smaller after each iteration (see below)
        PriorityQueue<PathVertex> pqueue = updatePriorityQueueDistances(pathvertexlist);

        while (!pqueue.isEmpty())
        {
            // always starts each iteration with the smallest PathVertex, pv
            PathVertex pvertex = pqueue.poll();
            
            // We set the visited attribute of the smallest PathVertex to true
            pvertex.visited = true;
            
            // retrieve all edges incident to pv
            List<Edge> iedges = g.incidentEdges(pvertex);

            // look into (not visit) all vertices incident to pv
            for (Edge e: iedges)
            {
                // since the vertex (incidentVertex of type Vertex) returned from incident vertex
                // does not hold information about its current distance to the source,
                // we look for its exact counterpart in paths (incidentPathVertex of type PathVertex):
                for (PathVertex ipathvertex : pathvertexlist)
                {
                    // we look for its exact counterpart in paths (incidentPathVertex of type PathVertex):
                    if ((ipathvertex != pvertex) && (ipathvertex.getLabel().equals(e.vertex1.getLabel())) || (ipathvertex.getLabel().equals(e.vertex2.getLabel())))
                    {
                        // see IF the edge needs to be relaxed
                        if (relaxEdge(pvertex, ipathvertex, e.weight))
                        {
                            // if so, update the path vertices in pq
                            pqueue.update(ipathvertex);
                        }
                        
                        break;
                    }
                }
            }
        }

        return pathvertexlist;
    }

    private static PriorityQueue<PathVertex> updatePriorityQueueDistances(List<PathVertex> paths)
    {
        PriorityQueue<PathVertex> pq = new PriorityQueue<>(new SortByDistance());
        
        for (int i = 0; i < paths.size(); i++)
        {
            PathVertex currentPV = paths.get(i);

            // If the currentPV is visited, skip
            // This is how the priority queue gets smaller
            if (currentPV.visited) 
            {
                continue;
            }

            pq.add(currentPV);
        }
        return pq;
    }

    public static List<PathVertex> initializeSingleSource(ExtendedGraph g, Vertex s)
    {
        // This sets every PathVertex's parent to null and its distance to the source infinity
        // except for the source (s) where its distance is 0
        // get the vertices (which is of type Vertex) from g and create a List of type PathVertex

        PathVertex pv;

        ArrayList<PathVertex> pVertexList = new ArrayList<>();

        for (Vertex v : g.vertices())
        {
            pv = new PathVertex(v.getLabel());
            
            if (v.equals(s))
            {
                pv.distance = 0;
            }

            pVertexList.add(pv);
        }

        return pVertexList;
    }

    public static boolean relaxEdge(PathVertex v, PathVertex w, int weight)
    {
        if (!v.distance.equals(Integer.MAX_VALUE) && v.distance + weight < w.distance)
        {
            w.distance = v.distance + weight;
            w.parent = v;
            return true;
        }
        return false;
    }

    // *** ignore below ***

    public static List<Vertex> readVertices(String filePath) throws Exception
    {
        String vertexFile = readFile(filePath);
        List<String> verticesList = new ArrayList<>(Arrays.asList(vertexFile.split("\n")));
        List<Vertex> vertices = new ArrayList<>();
        
        for (int i = 0; i < verticesList.size(); i++)
        {
            vertices.add(new Vertex(verticesList.get(i).trim()));
        }
        return vertices;
    }

    public static List<Edge> readEdges(String filePath) throws Exception
    {
        String edgeFile = readFile(filePath);
        List<String> edgesList = new ArrayList<>(Arrays.asList(edgeFile.split("\n")));
        List<Edge> edges = new ArrayList<>();
        
        for (int i = 0; i < edgesList.size(); i = i + 3)
        {
            Vertex v1 = new Vertex(edgesList.get(i).trim());
            Vertex v2 = new Vertex(edgesList.get(i+1).trim());
            Integer weight = Integer.parseInt(edgesList.get(i+2).trim());
            Edge e = new Edge(v1, v2, weight);
            edges.add(e);
        }
        return edges;
    }

    public static String readFile(String fileName) throws IOException 
    {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
