using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyTripleAngled2Controller : MonoBehaviour {

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
                    //Inner
                    shotCounter = fireRate;
                    Quaternion rot = firePoint.rotation;
                    GameObject bullet1 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                    if (bullet1 != null)
                    {
                        bullet1.transform.position = firePoint.position;
                        bullet1.transform.rotation = rot;
                        bullet1.SetActive(true);
                    }
                    bullet1.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                    //Outer
                    Vector3 temp = rot.eulerAngles;
                    temp = new Vector3(temp.x, temp.y + 30, temp.z);
                    rot = Quaternion.Euler(temp);
                    GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                    if (bullet2 != null)
                    {
                        bullet2.transform.position = firePoint.position;
                        bullet2.transform.rotation = rot;
                        bullet2.SetActive(true);
                    }
                    temp.y = temp.y - 60;
                    rot = Quaternion.Euler(temp);
                    bullet2.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                    GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                    if (bullet3 != null)
                    {
                        bullet3.transform.position = firePoint.position;
                        bullet3.transform.rotation = rot;
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
