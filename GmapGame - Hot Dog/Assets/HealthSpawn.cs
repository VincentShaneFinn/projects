using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HealthSpawn : MonoBehaviour {

    public float cooldown;
    private float countdown;

    public GameObject healthpickup;

    private GameObject healthAlive;

	// Use this for initialization
	void Start () {
        healthAlive = Instantiate(healthpickup, transform.position, transform.rotation);
        countdown = cooldown;
	}
	
	// Update is called once per frame
	void Update () {
        if (healthAlive == null)
        {
            countdown -= Time.deltaTime;
            if(countdown <= 0)
            {
                healthAlive = Instantiate(healthpickup, transform.position, transform.rotation);
                countdown = cooldown;
            }
        }
	}
}
