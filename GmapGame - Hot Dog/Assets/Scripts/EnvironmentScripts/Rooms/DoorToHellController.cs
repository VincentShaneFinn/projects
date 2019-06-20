using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public static class PlayerStats
{

    private static bool livingKeyObtained = false;
    private static bool diningKeyObtained = false;
    private static bool cameraPanMainHall = true;

    private static float playerFireRate = .2f;
    private static float playerChargeTime = 1.6f;

    public static bool LivingKeyObtained
    {
        get
        {
            return livingKeyObtained;
        }
        set
        {
            livingKeyObtained = value;
        }
    }

    public static bool DiningKeyObtained
    {
        get
        {
            return diningKeyObtained;
        }
        set
        {
            diningKeyObtained = value;
        }
    }

    public static bool CameraPanMainHall
    {
        get
        {
            return cameraPanMainHall;
        }
        set
        {
            cameraPanMainHall = value;
        }
    }

    public static float PlayerFireRate
    {
        get
        {
            return playerFireRate;
        }
        set
        {
            playerFireRate = value;
            if(playerFireRate < .15)
            {
                playerFireRate = .15f;
            }   
        }
    }

    public static float PlayerChargeTime
    {
        get
        {
            return playerChargeTime;
        }
        set
        {
            playerChargeTime = value;
            if (playerChargeTime < 1.1f)
            {
                playerChargeTime = 1.1f;
            }
        }
    }

}

public class DoorToHellController : MonoBehaviour {

    public GameObject LeftKeyLocked;
    public GameObject RightKeyLocked;
    public GameObject LeftKeyUnLocked;
    public GameObject RightKeyUnLocked;

    public GameObject thePlayer;
    public Text message;

    bool wasInRange = false;

    // Use this for initialization
    void Start () {
        if (PlayerStats.LivingKeyObtained)
        {
            LeftKeyLocked.SetActive(false);
            LeftKeyUnLocked.SetActive(true);
        }
        if (PlayerStats.DiningKeyObtained)
        {
            RightKeyLocked.SetActive(false);
            RightKeyUnLocked.SetActive(true);
        }
	}

    // Update is called once per frame
    void Update() {

        if (Vector3.Distance(gameObject.transform.position, thePlayer.transform.position) <= 2)
        {
            wasInRange = true;
            if (PlayerStats.LivingKeyObtained && PlayerStats.DiningKeyObtained)
            {
                message.text = "Stairway to Hell\n\nPress Enter";
                if (Input.GetKeyDown(KeyCode.Return))
                {
                    SceneManager.LoadScene(9);
                }
            }
            else
            {
                message.text = "Stairway to Hell\n\nLocked";
            }
            //REMEMBER TO REMOVE THIS CHEAT CODE
            if (Input.GetKeyDown(KeyCode.LeftControl))
            {
                SceneManager.LoadScene(9);
            }
        }
        else { 
            if (wasInRange)
            {
                message.text = "";
                wasInRange = false;
            }
        }
    }
}

