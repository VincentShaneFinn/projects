using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DogHop : MonoBehaviour {

    public float pauseTime;
    private float count;
    public float jumpTime;
    private float hopCount;

    public Transform jumplocation;
    private float test;

	// Use this for initialization
	void Start () {
        count = pauseTime;
        hopCount = jumpTime;
        test = transform.position.y;
	}
	
	// Update is called once per frame
	void Update () {
        if (count <= 0)
        {
            hopCount -= Time.deltaTime;
            if (hopCount <= -jumpTime)
            {
                hopCount = jumpTime;
                count = pauseTime;
            }
            else if (hopCount <= 0)
            {

                transform.position = new Vector3(transform.position.x, test  + (jumplocation.position.y - test) * (jumpTime + hopCount) / jumpTime, transform.position.z);
            }
            else
            {
                transform.position = new Vector3(transform.position.x, test + (jumplocation.position.y - test) * ((jumpTime - hopCount) / jumpTime), transform.position.z);
            }
        }
        else
        {
            count -= Time.deltaTime;
            float hopTime = jumpTime;
        }
	}
}
