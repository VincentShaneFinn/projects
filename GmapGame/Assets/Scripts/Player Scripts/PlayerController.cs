using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class Boundary
{
    public float xMin, xMax, zMin, zMax;

}

public class PlayerController : MonoBehaviour {

    public bool cutscene;

    public float speed;
    public Boundary boundary;

    private Rigidbody myRigidbody;

    private Vector3 moveInput;
    private Vector3 moveVelocity;

    private Camera mainCamera;

    public PlayerGunController theGun;
    public PlayerAltGunController altGun;
    private bool altFiring;
    private bool gunFiring;

    public bool useController = false;

    private bool spacePressed;
    public float dodgeCooldownTime;
    private float dodgeCountdown;
    public float dodgeSpeed;
    public float dodgingTime;
    private float dodgingTimeCounter;
    private Vector3 savedInput;

    void Start()
    {
        myRigidbody = GetComponent<Rigidbody>();

        altFiring = false;
        gunFiring = false;
        spacePressed = false;

        dodgeCountdown = 0;
        dodgingTimeCounter = dodgingTime;
    }

    void Update()
    {
        if (!cutscene)
        {
            try {
                mainCamera = GameObject.FindGameObjectsWithTag("MainCamera")[0].GetComponent<Camera>();
            }
            catch { }
            float moveHorizontal = Input.GetAxisRaw("Horizontal"); // GetAxisRaw for no stop acceleration, GetAxis has some decceleration 
            float moveVertical = Input.GetAxisRaw("Vertical");

            moveInput = new Vector3(moveHorizontal, 0.0f, moveVertical);
            moveVelocity = moveInput * speed;

            //Mouse character aiming

            if (!useController)
            {
                if (Input.GetMouseButtonDown(0))
                {
                    gunFiring = true;
                    //theGun.isFiring = true;

                }
                if (Input.GetMouseButtonUp(0))
                {
                    //theGun.isFiring = false;
                    gunFiring = false;
                }

                if (Input.GetMouseButtonDown(1))
                {
                    altFiring = true;
                }

                if (Input.GetMouseButtonUp(1))
                {
                    altFiring = false;
                }

                if (altFiring)
                {
                    altGun.isFiring = true;
                    theGun.isFiring = false;
                }
                else if (gunFiring)
                {
                    altGun.isFiring = false;
                    theGun.isFiring = true;
                }

                if (!altFiring)
                {
                    altGun.isFiring = false;
                }
                if (!gunFiring)
                {
                    theGun.isFiring = false;
                }

                if (Input.GetKeyDown("space"))
                {
                    if (dodgeCountdown <= 0)
                    {
                        spacePressed = true;
                        gameObject.GetComponent<PlayerHealthController>().becomeInvulnerable();
                        dodgeCountdown = dodgeCooldownTime;
                    }
                }
                if (dodgeCountdown > 0)
                {
                    dodgeCountdown -= Time.deltaTime;
                }
            }

            //controller aiming
            if (useController)
            {
                Vector3 playerDirection = Vector3.right * Input.GetAxisRaw("RHorizontal") + Vector3.forward * -Input.GetAxisRaw("RVertical");
                if (playerDirection.sqrMagnitude > 0.0f)
                {
                    transform.rotation = Quaternion.LookRotation(playerDirection, Vector3.up);
                }

                if (Input.GetKeyDown(KeyCode.Joystick1Button7))
                {
                    gunFiring = true;
                    //theGun.isFiring = true;

                }
                if (Input.GetKeyUp(KeyCode.Joystick1Button7))
                {
                    //theGun.isFiring = false;
                    gunFiring = false;
                }

                if (Input.GetKeyDown(KeyCode.Joystick1Button5))
                {
                    altFiring = true;
                }

                if (Input.GetKeyUp(KeyCode.Joystick1Button5))
                {
                    altFiring = false;
                }

                if (altFiring)
                {
                    altGun.isFiring = true;
                    theGun.isFiring = false;
                }
                else if (gunFiring)
                {
                    altGun.isFiring = false;
                    theGun.isFiring = true;
                }

                if (!altFiring)
                {
                    altGun.isFiring = false;
                }
                if (!gunFiring)
                {
                    theGun.isFiring = false;
                }

                if (Input.GetKeyDown(KeyCode.Joystick1Button6))
                {
                    if (dodgeCountdown <= 0)
                    {
                        spacePressed = true;
                        gameObject.GetComponent<PlayerHealthController>().becomeInvulnerable();
                        dodgeCountdown = dodgeCooldownTime;
                    }
                }
                if (dodgeCountdown > 0)
                {
                    dodgeCountdown -= Time.deltaTime;
                }

            }
        }
    }

    void FixedUpdate()
    {
        if (!cutscene)
        {
            if ((spacePressed && moveVelocity != Vector3.zero) || dodgingTimeCounter < dodgingTime)
            {
                if (dodgingTimeCounter <= 0)
                {
                    myRigidbody.velocity = moveVelocity;
                    savedInput = Vector3.zero;
                    spacePressed = false;
                    dodgingTimeCounter = dodgingTime;
                    gameObject.GetComponent<PlayerHealthController>().becomeVulnerable();
                }
                else
                {
                    if (savedInput == Vector3.zero)
                    {
                        savedInput = moveInput;
                    }
                    if (savedInput.x > 0 || savedInput.z > 0)
                    {
                        myRigidbody.velocity = savedInput * dodgeSpeed / (float)Math.Sqrt(savedInput.x * savedInput.x + savedInput.z * savedInput.z);
                    }
                    else
                    {
                        myRigidbody.velocity = savedInput * dodgeSpeed;
                    }
                    dodgingTimeCounter -= Time.deltaTime;
                }
            }
            else
            {
                myRigidbody.velocity = moveVelocity;
                savedInput = Vector3.zero;
                spacePressed = false;
            }
            //myRigidbody.position = new Vector3 // creates invisible boundary
            //(
            //    Mathf.Clamp(myRigidbody.position.x, boundary.xMin, boundary.xMax),
            //    0.0f,
            //    Mathf.Clamp(myRigidbody.position.z, boundary.zMin, boundary.zMax)
            //);

            if (!useController)
            {
                Ray cameraRay = mainCamera.ScreenPointToRay(Input.mousePosition); // creates line from camera to mouse
                Plane groundPlane = new Plane(Vector3.up, new Vector3(0, .85f, 0));
                float rayLength;

                if (groundPlane.Raycast(cameraRay, out rayLength)) //where ray starts to where if hits another object
                {
                    Vector3 pointToLook = cameraRay.GetPoint(rayLength); // point in space for camera to look at
                    Debug.DrawLine(cameraRay.origin, pointToLook, Color.blue);

                    transform.LookAt(new Vector3(pointToLook.x, transform.position.y, pointToLook.z));

                }
            }
        }
    }
}
