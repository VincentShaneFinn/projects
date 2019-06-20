using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerAltGunController : MonoBehaviour
{

    public bool isFiring;

    public PlayerAltGuideController altGuide;
    public float chargeTime;
    private float chargeCount;

    public Transform firePoint;
    public PlayerWaterBallController _playerWaterBall;

    public AudioClip AudioClip;
    public AudioSource AudioSource;

    // Use this for initialization
    void Start()
    {
        isFiring = false;
        chargeTime = PlayerStats.PlayerChargeTime;
        chargeCount = chargeTime;
        AudioSource.clip = AudioClip;
        AudioSource.time = 0f;

    }

    // Update is called once per frame
    void Update()
    {
        if (isFiring)
        {
            altGuide.enableVisibility();
            if (chargeCount <= 0)
            {
                AudioSource.Play();
                Instantiate(_playerWaterBall, firePoint.position, firePoint.rotation);
                chargeCount = chargeTime;
                altGuide.resetBall();
            }
            else
            {
                chargeCount -= Time.deltaTime;
                altGuide.growBall((chargeTime - chargeCount) / chargeTime);
            }
        }
        else
        {
            altGuide.disableVisibility();
            chargeCount = chargeTime;
            altGuide.resetBall();
        }

    }

}
