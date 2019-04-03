using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class FireRatePowerUp : MonoBehaviour {

    public Text alert;
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
            collision.gameObject.GetComponent<PlayerGunController>().timeBetweenShots -= .025f;
            PlayerStats.PlayerFireRate = collision.gameObject.GetComponent<PlayerGunController>().timeBetweenShots;
            alert.text = "Fire Rate Increased";
            Destroy(gameObject);
        }

    }
}
