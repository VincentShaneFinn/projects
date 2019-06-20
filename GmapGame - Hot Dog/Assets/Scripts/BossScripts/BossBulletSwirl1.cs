using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossBulletSwirl1 : MonoBehaviour {

    public float speed;
    public float destroyCountdown;

    public Transform firePoint;
    public float bulletSpeed;

    public float maxScale;
    public float minScale;
    public int bulletsToFire;
    private int bulletsFired;

    public float fireRate;
    private float fireRateCountdown;

    public float reverse = 1;

    public int BulletLevel;

    public int bulletDamage;

    public float rotationSpeed;
    // Use this for initialization
    void Start()
    {
        fireRateCountdown = fireRate;
    }

    // Update is called once per frame
    void Update()
    {
        transform.Translate(Vector3.forward * speed * Time.deltaTime); //deltatime is for update framerate variances
        Quaternion rot = firePoint.rotation;
        Vector3 temp = rot.eulerAngles;
        temp = new Vector3(temp.x, temp.y + rotationSpeed * Time.deltaTime * reverse, temp.z);
        rot = Quaternion.Euler(temp);
        firePoint.rotation = rot;

        if (fireRateCountdown <= 0)
        {
            if(bulletsFired >= bulletsToFire)
            {
                Destroy(gameObject);
            }
            if(BulletLevel == 1)
            {
                GameObject bullet = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet1");
                if (bullet != null)
                {
                    bullet.transform.position = firePoint.position;
                    bullet.transform.rotation = firePoint.rotation;
                    bullet.SetActive(true);
                }
                bullet.GetComponent<EnemyBulletType1>().speed = bulletSpeed;
                bulletsFired++;
            }
            else if (BulletLevel == 2)
            {
                GameObject bullet = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet2");
                if (bullet != null)
                {
                    bullet.transform.position = firePoint.position;
                    bullet.transform.rotation = firePoint.rotation;
                    bullet.SetActive(true);
                }
                bullet.GetComponent<EnemyBulletType2>().speed = bulletSpeed;
                bulletsFired++;
            }
            else
            {
                GameObject bullet = EnemyBulletPool.SharedInstance.GetPooledObject("EnemyBullet3");
                if (bullet != null)
                {
                    bullet.transform.position = firePoint.position;
                    bullet.transform.rotation = firePoint.rotation;
                    bullet.SetActive(true);
                }
                bullet.GetComponent<EnemyBulletType3>().speed = bulletSpeed;
                bulletsFired++;
            }

            gameObject.transform.localScale -= new Vector3((maxScale - minScale) / (bulletsToFire), (maxScale - minScale) / (bulletsToFire), (maxScale - minScale) / (bulletsToFire));

            fireRateCountdown = fireRate;
        }
        else
        {
            fireRateCountdown -= Time.deltaTime;
        }
       
        //Destroy(gameObject);
    }

    private void FixedUpdate()
    {
       
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "Player")
        {
            other.gameObject.GetComponent<PlayerHealthController>().HurtPlayer(bulletDamage, gameObject);
            //Destroy(gameObject);
        }
        else if (other.gameObject.tag == "Wall")
        {
            gameObject.SetActive(false);
        }
        else if (other.gameObject.tag == "PlayerBullet")
        {
            other.gameObject.SetActive(false);
            //gameObject.SetActive(false);
        }
    }
}
