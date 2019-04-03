using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DieAfterTime : MonoBehaviour {

    public float killTime;
	// Use this for initialization
	void Start () {
        Destroy(gameObject, killTime);
	}
	
}
