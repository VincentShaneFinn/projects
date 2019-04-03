using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CamaraPan : MonoBehaviour {

    private bool pan;

    public Transform normalCamera;

    public PlayerController thePlayer;

    private Vector3 originalPos;
    private Vector3 originalRot;

    public float timeToPan;
    private float count;

    public float pauseTime;
    private float pauseCount;
    public float exponential;

    public bool bossLevel;

	// Use this for initialization
	void Start () {
        originalPos = transform.position;
        originalRot = transform.eulerAngles;
        count = timeToPan;
        pauseCount = pauseTime;
        pan = PlayerStats.CameraPanMainHall;
        gameObject.SetActive(true);
        normalCamera.gameObject.SetActive(false);
        if (bossLevel)
        {
            pan = true;
        }
    }
	
	// Update is called once per frame
	void Update () {
        if (pan)
        {
            thePlayer.cutscene = true;
            try
            {
                GameObject.FindGameObjectWithTag("Boss").GetComponent<BossMovementController>().Speed = 0;
                GameObject.FindGameObjectWithTag("Boss").GetComponent<BossController>().canFire = false;
            }
            catch
            {

            }
            if (pauseCount <= 0)
            {
                if (count <= 0)
                {
                    gameObject.SetActive(false);
                    normalCamera.gameObject.SetActive(true);
                    thePlayer.cutscene = false;
                    PlayerStats.CameraPanMainHall = false;
                    try
                    {
                        GameObject.FindGameObjectWithTag("Boss").GetComponent<BossMovementController>().Speed = 4;
                        GameObject.FindGameObjectWithTag("Boss").GetComponent<BossController>().canFire = true;
                    }
                    catch
                    {

                    }
                }
                else
                {
                    count -= Time.deltaTime;
                    transform.position = new Vector3(originalPos.x + (normalCamera.position.x - originalPos.x) * Mathf.Pow((timeToPan - count) / timeToPan, exponential), originalPos.y + (normalCamera.position.y - originalPos.y) * Mathf.Pow((timeToPan - count) / timeToPan, exponential), originalPos.z + (normalCamera.position.z - originalPos.z) * Mathf.Pow((timeToPan - count) / timeToPan, exponential));
                    Vector3 temp = normalCamera.eulerAngles;
                    Vector3 temp2 = originalRot;
                    //temp2.x + (temp.x - temp2.x) * 1 / (timeToPan - count)
                    try
                    {
                        if (count < count / 2)
                        {
                            temp = new Vector3(temp2.x + (temp.x - 1) * Mathf.Pow((timeToPan - count) / timeToPan, 1), (temp.y) + temp2.y * Mathf.Pow((count / timeToPan), 1 / 1), temp.z);
                        }
                        else
                        {
                            temp = new Vector3(temp2.x + (temp.x - temp2.x) * Mathf.Pow((timeToPan - count) / timeToPan, exponential), (temp.y) + temp2.y * Mathf.Pow((count / timeToPan), 1 / exponential), temp.z);
                            transform.rotation = Quaternion.Euler(temp);
                        }
                    }
                    catch
                    {
                        transform.rotation = Quaternion.Euler(temp);
                    }
                }
            }
            else
            {
                pauseCount -= Time.deltaTime;
            }
        }
        else
        {
            normalCamera.gameObject.SetActive(true);
            gameObject.SetActive(false);
        }
	}
}
