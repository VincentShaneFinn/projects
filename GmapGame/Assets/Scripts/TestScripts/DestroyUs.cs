using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DestroyUs : MonoBehaviour {

	void OnTriggerEnter(Collider other)
    {
        if(other.tag == "Player")
        {
            Destroy(other.gameObject);
            Destroy(gameObject);
        }
    }
}
