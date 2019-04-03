using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerPoweredUp : MonoBehaviour {

    public float powerUpTime;
    private float powerUpCount;

    public SpriteRenderer tophalf;
    public SpriteRenderer bothalf;

    private Color originalColor;
    public Color powerColor;

    private bool poweredUp;

	// Use this for initialization
	void Start () {
        powerUpCount = powerUpTime;
        poweredUp = false;

        originalColor = tophalf.color;
	}
	
	// Update is called once per frame
	void Update () {
		if(powerUpCount < powerUpTime)
        {
            powerUpCount += Time.deltaTime;
        }
        else
        {
            tophalf.color = originalColor;
            bothalf.color = originalColor;
            poweredUp = false;
        }

	}

    public void powerUp()
    {
        poweredUp = true;
        powerUpCount = 0;
        tophalf.color = powerColor;
        bothalf.color = powerColor;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Enemy")
        {
            if (poweredUp)
            {
                Destroy(collision.gameObject);
            }
            else
            {
                SceneManager.LoadScene(0);
                Destroy(gameObject);
            }
        }
    }
}
