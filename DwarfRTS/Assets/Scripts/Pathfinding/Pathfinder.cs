using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pathfinder : MonoBehaviour {
    public GameObject Line;
    public GameObject VisitedIndicator;

    public bool Astar;
    public bool Fast;
    // Use this for initialization
    void Start() {
        
    }

    public void PathToTarget(Node Start, Node Target)
    {
        foreach (GameObject node in GameObject.FindGameObjectsWithTag("Node")){
            node.GetComponent<Node>().parent = null;
        }
        StartCoroutine(GetPath(Start, Target));
    }

    public List<GameObject> SeePath;

    //Start.g = 0;
    //Start.h = heuristic(Start)
    //OPEN = [Start];
    //CLOSED = []
    //WHILE OPEN is not empty

    //    N = OPEN.removeLowestF()
    //    IF goal(N) RETURN path to N

    //    CLOSED.add(N)
    //    FOR all children M of N not in CLOSED nor OPEN:
    //		M.parent = N
    //        M.g = N.g + 1;
    //M.h = heuristic(M)

    //        OPEN.add(M)
    //    ENDFOR
    //ENDWHILE

    public float heuristic(GameObject start, GameObject goal)
    {
        //print("Heuristic Distance: " + Vector2.Distance(start.transform.position, goal.transform.position) + " Start: " + start + " Goal: " + goal);
        //euclidian distance
        return Mathf.Abs(start.transform.position.x - goal.transform.position.x) + Mathf.Abs(start.transform.position.y - goal.transform.position.y);
    }

    //Given Start and Target Nodes, return the shortest path, Coroutine makes sure it doesnt freeze the game while it searches
    public IEnumerator GetPath(Node Start, Node Target)
    {
        //Initialize everything we need before the while loop
        Start.g = 0;
        Start.h = heuristic(Start.gameObject, Target.gameObject);

        List<GameObject> path = new List<GameObject>(); //path that will be returned
        List<GameObject> OPEN = new List<GameObject>() { Start.gameObject };
        List<GameObject> CLOSED = new List<GameObject>();
        GameObject branchingPath = new GameObject(); //visual element
        while(OPEN.Count > 0)
        {
            int i;
            if (Astar)
                i = GetLowest(OPEN); // A* simply tries the estimated shortest distance to goal
            else
                i = 0; // Breadth First uses the next open node
            Node N = OPEN[i].GetComponent<Node>();
            OPEN.RemoveAt(i);
            GameObject box = Instantiate(VisitedIndicator, N.gameObject.transform.position, Quaternion.identity); //visual
            box.transform.parent = branchingPath.transform; //visual

            if (N.gameObject == Target.gameObject) // If we are at the goal, we are done, keep adding each node's parent starting at the goal, and reverse the list
            {
                path = PathToN(N.gameObject);
                break;
            }

            CLOSED.Add(N.gameObject);

            N.SetChildren();
            foreach (GameObject child in N.children) // for each child node, set N as the parent, the heuristic value, and add to the open list.
            {
                Node M = child.GetComponent<Node>();
                if (OPEN.Contains(child) || CLOSED.Contains(child))
                {
                    continue;
                }
                M.parent = N.gameObject;
                M.g = N.g + 1;
                M.h = heuristic(M.gameObject, Target.gameObject); // Manhatton distance
                OPEN.Add(M.gameObject);
            }
            if(Fast)
                yield return null; //each iteration of the while loop responds to 1 frame, The ai is as fast as your framerate
            else
                yield return new WaitForSeconds(.1f); // wait for period of time to give the player a better chance
        }
        SeePath = path; // all visual below
        Destroy(branchingPath);
        for (int index = 0; index < path.Count-1; index++)
        {
            DrawLine(path[index].transform.position, path[index + 1].transform.position);
        }
        GetComponent<AIMover>().SetNewPath(path);
    }

    //Go through each item in the open list and return the smallest
    public int GetLowest(List<GameObject> list)
    {
        int i = 0;
        float lowest = Mathf.Infinity;
        int currentI = 0;
        foreach (GameObject node in list)
        {
            if(node.GetComponent<Node>().h < lowest)
            {
                lowest = node.GetComponent<Node>().h;
                i = currentI;
            }
            currentI++;
        }
        return i;
    }

    //starting at a node, construct a list from each parent node, and reverse
    public List<GameObject> PathToN(GameObject n)
    {
        List<GameObject> path = new List<GameObject>();
        GameObject currentNode = n;
        while(currentNode.gameObject != null)
        {
            path.Add(currentNode.gameObject);
            if(currentNode.GetComponent<Node>().parent != null)
            {
                currentNode = currentNode.GetComponent<Node>().parent;
            }
            else
            {
                break;
            }
        }

        path.Reverse();

        return path;
    }

    public void printNodes(List<GameObject> list)
    {
        foreach(GameObject n in list)
        {
            print(n);
        }
    }

    void DrawLine(Vector3 start, Vector3 end)
    {
        Vector2 rotDir = (start - end).normalized;
        float angle = Mathf.Atan2(-rotDir.y, -rotDir.x) * Mathf.Rad2Deg;
        GameObject l = Instantiate(Line, start, Quaternion.AngleAxis(angle, Vector3.forward));
    }
}