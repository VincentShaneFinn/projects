using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyGunControllerType1 : MonoBehaviour {

    public EnemyBulletType1 bullet;
    public float bulletSpeed;

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
            else
            {
                trackTime -= Time.deltaTime;
            }
        }
    }
}
