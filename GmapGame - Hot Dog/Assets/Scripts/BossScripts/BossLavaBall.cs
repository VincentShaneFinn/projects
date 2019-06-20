using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossLavaBall : MonoBehaviour {

    public GameObject LavaPool;
    private Transform thePlayer;

    public int bulletDamage;

    // Use this for initialization
    void Start()
    {
        thePlayer = GameObject.FindGameObjectWithTag("Player").transform;
        Rigidbody rb = GetComponent<Rigidbody>();
        print(thePlayer.position.x - transform.position.x);
        rb.velocity = new Vector3((thePlayer.position.x - transform.position.x)/2, 10, (thePlayer.position.z - transform.position.z)/2);
    }

    private void Update()
    {
        
    }

    private void FixedUpdate()
    {
       
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "Player")
        {
            other.gameObject.GetComponent<PlayerHealthController>().HurtPlayer(bulletDamage, gameObject);
            Destroy(gameObject);
        }
        else if (other.gameObject.tag == "Ground")
        {
            Instantiate(LavaPool,new Vector3(gameObject.transform.position.x,0, gameObject.transform.position.z), LavaPool.transform.rotation);
            Destroy(gameObject);

        }
    }
}
