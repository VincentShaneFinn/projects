using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerGunController : MonoBehaviour {

    public bool isFiring;

    public PlayerBulletController bullet;
    public float bulletSpeed;

    public float timeBetweenShots;
    private float shotCounter;

    public Transform firePoint;

    public AudioClip AudioClip;
    public AudioSource AudioSource;

    // Use this for initialization
    void Start () {
        AudioSource.clip = AudioClip;
        AudioSource.time = 0f;
        timeBetweenShots = PlayerStats.PlayerFireRate;
    }
	
	// Update is called once per frame
	void Update () {
        if (isFiring)
        {
            shotCounter -= Time.deltaTime;
            if(shotCounter <= 0)
            {
                shotCounter = timeBetweenShots;
                AudioSource.Play();
                GameObject bullet = PlayerBulletPool.SharedInstance.GetPooledObject();
                if (bullet != null)
                {
                    bullet.transform.position = firePoint.position;
                    bullet.transform.rotation = firePoint.rotation;
                    bullet.SetActive(true);
                }
            }
        }
        else
        {
            if(shotCounter > 0)
                shotCounter -= Time.deltaTime;
        }
	}
}

