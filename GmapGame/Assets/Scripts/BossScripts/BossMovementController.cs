using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossMovementController : MonoBehaviour {

    public Transform[] Waypoints1;
    public Transform[] Waypoints2;
    public Transform[] Waypoints3;
    public float Speed;
    public float waitTime;
    public int curWaypoint;
    public bool Patrol = true;
    public Vector3 Target;
    public Vector3 MoveDirection;
    public Vector3 Velocity;

    public int phase;

    private float waitCount;

    private void Start()
    {
        waitCount = waitTime;
    }

    private void Update()
    {
        if (phase == 1)
        {
            if (curWaypoint < Waypoints1.Length)
            {
                Target = Waypoints1[curWaypoint].position;
                MoveDirection = Target - transform.position;
                Velocity = GetComponent<Rigidbody>().velocity;

                if (MoveDirection.magnitude < .1)
                {
                    if (waitCount <= 0)
                    {
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
        }
        else if(phase == 2)
        {
            if (curWaypoint < Waypoints2.Length)
            {
                Target = Waypoints2[curWaypoint].position;
                MoveDirection = Target - transform.position;
                Velocity = GetComponent<Rigidbody>().velocity;

                if (MoveDirection.magnitude < .1)
                {
                    if (waitCount <= 0)
                    {
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
        }
        else if (phase == 3)
        {
            if (curWaypoint < Waypoints2.Length)
            {
                Target = Waypoints3[curWaypoint].position;
                MoveDirection = Target - transform.position;
                Velocity = GetComponent<Rigidbody>().velocity;

                if (MoveDirection.magnitude < .1)
                {
                    if (waitCount <= 0)
                    {
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
        }
        GetComponent<Rigidbody>().velocity = Velocity;
        {
            //transform.Rotate(new Vector3(0, 300, 0) * Time.deltaTime);
        }
    }
}
