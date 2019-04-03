using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossController : MonoBehaviour {

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

    public float rotationRatePercent; // percentage, so a number between 0 and 1 

    // Use this for initialization
    void Start () {
        myRigidBody = GetComponent<Rigidbody>();
        thePlayer = FindObjectOfType<PlayerController>();
        canLook = true;
        canFire = true;
        isFiring = false;
	}
	
	// Update is called once per frame
	void Update () {
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
                    if (Vector3.Distance(thePlayer.transform.position, gameObject.transform.position) < 3.5)
                    {
                        Quaternion targetRot = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x - transform.position.x, transform.position.y, thePlayer.transform.position.z - transform.position.z));
                        //Quaternion targetRotFirepoint = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x, firePoint.position.y, thePlayer.transform.position.z) - firePoint.position);
                        transform.rotation = Quaternion.Slerp(transform.rotation, targetRot, rotationRatePercent);
                    }
                    else
                    {
                        Quaternion targetRot = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x - firePoint.position.x, transform.position.y, thePlayer.transform.position.z - firePoint.position.z));
                        //Quaternion targetRotFirepoint = Quaternion.LookRotation(new Vector3(thePlayer.transform.position.x, firePoint.position.y, thePlayer.transform.position.z) - firePoint.position);
                        transform.rotation = Quaternion.Slerp(transform.rotation, targetRot, rotationRatePercent);
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
    }
}
