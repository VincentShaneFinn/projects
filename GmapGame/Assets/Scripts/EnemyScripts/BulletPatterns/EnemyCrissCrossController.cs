using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyCrissCrossController : MonoBehaviour
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
                    gameObject.GetComponent<EnemyController>().canLook = false;
                    gameObject.GetComponent<EnemyController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
                        //Inner
                        shotCounter = fireRate;
                        Quaternion rot = firePoint.rotation;
                        GameObject bullet1 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet1 != null)
                        {
                            bullet1.transform.position = firePoint.position;
                            bullet1.transform.rotation = rot;
                            bullet1.SetActive(true);
                        }
                        bullet1.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        //Outer
                        Vector3 temp = rot.eulerAngles;
                        temp = new Vector3(temp.x, temp.y + 70, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePoint.position;
                            bullet2.transform.rotation = rot;
                            bullet2.SetActive(true);
                        }
                        temp.y = temp.y - 140;
                        rot = Quaternion.Euler(temp);
                        bullet2.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        bullet2.GetComponent<EnemyBulletType1>().ActivateCrissCrossLeft();
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePoint.position;
                            bullet3.transform.rotation = rot;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        bullet3.GetComponent<EnemyBulletType1>().ActivateCrissCrossRight();
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
                    gameObject.GetComponent<EnemyController>().canLook = false;
                    gameObject.GetComponent<EnemyController>().isFiring = true;
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
                        temp = new Vector3(temp.x, temp.y + 70, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePoint.position;
                            bullet2.transform.rotation = rot;
                            bullet2.SetActive(true);
                        }
                        temp.y = temp.y - 140;
                        rot = Quaternion.Euler(temp);
                        bullet2.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        bullet2.GetComponent<EnemyBulletType1>().ActivateCrissCrossLeft();
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePoint.position;
                            bullet3.transform.rotation = rot;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        bullet3.GetComponent<EnemyBulletType1>().ActivateCrissCrossRight();
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
                    gameObject.GetComponent<EnemyController>().canLook = false;
                    gameObject.GetComponent<EnemyController>().isFiring = true;
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
                        temp = new Vector3(temp.x, temp.y + 70, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePoint.position;
                            bullet2.transform.rotation = rot;
                            bullet2.SetActive(true);
                        }
                        temp.y = temp.y - 140;
                        rot = Quaternion.Euler(temp);
                        bullet2.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
                        bullet2.GetComponent<EnemyBulletType3>().ActivateCrissCrossLeft();
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePoint.position;
                            bullet3.transform.rotation = rot;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
                        bullet3.GetComponent<EnemyBulletType3>().ActivateCrissCrossRight();
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
                gameObject.GetComponent<EnemyController>().canLook = true;
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
