using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerMover : MonoBehaviour {
    public GameObject playerSprite;
    ////public Animator animator;
    public float moveSpeed;

    private Rigidbody2D myRigidbody;

    public LayerMask layerMask;
	private void Start()
	{
        myRigidbody = GetComponent<Rigidbody2D>();
        //Ignore the collisions between layer 0 (default) and layer 8 (custom layer you set in Inspector window)
    }

	private void Update()
	{
        Vector2 newVelocity = Vector2.zero;

        if (Input.GetKey(KeyCode.UpArrow) || Input.GetKey(KeyCode.W))
        {
            newVelocity += Vector2.up;
        }
        if (Input.GetKey(KeyCode.DownArrow) || Input.GetKey(KeyCode.S))
        {
            newVelocity += Vector2.down;
        }
        if (Input.GetKey(KeyCode.LeftArrow) || Input.GetKey(KeyCode.A))
        {
            newVelocity += Vector2.left;
        }
        if (Input.GetKey(KeyCode.RightArrow) || Input.GetKey(KeyCode.D))
        {
            newVelocity += Vector2.right;
        }
        myRigidbody.velocity = newVelocity.normalized * moveSpeed;
        //animator.SetFloat("speed", newVelocity.magnitude);
        if (newVelocity.magnitude > 0f){
            playerSprite.transform.localEulerAngles = Vector3.forward * HelperSingleton.instance.angleByVelocity[newVelocity.normalized];
        }

        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene(0);
        }
	}

    //private void OnCollisionEnter2D(Collision2D other)
    //{
    //    //print(other.gameObject);
    //}
}
