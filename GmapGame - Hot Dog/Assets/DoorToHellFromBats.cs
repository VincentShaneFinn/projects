using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class DoorToHellFromBats : MonoBehaviour {

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
            message.text = "Hell Itself\n\nPress Enter";
            if (Input.GetKeyDown(KeyCode.Return))
            {
                SceneManager.LoadScene(7);
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
}
