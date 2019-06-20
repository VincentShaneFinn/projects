using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyFireStraightController : MonoBehaviour
{
    public float bulletSpeed;

    public float timeBetweenShots;
    public float fireRate;
    public int shotsToFire;
    private float shotCounter;
    private int shotsFired;
    private float trackTime;
    public Transform firePoint;

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
        CanFire = GetComponent<EnemyController>().canFire;
        if (CanFire)
        {
            if (trackTime <= 0)
            {
                if (EnemyLevel == 1)
                {
                    gameObject.GetComponent<EnemyController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
                        shotCounter = fireRate;
                        GameObject bullet = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet != null)
                        {
                            bullet.transform.position = firePoint.position;
                            bullet.transform.rotation = firePoint.rotation;
                            bullet.SetActive(true);
                        }
                        bullet.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
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
                    gameObject.GetComponent<EnemyController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
                        shotCounter = fireRate;
                        GameObject bullet = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet != null)
                        {
                            bullet.transform.position = firePoint.position;
                            bullet.transform.rotation = firePoint.rotation;
                            bullet.SetActive(true);
                        }
                        bullet.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
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
                    gameObject.GetComponent<EnemyController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
                        shotCounter = fireRate;
                        GameObject bullet = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet != null)
                        {
                            bullet.transform.position = firePoint.position;
                            bullet.transform.rotation = firePoint.rotation;
                            bullet.SetActive(true);
                        }
                        bullet.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
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
                gameObject.GetComponent<EnemyController>().isFiring = false;
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
