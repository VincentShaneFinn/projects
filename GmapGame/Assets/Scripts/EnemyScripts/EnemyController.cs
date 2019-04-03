using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyController : MonoBehaviour {

    private Rigidbody myRigidBody;
    public float moveSpeed;
    public bool canLook;
    public bool isFiring;
    public bool isGlitchy;
    public bool rotateOpposite;
    public float turnRate;
    public bool canFire;

    public PlayerController thePlayer;
    public Transform firePoint;

    private float rotationRatePercent = .03f; // percentage, so a number between 0 and 1 

    private bool fullsize;

    private float countdown;

    // Use this for initialization
    void Start () {
        myRigidBody = GetComponent<Rigidbody>();
        thePlayer = FindObjectOfType<PlayerController>();
        canLook = true;
        canFire = true;
        isFiring = false;
        countdown = 0;
	}
	// Update is called once per frame
	void Update () {
        if (fullsize)
        {
            if(countdown >= 1)
            {
                gameObject.transform.localScale = new Vector3(1,1,1);
                fullsize = false;
            }
            countdown += Time.deltaTime;
            gameObject.transform.localScale = new Vector3(countdown, countdown, countdown);
        }
        if (isGlitchy)
        {
            int negative = 1;
            if (rotateOpposite)
            {
                negative = -1;
            }
            Quaternion rot = transform.rotation;
            Vector3 temp = rot.eulerAngles;
            temp = new Vector3(temp.x, temp.y + turnRate * negative, temp.z);
            rot = Quaternion.Euler(temp);
            transform.rotation = rot;
        }
        else
        {
            if (canLook)
            {
                if (isFiring)
                {
                    if (Vector3.Distance(thePlayer.transform.position, gameObject.transform.position) < 2)
                    {
                        Quaternion targetRot = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x - transform.position.x, transform.position.y, thePlayer.transform.position.z - transform.position.z));
                        //Quaternion targetRotFirepoint = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x, firePoint.position.y, thePlayer.transform.position.z) - firePoint.position);
                        targetRot *= Quaternion.Euler(0, 45, 0);
                        transform.rotation = Quaternion.Slerp(transform.rotation, targetRot, rotationRatePercent);
                    }
                    else if (Vector3.Distance(thePlayer.transform.position, gameObject.transform.position) < 3.5)
                    {
                        Quaternion targetRot = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x - transform.position.x, transform.position.y, thePlayer.transform.position.z - transform.position.z));
                        //Quaternion targetRotFirepoint = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x, firePoint.position.y, thePlayer.transform.position.z) - firePoint.position);
                        targetRot *= Quaternion.Euler(0, 15, 0);
                        transform.rotation = Quaternion.Slerp(transform.rotation, targetRot, rotationRatePercent);
                    }
                    else
                    {
                        Quaternion targetRot = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x - firePoint.position.x, transform.position.y, thePlayer.transform.position.z - firePoint.position.z));
                        //Quaternion targetRotFirepoint = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x, firePoint.position.y, thePlayer.transform.position.z) - firePoint.position);
                        transform.rotation = Quaternion.Slerp(transform.rotation, targetRot, .01f);
                        //firePoint.rotation = Quaternion.Slerp(firePoint.rotation, targetRotFirepoint, rotationRatePercent);
                    }
                }
                else
                {
                    //transform.LookAt(new Vector3(thePlayer.transform.position.x, transform.position.y, thePlayer.transform.position.z));
                    //firePoint.LookAt(new Vector3(thePlayer.transform.position.x, firePoint.position.y, thePlayer.transform.position.z));
                    if (Vector3.Distance(thePlayer.transform.position, gameObject.transform.position) < 3.5)
                    {
                        transform.LookAt(new Vector3(thePlayer.transform.position.x, transform.position.y, thePlayer.transform.position.z));
                    }
                    else
                    {
                        Quaternion targetRot = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x - firePoint.position.x, transform.position.y, thePlayer.transform.position.z - firePoint.position.z));
                        transform.rotation = Quaternion.Slerp(transform.rotation, targetRot, 1);
                    }
                }
            }
        }
	}

    void FixedUpdate()
    {
        //myRigidBody.velocity = (transform.forward * moveSpeed);

        //this would help figure out to get enemies to not shot if far away
        if (Vector3.Distance(thePlayer.transform.position, transform.position) < 17){
            //GetComponent<EnemyFireStraightController>().disableFiring();
            //GetComponent<EnemyCrissCrossController>().disableFiring();
            //GetComponent<EnemyTripleAngledController>().disableFiring();
            //literally dont know what makes them stop firing
            GetComponent<EnemyHealthController>().MakeVulnerable();
            canFire = true;

        } else
        {
            canFire = false;
            GetComponent<EnemyHealthController>().MakeInvulnerable();
            //GetComponent<EnemyFireStraightController>().enableFiring();
            //GetComponent<EnemyCrissCrossController>().enableFiring();
            //GetComponent<EnemyTripleAngledController>().enableFiring();
            //GetComponent<EnemyTripleLinesController>().enableFiring();
            //GetComponent<EnemyTripleLinesController>().disableFiring();
        }
    }

    public void spawnAnimation()
    {
        gameObject.transform.localScale = new Vector3(0.1f,0.1f,0.1f);
        fullsize = true;
    }
}
