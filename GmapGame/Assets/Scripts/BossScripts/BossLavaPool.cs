using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossLavaPool : MonoBehaviour {

    public int bulletDamage;
    public float timeAlive;
    private float timeLeft;

    public float damageCooldown;
    private float damageCountdown;
    // Use this for initialization

    private void Start()
    {
        timeLeft = timeAlive;
        damageCountdown = 0;
    }


    // Update is called once per frame
    void Update()
    {
        if(timeLeft <= 0)
        {
            Destroy(gameObject);
        }
        timeLeft -= Time.deltaTime;
    }

    private void FixedUpdate()
    {
     
    }

    private void OnTriggerStay(Collider other)
    {
        if (other.gameObject.tag == "Player")
        {
            if (damageCountdown <= 0)
            {
                other.gameObject.GetComponent<PlayerHealthController>().HurtPlayer(bulletDamage, gameObject);
                damageCountdown = damageCooldown;
            }
            else
            {
                damageCountdown -= Time.deltaTime;
            }
        }

    }
}
