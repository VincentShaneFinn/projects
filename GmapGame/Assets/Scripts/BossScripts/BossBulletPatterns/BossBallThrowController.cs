using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossBallThrowController : MonoBehaviour
{
    public GameObject Ball1;
    public GameObject Ball2;
    public GameObject Ball3;
    public float timeBetweenShots;
    public float fireRate;
    public int shotsToFire;
    private float shotCounter;
    private int shotsFired;
    private float trackTime;
    public Transform firePoint;

    public int reverse;

    public int EnemyLevel;

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
        CanFire = GetComponent<BossController>().canFire;
        if (CanFire)
        {
            if (trackTime <= 0)
            {
                if (EnemyLevel == 1)
                {
                    gameObject.GetComponent<BossController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
                        shotCounter = fireRate;
                        GameObject ball = Instantiate(Ball1, firePoint.position, firePoint.rotation);
                        ball.GetComponent<BossBulletSwirl1>().reverse = reverse;
                        shotsFired++;
                        if (shotsFired >= shotsToFire)
                        {
                            trackTime = timeBetweenShots;
                            shotsFired = 0;
                        }
                    }
                }
                else if (EnemyLevel == 2)
                {
                    gameObject.GetComponent<BossController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
                        shotCounter = fireRate;
                        GameObject ball = Instantiate(Ball2, firePoint.position, firePoint.rotation);
                        ball.GetComponent<BossBulletSwirl1>().reverse = reverse;
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
                    gameObject.GetComponent<BossController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
                        shotCounter = fireRate;
                        GameObject ball = Instantiate(Ball3,firePoint.position,firePoint.rotation);
                        ball.GetComponent<BossBulletSwirl1>().reverse = reverse;
                        shotsFired++;
                        if (shotsFired >= shotsToFire)
                        {
                            trackTime = timeBetweenShots;
                            shotsFired = 0;
                        }
                    }
                }
            }
            else
            {
                gameObject.GetComponent<BossController>().isFiring = false;
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
