using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossLavaThrowController : MonoBehaviour
{
    public GameObject LavaBall;
    public float timeBetweenShots;
    public float fireRate;
    public int shotsToFire;
    private float shotCounter;
    private int shotsFired;
    private float trackTime;
    public Transform firePoint;

    public bool CanFire;

    public void enableFiring()
    {
        trackTime = 0.5f;
        shotsFired = 0;
        shotCounter = fireRate;
        CanFire = true;
    }

    public void disableFiring()
    {
        CanFire = false;
    }

    // Use this for initialization
    void Start()
    {
        shotsFired = 0;
        trackTime = timeBetweenShots;
    }

    // Update is called once per frame
    void Update()
    {
        //CanFire = GetComponent<BossController>().canFire;
        if (true)
        {
            if (trackTime <= 0)
            {
                shotCounter -= Time.deltaTime;
                if (shotCounter <= 0)
                {
                    shotCounter = fireRate;
                    GameObject ball = Instantiate(LavaBall, firePoint.position, firePoint.rotation);
                    shotsFired++;
                    if (shotsFired >= shotsToFire)
                    {
                        trackTime = timeBetweenShots;
                        shotsFired = 0;
                    }
                }
            }
            else
            {
                trackTime -= Time.deltaTime;
            }
        }
        else
        {
            trackTime = 0.5f;
            shotsFired = 0;
            shotCounter = fireRate;
        }
    }
}
