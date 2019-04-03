using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class LivingWingDoorFromMainHall : MonoBehaviour {

    public GameObject thePlayer;
    public Text message;

    bool wasInRange = false;

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (Vector3.Distance(gameObject.transform.position, thePlayer.transform.position) <= 2)
        {
            wasInRange = true;
            message.text = "Living Wing\n\nPress Enter";
            if (Input.GetKeyDown(KeyCode.Return) && !PlayerStats.LivingKeyObtained)
            {
                SceneManager.LoadScene(3);
            }
        }
        else
        {
            if (wasInRange)
            {
                message.text = "";
                wasInRange = false;
            }
        }
	}

    //private void OnCollisionEnter(Collision collision)
    //{
    //    if(collision.gameObject.tag == "Player")
    //    {
    //        SceneManager.LoadScene(2);
    //    }
    //}
}
