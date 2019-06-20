using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class EnemyWaypoints
{
    public int NumberOfWaypoints;
    public GameObject WaypointObject;
}

public class EnemyMovementController : MonoBehaviour {

    public Transform[] Waypoints;
    public float Speed;
    public float waitTime;
    public int curWaypoint;
    public bool Patrol = true;
    public Vector3 Target;
    public Vector3 MoveDirection;
    public Vector3 Velocity;

    private float waitCount;

    private void Start()
    {
        waitCount = waitTime;
    }

    private void Update()
    {
        if (curWaypoint < Waypoints.Length)
        {
            Target = Waypoints[curWaypoint].position;
            MoveDirection = Target - transform.position;
            Velocity = GetComponent<Rigidbody>().velocity;

            if (MoveDirection.magnitude < .1)
            {
                if (waitCount <= 0) {
                    curWaypoint++;
                    waitCount = waitTime;
                }
                else
                {
                    waitCount -= Time.deltaTime;
                    Velocity = Vector3.zero;
                }
                
            }
            else
            {
                Velocity = MoveDirection.normalized * Speed;
            }
        }
        else
        {
            if (Patrol)
            {
                curWaypoint = 0;
            }
            else
            {
                Velocity = Vector3.zero;
            }
        }
        GetComponent<Rigidbody>().velocity = Velocity;
        {
            //transform.Rotate(new Vector3(0, 300, 0) * Time.deltaTime);
        }
    }
}
