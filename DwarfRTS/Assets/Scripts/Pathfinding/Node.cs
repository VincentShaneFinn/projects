using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Node : MonoBehaviour {

    public GameObject NorthNode;
    public GameObject EastNode;
    public GameObject SouthNode;
    public GameObject WestNode;

    public LayerMask layerMask;
    private NodesParameterHelper nph;

    // Use this for initialization
    void Start () {
        nph = GameObject.FindGameObjectWithTag("NodeParameters").GetComponent<NodesParameterHelper>();
        FindNearbyNodes();
    }
	
	// Update is called once per frame
	void Update () {
        FindNearbyNodes();
	}
    //Shoots ray up, right, down, and left, and adds what it hit to the appropriate variable. I do check when I use it if the variable is a node, wall, or empty
    public void FindNearbyNodes()
    {
        // Cast a ray up
        RaycastHit2D hit = Physics2D.Raycast(transform.position + new Vector3(0, .3f, 0), Vector2.up, .7f, layerMask);
        if (nph.DrawLinks && hit.collider != null && hit.collider.gameObject.tag == "Node") // only draw rays that hit nodes
            Debug.DrawLine(transform.position + new Vector3(0, .3f, 0), hit.point);

        // If it hits something...
        if (hit.collider != null)
        {
            NorthNode = (hit.transform.gameObject);
        }

        hit = Physics2D.Raycast(transform.position + new Vector3(.3f, 0, 0), Vector2.right, .7f, layerMask);
        if (nph.DrawLinks && hit.collider != null && hit.collider.gameObject.tag == "Node")
            Debug.DrawLine(transform.position + new Vector3(.3f, 0, 0), hit.point);

        // If it hits something...
        if (hit.collider != null)
        {
            EastNode = (hit.transform.gameObject);
        }

        hit = Physics2D.Raycast(transform.position + new Vector3(0, -.3f, 0), Vector2.down, .7f, layerMask);
        if (nph.DrawLinks && hit.collider != null && hit.collider.gameObject.tag == "Node")
            Debug.DrawLine(transform.position + new Vector3(0, -.3f, 0), hit.point);

        // If it hits something...
        if (hit.collider != null)
        {
            SouthNode = (hit.transform.gameObject);
        }

        hit = Physics2D.Raycast(transform.position + new Vector3(-.3f, 0, 0), Vector2.left, .7f, layerMask);
        if (nph.DrawLinks && hit.collider != null && hit.collider.gameObject.tag == "Node")
            Debug.DrawLine(transform.position + new Vector3(-.3f, 0, 0), hit.point);

        // If it hits something...
        if (hit.collider != null)
        {
            WestNode = (hit.transform.gameObject);
        }
    }

    public float g;
    public float h;
    public GameObject parent;
    public List<GameObject> children;

    public void SetChildren()
    {
        children = new List<GameObject>();
        g = 0;
        h = 0;
        if (NorthNode.tag == "Node")
        {
            children.Add(NorthNode);
        }
        if (EastNode.tag == "Node")
        {
            children.Add(EastNode);
        }
        if (SouthNode.tag == "Node")
        {
            children.Add(SouthNode);
        }
        if (WestNode.tag == "Node")
        {
            children.Add(WestNode);
        }
    }
}
