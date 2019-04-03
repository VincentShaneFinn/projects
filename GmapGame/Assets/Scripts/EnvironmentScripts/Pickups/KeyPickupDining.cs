using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KeyPickupDining : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void OnTriggerEnter(Collider collision)
    {
        if (collision.gameObject.tag == "Player")
        {
            PlayerStats.DiningKeyObtained = true;
            //save key to global
            Destroy(gameObject);
        }

    }
}
