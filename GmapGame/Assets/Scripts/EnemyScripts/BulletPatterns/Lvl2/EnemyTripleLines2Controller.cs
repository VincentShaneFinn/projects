using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyTripleLines2Controller : MonoBehaviour {

    public EnemyBulletType1 bullet;
    public float bulletSpeed;

    public float timeBetweenShots;
    public float fireRate;
    public int shotsToFire;
    private float shotCounter;
    private int shotsFired;
    private float trackTime;
    public Transform firePoint;

    public float spreadWidth;

    public bool CanFire;

    public void enableFiring()
    {
        trackTime = timeBetweenShots;
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
        if (CanFire)
        {
            if (trackTime <= 0)
            {
                shotCounter -= Time.deltaTime;
                if (shotCounter <= 0)
                {
                    shotCounter = fireRate;
                    GameObject bullet1 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                    if (bullet1 != null)
                    {
                        bullet1.transform.position = transform.localPosition + transform.forward;
                        bullet1.transform.rotation = firePoint.rotation;
                        bullet1.SetActive(true);
                    }
                    bullet1.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                    GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                    if (bullet2 != null)
                    {
                        bullet2.transform.position = transform.localPosition + transform.right * -spreadWidth + transform.forward;
                        bullet2.transform.rotation = firePoint.rotation;
                        bullet2.SetActive(true);
                    }
                    bullet2.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                    GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                    if (bullet3 != null)
                    {
                        bullet3.transform.position = transform.localPosition + transform.right * spreadWidth + transform.forward;
                        bullet3.transform.rotation = transform.rotation;
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
            else
            {
                trackTime -= Time.deltaTime;
            }
        }
    }
}
