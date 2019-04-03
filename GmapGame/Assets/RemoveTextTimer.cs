using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class RemoveTextTimer : MonoBehaviour {

    private float countdown;
	// Use this for initialization
	void Start () {
        countdown = 5;
	}
	
	// Update is called once per frame
	void Update () {
		if(gameObject.GetComponent<Text>().text == "Fire Rate Increased" || gameObject.GetComponent<Text>().text == "Alt-Fire Charge Time Decreased")
        {
            if(countdown <= 0)
            {
                gameObject.GetComponent<Text>().text = "";
                countdown = 5;
            }
            countdown -= Time.deltaTime;
        }
	}
}
