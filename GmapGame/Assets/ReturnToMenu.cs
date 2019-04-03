using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;

public class ReturnToMenu : MonoBehaviour {

    public int sceneIndex;
    public Collider collision;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (collision.gameObject.tag == "Player")
        {
            SceneManager.LoadScene(sceneIndex);
        }
    }

    void OnTriggerChangeScene(Collider collision)
    {
        if (collision.gameObject.tag == "Player")
        {
            SceneManager.LoadScene(sceneIndex);
        }
    }
}
