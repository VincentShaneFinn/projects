using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ChargeTimePowerUp : MonoBehaviour {

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
            collision.gameObject.GetComponent<PlayerAltGunController>().chargeTime -= .25f;
            PlayerStats.PlayerChargeTime = collision.gameObject.GetComponent<PlayerAltGunController>().chargeTime;
            alert.text = "Alt-Fire Charge Time Decreased";
            Destroy(gameObject);
        }

    }
}
