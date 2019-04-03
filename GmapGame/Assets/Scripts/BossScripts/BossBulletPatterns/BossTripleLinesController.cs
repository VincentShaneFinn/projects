using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossTripleLinesController : MonoBehaviour
{

    public float bulletSpeed;

    public float timeBetweenShots;
    public float fireRate;
    public int shotsToFire;
    private float shotCounter;
    private int shotsFired;
    private float trackTime;
    public Transform firePoint;
    public Transform firePointL;
    public Transform firePointR;

    public float spreadWidth;

    public int EnemyLevel;

    public bool CanFire;

    private float savedSpeed;

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
        savedSpeed = 4;
    }

    // Update is called once per frame
    void Update()
    {
        CanFire = GetComponent<BossController>().canFire;
        if (CanFire)
        {
            if (trackTime <= 0)
            {
                gameObject.GetComponent<BossController>().canLook = false;
                gameObject.GetComponent<BossMovementController>().Speed = 0;
                if (EnemyLevel == 1)
                {
                    gameObject.GetComponent<BossController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
                        shotCounter = fireRate;
                        GameObject bullet1 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet1 != null)
                        {
                            bullet1.transform.position = firePoint.position;
                            bullet1.transform.rotation = firePoint.rotation;
                            bullet1.SetActive(true);
                        }
                        bullet1.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePointL.position;
                            bullet2.transform.rotation = firePointL.rotation;
                            bullet2.SetActive(true);
                        }
                        bullet2.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePointR.position;
                            bullet3.transform.rotation = firePointR.rotation;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
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
                        GameObject bullet1 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet1 != null)
                        {
                            bullet1.transform.position = firePoint.position;
                            bullet1.transform.rotation = firePoint.rotation;
                            bullet1.SetActive(true);
                        }
                        bullet1.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePointL.position;
                            bullet2.transform.rotation = firePointL.rotation;
                            bullet2.SetActive(true);
                        }
                        bullet2.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePointR.position;
                            bullet3.transform.rotation = firePointR.rotation;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
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
                        GameObject bullet1 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet1 != null)
                        {
                            bullet1.transform.position = firePoint.position;
                            bullet1.transform.rotation = firePoint.rotation;
                            bullet1.SetActive(true);
                        }
                        bullet1.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePointL.position;
                            bullet2.transform.rotation = firePointL.rotation;
                            bullet2.SetActive(true);
                        }
                        bullet2.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePointR.position;
                            bullet3.transform.rotation = firePointR.rotation;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
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
                gameObject.GetComponent<BossController>().canLook = true;
                gameObject.GetComponent<BossMovementController>().Speed = savedSpeed;
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
