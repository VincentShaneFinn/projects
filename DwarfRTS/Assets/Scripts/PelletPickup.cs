using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PelletPickup : MonoBehaviour {

    public Text ScoreCounter;
    public Text ScoreCounterShown;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void OnTriggerEnter2D(Collider2D col)
    {
        if (col.gameObject.tag == "Player")
        {
            ScoreCounter.text = (Convert.ToInt32(ScoreCounter.text) + 1).ToString();
            ScoreCounterShown.text = "Pellets: " + (Convert.ToInt32(ScoreCounter.text)).ToString() + "/ 150";
            if(ScoreCounter.text == "150")
            {
                ScoreCounterShown.text = "You Win";
            }
            Destroy(gameObject);
        }
    }
}
