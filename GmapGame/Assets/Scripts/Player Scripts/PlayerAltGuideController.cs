using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerAltGuideController : MonoBehaviour {

    public bool isVisible;
    public float WaterBallSize;

    // Use this for initialization
    void Start()
    {
        isVisible = false;
    }

    public void enableVisibility()
    {
        isVisible = true;
    }

    public void disableVisibility()
    {
        isVisible = false;
    }

    public void growBall(float c)
    {
        gameObject.transform.localScale = new Vector3(WaterBallSize * c, WaterBallSize * c, WaterBallSize * c);
    }
    public void resetBall()
    {
        gameObject.transform.localScale = new Vector3(0.1F, 0.1F, 0.1F);
    }

    // Update is called once per frame
    void Update()
    {
        if (isVisible)
        {
            gameObject.GetComponent<MeshRenderer>().enabled = true;
        }
        else
        {
            gameObject.GetComponent<MeshRenderer>().enabled = false;
        }
    }

}
