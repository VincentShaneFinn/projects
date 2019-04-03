using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossBigWaveController : MonoBehaviour
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
        savedSpeed = gameObject.GetComponent<BossMovementController>().Speed;
    }

    // Update is called once per frame
    void Update()
    {
        CanFire = GetComponent<BossController>().canFire;
        if (CanFire)
        {
            if (trackTime <= 0)
            {
                gameObject.GetComponent<BossMovementController>().Speed = 0;
                if (EnemyLevel == 1)
                {
                    gameObject.GetComponent<BossController>().isFiring = true;
                    shotCounter -= Time.deltaTime;
                    if (shotCounter <= 0)
                    {
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
                        Vector3 temp = rot.eulerAngles;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePoint.position;
                            bullet2.transform.rotation = rot;
                            bullet2.SetActive(true);
                        }
                        bullet2.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePoint.position;
                            bullet3.transform.rotation = rot;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet4 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet4 != null)
                        {
                            bullet4.transform.position = firePoint.position;
                            bullet4.transform.rotation = rot;
                            bullet4.SetActive(true);
                        }
                        bullet4.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet5 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet5 != null)
                        {
                            bullet5.transform.position = firePoint.position;
                            bullet5.transform.rotation = rot;
                            bullet5.SetActive(true);
                        }
                        bullet5.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet6 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet6 != null)
                        {
                            bullet6.transform.position = firePoint.position;
                            bullet6.transform.rotation = rot;
                            bullet6.SetActive(true);
                        }
                        temp.y = temp.y - 30;
                        rot = Quaternion.Euler(temp);
                        bullet6.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        GameObject bullet7 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet7 != null)
                        {
                            bullet7.transform.position = firePoint.position;
                            bullet7.transform.rotation = rot;
                            bullet7.SetActive(true);
                        }
                        bullet7.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        GameObject bullet8 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet8 != null)
                        {
                            bullet8.transform.position = firePoint.position;
                            bullet8.transform.rotation = rot;
                            bullet8.SetActive(true);
                        }
                        bullet8.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet9 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet9 != null)
                        {
                            bullet9.transform.position = firePoint.position;
                            bullet9.transform.rotation = rot;
                            bullet9.SetActive(true);
                        }
                        bullet9.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet10 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet10 != null)
                        {
                            bullet10.transform.position = firePoint.position;
                            bullet10.transform.rotation = rot;
                            bullet10.SetActive(true);
                        }
                        bullet10.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet11 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet11 != null)
                        {
                            bullet11.transform.position = firePoint.position;
                            bullet11.transform.rotation = rot;
                            bullet11.SetActive(true);
                        }
                        bullet11.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
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
                        Quaternion rot = firePoint.rotation;
                        GameObject bullet1 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet1 != null)
                        {
                            bullet1.transform.position = firePoint.position;
                            bullet1.transform.rotation = rot;
                            bullet1.SetActive(true);
                        }
                        bullet1.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        Vector3 temp = rot.eulerAngles;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePoint.position;
                            bullet2.transform.rotation = rot;
                            bullet2.SetActive(true);
                        }
                        bullet2.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePoint.position;
                            bullet3.transform.rotation = rot;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet4 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet4 != null)
                        {
                            bullet4.transform.position = firePoint.position;
                            bullet4.transform.rotation = rot;
                            bullet4.SetActive(true);
                        }
                        bullet4.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet5 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet5 != null)
                        {
                            bullet5.transform.position = firePoint.position;
                            bullet5.transform.rotation = rot;
                            bullet5.SetActive(true);
                        }
                        bullet5.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet6 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet6 != null)
                        {
                            bullet6.transform.position = firePoint.position;
                            bullet6.transform.rotation = rot;
                            bullet6.SetActive(true);
                        }
                        temp.y = temp.y - 30;
                        rot = Quaternion.Euler(temp);
                        bullet6.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        GameObject bullet7 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet7 != null)
                        {
                            bullet7.transform.position = firePoint.position;
                            bullet7.transform.rotation = rot;
                            bullet7.SetActive(true);
                        }
                        bullet7.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet8 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet8 != null)
                        {
                            bullet8.transform.position = firePoint.position;
                            bullet8.transform.rotation = rot;
                            bullet8.SetActive(true);
                        }
                        bullet8.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet9 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet9 != null)
                        {
                            bullet9.transform.position = firePoint.position;
                            bullet9.transform.rotation = rot;
                            bullet9.SetActive(true);
                        }
                        bullet9.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet10 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet10 != null)
                        {
                            bullet10.transform.position = firePoint.position;
                            bullet10.transform.rotation = rot;
                            bullet10.SetActive(true);
                        }
                        bullet10.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet11 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet11 != null)
                        {
                            bullet11.transform.position = firePoint.position;
                            bullet11.transform.rotation = rot;
                            bullet11.SetActive(true);
                        }
                        bullet11.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
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
                        Quaternion rot = firePoint.rotation;
                        GameObject bullet1 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet1 != null)
                        {
                            bullet1.transform.position = firePoint.position;
                            bullet1.transform.rotation = rot;
                            bullet1.SetActive(true);
                        }
                        bullet1.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        Vector3 temp = rot.eulerAngles;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet2 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet2 != null)
                        {
                            bullet2.transform.position = firePoint.position;
                            bullet2.transform.rotation = rot;
                            bullet2.SetActive(true);
                        }
                        bullet2.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet3 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet3 != null)
                        {
                            bullet3.transform.position = firePoint.position;
                            bullet3.transform.rotation = rot;
                            bullet3.SetActive(true);
                        }
                        bullet3.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet4 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet4 != null)
                        {
                            bullet4.transform.position = firePoint.position;
                            bullet4.transform.rotation = rot;
                            bullet4.SetActive(true);
                        }
                        bullet4.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet5 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet5 != null)
                        {
                            bullet5.transform.position = firePoint.position;
                            bullet5.transform.rotation = rot;
                            bullet5.SetActive(true);
                        }
                        bullet5.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y + 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet6 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet6 != null)
                        {
                            bullet6.transform.position = firePoint.position;
                            bullet6.transform.rotation = rot;
                            bullet6.SetActive(true);
                        }
                        temp.y = temp.y - 30;
                        rot = Quaternion.Euler(temp);
                        bullet6.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
                        GameObject bullet7 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                        if (bullet7 != null)
                        {
                            bullet7.transform.position = firePoint.position;
                            bullet7.transform.rotation = rot;
                            bullet7.SetActive(true);
                        }
                        bullet7.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet8 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet8 != null)
                        {
                            bullet8.transform.position = firePoint.position;
                            bullet8.transform.rotation = rot;
                            bullet8.SetActive(true);
                        }
                        bullet8.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet9 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                        if (bullet9 != null)
                        {
                            bullet9.transform.position = firePoint.position;
                            bullet9.transform.rotation = rot;
                            bullet9.SetActive(true);
                        }
                        bullet9.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet10 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet10 != null)
                        {
                            bullet10.transform.position = firePoint.position;
                            bullet10.transform.rotation = rot;
                            bullet10.SetActive(true);
                        }
                        bullet10.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
                        GameObject bullet11 = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                        if (bullet11 != null)
                        {
                            bullet11.transform.position = firePoint.position;
                            bullet11.transform.rotation = rot;
                            bullet11.SetActive(true);
                        }
                        bullet11.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
                        temp = new Vector3(temp.x, temp.y - 5, temp.z);
                        rot = Quaternion.Euler(temp);
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
                gameObject.GetComponent<BossMovementController>().Speed = savedSpeed;
                gameObject.GetComponent<BossController>().canLook = true;
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
